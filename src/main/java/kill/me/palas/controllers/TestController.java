package kill.me.palas.controllers;

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
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/test")
public class TestController {

    private TestService testService;

    private TestValidator testValidator;

    private UserServiceImpl userService;

    private CourseService courseService;

    private QuestionService questionService;

    private AnswerService answerService;

    private TestGradeService testGradeService;

    private CourseGradeService courseGradeService;

    private TopicService topicService;

    @Autowired
    public TestController(TestService testService, TestValidator testValidator,
                          UserServiceImpl userService, CourseService courseService,
                          QuestionService questionService, AnswerService answerService,
                          TestGradeService testGradeService, CourseGradeService courseGradeService,
                          TopicService topicService){
        this.testService = testService;
        this.testValidator = testValidator;
        this.userService = userService;
        this.courseService = courseService;
        this.questionService = questionService;
        this.answerService = answerService;
        this.testGradeService = testGradeService;
        this.courseGradeService = courseGradeService;
        this.topicService = topicService;
    }

    @GetMapping("/find")
    public String find(@RequestParam(value = "test") String name, Model model){
        model.addAttribute("tests", testService.findByName(name));
        return "test/index";
    }

    @GetMapping("/{id_course}")
    public String index(Model model, @PathVariable("id_course") int id){
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        User db_user = userService.findByUsername(loggedInUser.getName());

        boolean check = false;

        if (db_user != null){
            for (Course course: db_user.getCourses()){
                if (course.getId() == id ){
                    check = true;
                    model.addAttribute("status","student");
                    model.addAttribute("grades", testGradeService.findByUser(db_user));
                    break;
                }
            }
        }

        if (db_user != null && db_user.getId() == courseService.findTeacher(id).getId()){
            model.addAttribute("status", "teacher");
        } else if (db_user == null || check == false) {return "error/not_access";}


        List<Test> tests = testService.findTestByTopic(id);
        model.addAttribute("tests", tests);
        return "test/index";
    }

    @GetMapping("/show/{id}")
    public String show (@PathVariable("id") int id, Model model) {
        model.addAttribute("test", testService.findOne(id));
        return "test/show";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable("id") int id, Model model){
        model.addAttribute("test", testService.findOne(id));
        return "test/edit";
    }

    @PostMapping("/update/{id}")
    public String update(@ModelAttribute("test") @Valid Test test, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        testValidator.validate(test, bindingResult);
        if (bindingResult.hasErrors())
            return "test/edit";
        Topic topic = testService.findTopic(id);
        testService.update(id, test,topic);
        return "redirect:/test/show/" + test.getId();
    }

    @GetMapping("/create/{id}")
    public String create(@ModelAttribute("test") Test test, @PathVariable("id") int id, Model model) {
        model.addAttribute("topic",id);
        return "test/create";
    }

    @PostMapping("/create/{topic_id}")
    public String create(@ModelAttribute("test") @Valid Test test,
                         BindingResult bindingResult, @PathVariable("topic_id") int topicId, Model model) {
        if (bindingResult.hasErrors())
        {
            model.addAttribute("topic", topicService.findOne(topicId));
            return "test/create";
        }

        testService.save(test, topicId);
//        courseGradeService.recalc(courseService.findOne(id_course),"create",0);
        return "redirect:/topic/show/" + topicId;
    }

    @PostMapping("/delete/{test_id}")
    public String delete(@PathVariable int test_id) {
        Topic topic = testService.findTopic(test_id);
        testService.delete(test_id);
//        if (testService.findAll().size() != 1) courseGradeService.recalc(courseService.findOne(topic.getId()),"delete",test_id);
        return "redirect:/topic/show/" + topic.getId();
    }

    int mark;
    @GetMapping("/start/{test_id}/{question_id}")
    public String start(@PathVariable int test_id,
                        @PathVariable int question_id, Model model,
                        @ModelAttribute("answer") Answer answer){
        if (question_id !=0){
            Question q = new Question();
            q.setId(0);
            List<Question> questions = questionService.findQuestionByTest(test_id);
            if (questions.size() == 0) {
                model.addAttribute("topic", testService.findTopic(test_id));
                return "error/test";}
            if (question_id == 1){
                Question question = questionService.findQuestionByTestById(test_id,0);
                List<Answer> answers = answerService.findAnswerByQuestion(question.getId());
                if (answers == null) return "error/test";
                model.addAttribute("question",question);
                model.addAttribute("answers",answers);
                mark = 0;
                Question next = questions.stream().filter((s) -> s.getId() > question.getId()).findFirst().orElse(q);
                model.addAttribute("next",next.getId());
                return "test/execute";
            } else {
                Question question = questionService.findOne(question_id);
                List<Answer> answers = answerService.findAnswerByQuestion(question_id);
                if (answers == null) return "error/test";
                Question next = questions.stream().filter((s) -> s.getId() > question.getId()).findFirst().orElse(q);
                model.addAttribute("question", question);
                model.addAttribute("answers", answers);
                model.addAttribute("next", next.getId());
                return "test/execute";
            }
        } else return "redirect:/topic/show/" + testService.findTopic(test_id).getId();
    }

    @PostMapping("/execute/{test_id}/{next}")
    public String execute(@ModelAttribute("answer") Answer answer, BindingResult bindingResult,
                          @PathVariable int next,
                          @PathVariable int test_id){
        testValidator.answers_validate(answer, bindingResult);
        if (bindingResult.hasErrors())
            return "test/execute";
        int id_answer = answer.getId();
        if (answerService.findOne(id_answer).getIs_right()){
            mark +=1;
        }

        if (next == 0){
            int grade = mark * 100/(questionService.findQuestionByTest(test_id).size());
            Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
            User db_user = userService.findByUsername(loggedInUser.getName());
            testGradeService.save(db_user,testService.findOne(test_id),grade);
//            courseGradeService.add(db_user,testService.findOne(test_id).getCourse());
        }

        return "redirect:/test/start/" + test_id + "/" + next;
    }

    @PostMapping("/close/{id}")
    public String close(@PathVariable("id")int id){
        testService.changeStatus(id,3);
        return "redirect: /test/show/" + id;
    }

    @PostMapping("/open/{id}")
    public String open(@PathVariable("id")int id){
        testService.changeStatus(id,1);
        return "redirect: /test/show/" + id;
    }
}
