package kill.me.palas.controllers;

import kill.me.palas.models.Course;
import kill.me.palas.models.Test;
import kill.me.palas.models.Topic;
import kill.me.palas.models.User;
import kill.me.palas.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/topic")
public class TopicController {

    private final UserServiceImpl userService;
    private final TopicService topicService;
    private final TopicGradeService topicGradeService;
    private final CourseService courseService;
    private final TestService testService;
    private final LectureService lectureService;
    private final TestGradeService testGradeService;
    private final OverCourseService overCourseService;
    private final CourseGradeService courseGradeService;

    @Autowired
    public TopicController(UserServiceImpl userService, TopicService topicService, TopicGradeService topicGradeService,
                           CourseService courseService, TestService testService, LectureService lectureService,
                           TestGradeService testGradeService, OverCourseService overCourseService,
                           CourseGradeService courseGradeService) {
        this.topicService = topicService;
        this.userService = userService;
        this.topicGradeService = topicGradeService;
        this.courseService = courseService;
        this.testService = testService;
        this.lectureService = lectureService;
        this.testGradeService = testGradeService;
        this.overCourseService = overCourseService;
        this.courseGradeService = courseGradeService;
    }


    @GetMapping("/{id_course}")
    public String index(Model model, @PathVariable("id_course") int id) {
        User db_user = userService.getCurrentAuthUser();
        if (db_user != null) {
            boolean check = false;
            if (db_user.getId() == courseService.findTeacher(id).getId()) {
                model.addAttribute("status", "teacher");
                model.addAttribute("topics", topicService.findAllByCourseId(id));
                check = true;
            } else {
                for (Course course : db_user.getCourses()) {
                    if (course.getId() == id) {
                        check = true;
                        model.addAttribute("status", "student");
                        model.addAttribute("grade",courseGradeService.findByUserAndCourse(db_user,course));
                        model.addAttribute("grades", topicGradeService.findByUserAndCourse(db_user, course));
                        model.addAttribute("topics", topicService.findAllActiveTopicsByCourseId(id));
                        if (overCourseService.findOneByUserAndCourse(db_user, course) != null)
                            if (overCourseService.isUserOverCourse(db_user, course) != 6) {
                                model.addAttribute("final_testing", true);
                                model.addAttribute("course", course);
                                model.addAttribute("courseGrade", courseGradeService.findByUserAndCourse(db_user, course));
                            }

                        break;
                    }
                }
            }
            if (check) return "topic/index";
            return "error/not_access";
        }
        return "error/not_auth";
    }

    @GetMapping("/show/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        User user = userService.getCurrentAuthUser();
        if (user != null) {
            Topic topic = topicService.findOne(id);
            Course tCourse = topic.getCourse();
            if (courseService.isStudent(user, tCourse) || courseService.isTeacher(user, tCourse)) {
                if (courseService.isTeacher(user, tCourse)) {
                    model.addAttribute("status", "teacher");
                    model.addAttribute("tests", testService.findTestByTopic(id));

                } else if (courseService.isStudent(user, tCourse)) {
                    model.addAttribute("status", "student");
                    model.addAttribute("grades", testGradeService.findByUserAndTopic(user, topic));
                    model.addAttribute("tests", testService.findAllActiveByTopic(topic));
                }
                model.addAttribute("topic", topic);
                model.addAttribute("course_id", tCourse.getId());
                model.addAttribute("lectures", lectureService.findByTopic(id));
                model.addAttribute("countActiveTests", testService.findCountActiveTests(topic));
                return "topic/show";
            }
            return "error/not_access";
        }
        return "error/not_auth";
    }

    @PostMapping("/close/{id}")
    public String close(@PathVariable("id") int id) {
        topicService.changeStatus(id, 3);
        return "redirect: /topic/show/" + id;
    }

    @PostMapping("/open/{id}")
    public String open(@PathVariable("id") int id) {
        topicService.changeStatus(id, 1);
        return "redirect: /topic/show/" + id;
    }

    @GetMapping("create/{course_id}")
    public String create(@ModelAttribute("topic") Topic topic, @PathVariable("course_id") int courseId, Model model) {
        model.addAttribute("course", courseId);
        return "topic/create";
    }

    @PostMapping("/create/{course_id}")
    public String create(@ModelAttribute("topic") @Valid Topic topic,
                         BindingResult bindingResult, @PathVariable("course_id") int courseId) {
        if (bindingResult.hasErrors())
            return "topic/create";
        topicService.save(topic, courseService.findOne(courseId));
        return "redirect:/topic/" + courseId;
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable("id") int id, Model model) {
        model.addAttribute("topic", topicService.findOne(id));
        return "topic/update";
    }

    @PostMapping("/update/{id}")
    public String update(@ModelAttribute("topic") @Valid Topic topic, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "topic/update";
        Course course = topicService.findCourse(id);
        topicService.update(id, topic, course);
        return "redirect:/topic/show/" + id;
    }

    @PostMapping("delete/{id}")
    public String delete(@PathVariable("id") int id) {
        int courseId = topicService.findCourse(id).getId();
        topicService.delete(id);
        return "redirect: /topic/" + courseId;
    }
}
