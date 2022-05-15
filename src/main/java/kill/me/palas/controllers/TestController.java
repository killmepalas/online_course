package kill.me.palas.controllers;

import kill.me.palas.models.Course;
import kill.me.palas.models.Test;
import kill.me.palas.models.User;
import kill.me.palas.services.TestService;
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
    @Autowired
    TestService testService;

    @Autowired
    TestValidator testValidator;

    @GetMapping("/{id}")
    public String index(Model model, @PathVariable("id") int id){
        List<Test> tests = testService.findTestByCourse(id);
        model.addAttribute("tests", tests);
        model.addAttribute("course", id);
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
}
