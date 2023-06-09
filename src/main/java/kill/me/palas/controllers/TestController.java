package kill.me.palas.controllers;

import kill.me.palas.classes.QuestionCheck;
import kill.me.palas.models.*;
import kill.me.palas.services.*;
import kill.me.palas.validators.TestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/test")
public class TestController {

    private final TestService testService;

    private final TestValidator testValidator;

    private final UserServiceImpl userService;

    private final CourseService courseService;

    private final QuestionService questionService;

    private final AnswerService answerService;

    private final TestGradeService testGradeService;

    private final CourseGradeService courseGradeService;

    private final TopicService topicService;

    private final TopicGradeService topicGradeService;

    @Autowired
    public TestController(TestService testService, TestValidator testValidator,
                          UserServiceImpl userService, CourseService courseService,
                          QuestionService questionService, AnswerService answerService,
                          TestGradeService testGradeService, CourseGradeService courseGradeService,
                          TopicService topicService, TopicGradeService topicGradeService) {
        this.testService = testService;
        this.testValidator = testValidator;
        this.userService = userService;
        this.courseService = courseService;
        this.questionService = questionService;
        this.answerService = answerService;
        this.testGradeService = testGradeService;
        this.courseGradeService = courseGradeService;
        this.topicService = topicService;
        this.topicGradeService = topicGradeService;
    }

    @GetMapping("/find")
    public String find(@RequestParam(value = "test") String name, Model model) {
        model.addAttribute("tests", testService.findByName(name));
        return "test/index";
    }

    @GetMapping("/{id_course}")
    public String index(Model model, @PathVariable("id_course") int id) {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        User db_user = userService.findByUsername(loggedInUser.getName());

        boolean check = false;

        if (db_user != null) {
            for (Course course : db_user.getCourses()) {
                if (course.getId() == id) {
                    check = true;
                    model.addAttribute("status", "student");
                    model.addAttribute("grades", testGradeService.findByUser(db_user));
                    break;
                }
            }
        }

        if (db_user != null && db_user.getId() == courseService.findTeacher(id).getId()) {
            model.addAttribute("status", "teacher");
        } else if (db_user == null || !check) {
            return "error/not_access";
        }


        List<Test> tests = testService.findTestByTopic(id);
        model.addAttribute("tests", tests);
        return "test/index";
    }

    @GetMapping("/show/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("test", testService.findOne(id));
        return "test/show";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable("id") int id, Model model) {
        model.addAttribute("test", testService.findOne(id));
        return "test/edit";
    }

    @PostMapping("/update/{id}")
    public String update(@ModelAttribute("test") @Valid Test test, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "test/edit";
        Topic topic = testService.findTopic(id);
        testService.update(id, test, topic);
        return "redirect:/test/show/" + test.getId();
    }

    @GetMapping("/mixEdit/{id}")
    public String mixEdit(@PathVariable("id") int id, Model model) {
        model.addAttribute("test", testService.findOne(id));
        return "test/mixEdit";
    }

    @PostMapping("/mixEdit/{id}")
    public String mixEdit(@ModelAttribute("test") Test test, BindingResult bindingResult, @PathVariable int id) {
        testValidator.mix_validate(test, bindingResult, questionService.findQuestionByTest(test.getId()));
        if (bindingResult.hasErrors()) return "test/mixEdit";
        testService.updateMix(id, test);
        return "redirect:/question/" + id;
    }


    @GetMapping("/create/{id}")
    public String create(@ModelAttribute("test") Test test, @PathVariable("id") int id, Model model) {
        User user = userService.getCurrentAuthUser();
        if (user != null) {
            Topic topic = topicService.findOne(id);
            if (user.getId() == topic.getCourse().getTeacher().getId()) {
                model.addAttribute("topic", id);
                return "test/create";
            }
            return "error/not_access";
        }
        return "error/not_auth";
    }

