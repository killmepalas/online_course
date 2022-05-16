package kill.me.palas.controllers;

import kill.me.palas.models.*;
import kill.me.palas.repositories.RoleRepository;
import kill.me.palas.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/course")
public class CourseController{
    @Autowired
    UserServiceImpl userService;

    @Autowired
    CourseService courseService;

    @Autowired
    TestService testService;

    @Autowired
    SecurityServiceImpl securityService;

    @GetMapping()
    public String index (Model model) {
        model.addAttribute("course", courseService.findAll());
        return "course/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String username = loggedInUser.getName();
        kill.me.palas.models.User db_user = userService.findByUsername(username);
        model.addAttribute("teacher", courseService.findTeacher(id));
        model.addAttribute("course", courseService.findOne(id));
        if (db_user !=null && db_user.getId() == courseService.findOne(id).getTeacher().getId()){
            model.addAttribute("teach_course", "teacher");
        }
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
    public String teach(Model model){
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String username = loggedInUser.getName();
        kill.me.palas.models.User db_user = userService.findByUsername(username);
        List<Course> courses = courseService.findTeacherCourses(db_user);
        if (db_user !=null & !courses.isEmpty()){
            model.addAttribute("course",courses);
            return "teach/teach_courses";
        }
        else if(db_user == null) model.addAttribute("auth", "not");
            return "teach/teaching";
    }

    @GetMapping("/update/{id}")
    public String update(Model model, @PathVariable("id") int id) {
        model.addAttribute("course", courseService.findOne(id));
        return "course/edit";
    }

    @PostMapping("/update/{id}")
    public String update(@ModelAttribute("course") @Valid Course course, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "course/edit";
        User teacher = courseService.findTeacher(id);
        courseService.update(id, course,teacher);
        return "redirect:/course/"+id;
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        courseService.delete(id);
        return "redirect:/course/teach";
    }

    @GetMapping("/create")
    public String create(@ModelAttribute("course") Course course) {
        return "course/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("course") @Valid Course course,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "course/create";
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String username = loggedInUser.getName();
        kill.me.palas.models.User db_user = userService.findByUsername(username);
        courseService.save(course, db_user);
        String role = "ROLE_TEACHER";
        userService.setRoles(db_user,role);
        return "redirect:/course/teach";
    }
}
