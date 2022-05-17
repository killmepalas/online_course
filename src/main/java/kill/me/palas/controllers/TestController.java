package kill.me.palas.controllers;

import kill.me.palas.models.*;
import kill.me.palas.services.CourseService;
import kill.me.palas.services.QuestionService;
import kill.me.palas.services.TestService;
import kill.me.palas.services.UserServiceImpl;
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

@Controller
@RequestMapping("/test")
public class TestController {

    private TestService testService;

    private TestValidator testValidator;

    private UserServiceImpl userService;

    private CourseService courseService;

    private QuestionService questionService;

    private int count;

    @Autowired
    public TestController(TestService testService, TestValidator testValidator,
                          UserServiceImpl userService, CourseService courseService, QuestionService questionService){
        this.testService = testService;
        this.testValidator = testValidator;
        this.userService = userService;
        this.courseService = courseService;
        this.questionService = questionService;
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
                    break;
                }
            }
        }

        if (db_user != null && db_user.getId() == courseService.findTeacher(id).getId()){
            model.addAttribute("status", "teacher");
        } else if (db_user == null || check == false) {return "error/not_access";}


        List<Test> tests = testService.findTestByCourse(id);
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
        Course course = testService.findCourse(id);
        testService.update(id, test,course);
        return "redirect:/test/show/" + test.getId();
    }

    @GetMapping("/create/{id}")
    public String create(@ModelAttribute("test") Test test, @PathVariable("id") int id, Model model) {
        model.addAttribute("course",id);
        return "test/create";
    }

    @PostMapping("/create/{course_id}")
    public String create(@ModelAttribute("test") @Valid Test test,
                         BindingResult bindingResult, @PathVariable("course_id") int id_course) {
        testValidator.validate(test, bindingResult);
        if (bindingResult.hasErrors())
            return "test/create";
        testService.save(test, id_course);
        return "redirect:/test/" + test.getCourse().getId();
    }

    @PostMapping("/delete/{test_id}/{course_id}")
    public String delete(@PathVariable int test_id, @PathVariable int course_id) {
        testService.delete(test_id);
        return "redirect:/test/" + course_id;
    }

    @GetMapping("start/{test_id}")
    public String start(@PathVariable int test_id, Model model){
        List<Question> questions = testService.findOne(test_id).getQuestions();
        model.addAttribute("questions", questions);
        count = 0;
        return "test/execute";
    }

    @PostMapping({"/execute", "/execute/{question_id}"})
    public String execute(@PathVariable(required = false) int question_id, Model model){
        Question question = questionService.findOne(question_id);

        return "";
    }

}
