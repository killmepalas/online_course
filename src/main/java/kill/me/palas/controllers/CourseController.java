package kill.me.palas.controllers;

import kill.me.palas.services.CourseService;
import kill.me.palas.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/course")
public class CourseController {
    @Autowired
    UserService userService;

    @Autowired
    CourseService courseService;

    @GetMapping()
    public String index (Model model) {
        model.addAttribute("course", courseService.findAll());
        return "course/index";
    }

}
