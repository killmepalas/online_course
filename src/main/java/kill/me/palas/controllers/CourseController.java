package kill.me.palas.controllers;

import kill.me.palas.services.CourseService;
import kill.me.palas.services.UserDetailsServiceImpl;
import kill.me.palas.services.UserService;
import kill.me.palas.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/course")
public class CourseController{
    @Autowired
    UserServiceImpl userService;

    @Autowired
    CourseService courseService;

    @GetMapping()
    public String index (Model model) {
        model.addAttribute("course", courseService.findAll());
        return "course/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("teacher", courseService.findTeacher(id));
        model.addAttribute("course", courseService.findOne(id));
        return "course/show";
    }

    @GetMapping("/find")
    public String find(@RequestParam(value = "courses") String name, Model model){
        model.addAttribute("courses", courseService.findByName(name));
        return "course/find";
    }

    @GetMapping("/my_courses")
    public String myCourses( Model model){
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String username = loggedInUser.getName();
        kill.me.palas.models.User db_user = userService.findByUsername(username);
        if (db_user !=null){
            model.addAttribute("course",courseService.findByUserId(db_user.getId()));
            return "course/my_courses";
        }
        else return "error/not_auth";
    }

    @GetMapping("/teach")
    public String teach(){
        return "teach/teaching";
    }
}