    @PostMapping("/create/{topic_id}")
    public String create(@ModelAttribute("test") @Valid Test test,
                         BindingResult bindingResult, @PathVariable("topic_id") int topicId, Model model) {
        User user = userService.getCurrentAuthUser();
        if (user != null) {
            Topic topic = topicService.findOne(topicId);
            if (user.getId() == topic.getCourse().getTeacher().getId()) {
                if (bindingResult.hasErrors()) {
                    model.addAttribute("topic", topicService.findOne(topicId));
                    return "test/create";
                }

                testService.save(test, topicId);
                return "redirect:/topic/show/" + topicId;
            }
            return "error/not_access";
        }
        return "error/not_auth";
    }

    @PostMapping("/delete/{test_id}")
    public String delete(@PathVariable int test_id) {
        User user = userService.getCurrentAuthUser();
        if (user != null) {
            Topic topic = testService.findTopic(test_id);
            if (user.getId() == topic.getCourse().getTeacher().getId()) {
                testService.delete(test_id);
                topicGradeService.save(user, topic);
                courseGradeService.add(user, topic.getCourse());
                userService.updateRating(user);
                return "redirect:/topic/show/" + topic.getId();
            }
            return "error/not_access";
        }
        return "error/not_auth";
    }

    int mark;
    List<Question> questionChecks = new ArrayList<>();
    List<Topic> topicChecks = new ArrayList<>();
    List<Test> testChecks = new ArrayList<>();

    @GetMapping("/start/{test_id}/{question_id}")
    public String start(@PathVariable int test_id,
                        @PathVariable int question_id, Model model,
                        @ModelAttribute("answer") Answer answer) {
        User user = userService.getCurrentAuthUser();
        if (user != null) {
            Test test = testService.findOne(test_id);
            if (courseService.isStudent(user, test.getTopic().getCourse())) {
                List<Question> questions = questionService.findQuestionByTest(test_id);
                model.addAttribute("topic", testService.findTopic(test_id));
                if (questions.size() == 0) {
                    return "error/test";
                }
                if (test.isMix()) {
                    int count = test.getCount();
                    if (question_id == 1) questionChecks.clear();
                    if (count >= question_id) {
                        Question question = questionService.findUncheckedQuestionOfTest(questionChecks, test);
                        if (question != null) {
                            questionChecks.add(question);
                            List<Answer> answers = answerService.findMixedAnswerByQuestion(question.getId());
                            if (answers == null) return "error/test";
                            model.addAttribute("question", question);
                            model.addAttribute("answers", answers);
                            if (question_id == 1) mark = 0;
                            int next = question_id + 1;
                            model.addAttribute("next", next);
                            return "test/execute";
                        }
                        return "error/test";
                    }
                    questionChecks.clear();
                    return "redirect:/topic/show/" + testService.findTopic(test_id).getId();
                }
                if (question_id != 0) {
                    Question q = new Question();
                    q.setId(0);
                    Question question;
                    if (question_id == 1) {
                        question = questionService.findQuestionByTestById(test_id, 0);
                        List<Answer> answers = answerService.findAnswerByQuestion(question.getId());
                        if (answers == null) return "error/test";
                        model.addAttribute("question", question);
                        model.addAttribute("answers", answers);
                        mark = 0;
                        Question next = questions.stream().filter((s) -> s.getId() > question.getId()).findFirst().orElse(q);
                        model.addAttribute("next", next.getId());
                    } else {
                        question = questionService.findOne(question_id);
                        List<Answer> answers = answerService.findAnswerByQuestion(question_id);
                        if (answers == null) return "error/test";
                        Question next = questions.stream().filter((s) -> s.getId() > question.getId()).findFirst().orElse(q);
                        model.addAttribute("question", question);
                        model.addAttribute("answers", answers);
                        model.addAttribute("next", next.getId());
                    }
                    return "test/execute";
                }
                return "redirect:/topic/show/" + testService.findTopic(test_id).getId();
            }
            return "error/not_access";
        }
        return "error/not_auth";
    }


    @PostMapping("/execute/{test_id}/{next}")
    public String execute(@ModelAttribute("answer") Answer answer, BindingResult bindingResult,
                          @PathVariable int next,
                          @PathVariable int test_id) {
        User user = userService.getCurrentAuthUser();
        if (user != null) {
            Test test = testService.findOne(test_id);
            if (courseService.isStudent(user, test.getTopic().getCourse())) {
                testValidator.answers_validate(answer, bindingResult);
                if (bindingResult.hasErrors())
                    return "test/execute";
                if (answer == null) return "error/execute_test";
                int id_answer = answer.getId();
                if (answerService.findOne(id_answer) == null) return "error/execute_test";
                if (answerService.findOne(id_answer).getIs_right())
                    mark += 1;
                if (test.isMix()) {
                    if (test.getCount() < next) {
                        int grade = mark * 100 / (test.getCount());
                        testGradeService.save(user, test, grade);
                        topicGradeService.save(user, test.getTopic());
                        courseGradeService.add(user, test.getTopic().getCourse());
                        userService.updateRating(user);
                    }
                } else if (next == 0) {
                    int grade = mark * 100 / (questionService.findQuestionByTest(test_id).size());
                    testGradeService.save(user, test, grade);
                    topicGradeService.save(user, test.getTopic());
                    courseGradeService.add(user, test.getTopic().getCourse());
                    userService.updateRating(user);
                }
                return "redirect:/test/start/" + test_id + "/" + next;
            }
            return "error/not_access";
        }
        return "error/not_auth";

    }

    @PostMapping("/close/{id}")
    public String close(@PathVariable("id") int id) {
        testService.changeStatus(id, 3);
        return "redirect: /test/show/" + id;
    }

    @PostMapping("/open/{id}")
    public String open(@PathVariable("id") int id) {
        testService.changeStatus(id, 1);
        return "redirect: /test/show/" + id;
    }

    @GetMapping("/final_testing/{course_id}/{question_id}")
    public String finalTesting(@PathVariable int course_id, Model model, @PathVariable int question_id,
                               @ModelAttribute("answer") Answer answer) {
        User user = userService.getCurrentAuthUser();
        if (user != null) {
            Course course = courseService.findOne(course_id);
            if (courseService.isStudent(user, course)) {
                if (question_id == 1) testChecks.clear();
                if (question_id <= testService.findAllActiveByCourse(course).size()) {
                    Test test = testService.findUncheckedTestOfCourse(testChecks, course);
                    Question question = questionService.findRandomQuestionOfTest(test);
                    if (question != null) {
                        testChecks.add(test);
                        List<Answer> answers = answerService.findMixedAnswerByQuestion(question.getId());
                        if (answers == null) return "error/test";
                        model.addAttribute("question", question);
                        model.addAttribute("answers", answers);
                        if (question_id == 1) mark = 0;
                        int next = question_id + 1;
                        model.addAttribute("next", next);
                        return "test/final_execute";
                    }
                    return "error/test";
                }
                topicChecks.clear();
                return "redirect:/topic/" + course_id;
            }
            return "error/not_access";
        }
        return "error/not_auth";
    }


    @PostMapping("/final_execute/{course_id}/{next}")
    public String finalExecute(@ModelAttribute("answer") Answer answer, BindingResult bindingResult,
                               @PathVariable int next,
                               @PathVariable int course_id) {
        User user = userService.getCurrentAuthUser();
        if (user != null) {
            Course course = courseService.findOne(course_id);
            if (courseService.isStudent(user, course)) {
                testValidator.answers_validate(answer, bindingResult);
                if (bindingResult.hasErrors())
                    return "error/execute_test";
                int id_answer = answer.getId();
                if (answerService.findOne(id_answer) == null) return "error/execute_test";
                if (answerService.findOne(id_answer).getIs_right())
                    mark += 1;
                if (testService.findAllActiveByCourse(course).size() < next) {
                    int grade = mark * 100 / testService.findAllActiveByCourse(course).size();
                    courseGradeService.saveFinalTesting(course, user, grade);
                    courseGradeService.add(user, course);
                    userService.updateRating(user);
                }
                return "redirect:/test/final_testing/" + course_id + "/" + next;
            }
            return "error/not_access";
        }
        return "error/not_auth";

    }
}
