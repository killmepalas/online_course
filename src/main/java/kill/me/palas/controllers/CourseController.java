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
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/course")
public class CourseController{
    private final UserServiceImpl userService;

    private final CourseService courseService;

    private final CourseGradeService courseGradeService;

    private final CategoryService categoryService;

    private final OverCourseService overCourseService;

    @Autowired
    public CourseController(UserServiceImpl userService, CourseService courseService, CourseGradeService courseGradeService,
                            CategoryService categoryService, OverCourseService overCourseService){
        this.userService = userService;
        this.courseService = courseService;
        this.courseGradeService = courseGradeService;
        this.categoryService = categoryService;
        this.overCourseService = overCourseService;
    }

    @GetMapping("/category/index")
    public String categories(Model model){
        model.addAttribute("categories",categoryService.findAll());
        return "category/index";
    }

    @GetMapping("category/{category_id}/{num}")
    public String category(@PathVariable("category_id") int categoryId, @PathVariable("num") int num, Model model){
        model.addAttribute("category",categoryService.findOne(categoryId));
        model.addAttribute("course", courseService.findPageByCategory(categoryId,num));
        if (courseService.findByCategory(categoryId).size()<=num*9+9)
            model.addAttribute("end","true");
        return "course/index";
    }

    @GetMapping("/index/{num}")
    public String index (@PathVariable int num,Model model) {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String username = loggedInUser.getName();
        kill.me.palas.models.User db_user = userService.findByUsername(username);

        if (db_user != null){
            Set<Role> roles = db_user.getRoles();
            for (Role role: roles)
                if (role.getId() == 3) model.addAttribute("status","admin");

        }
        model.addAttribute("course", courseService.getActivePage(num));
        if (courseService.findActiveAll().size()<=num*9+9)
            model.addAttribute("end","true");
        return "course/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String username = loggedInUser.getName();
        kill.me.palas.models.User db_user = userService.findByUsername(username);
        Course c = courseService.findOne(id);
        model.addAttribute("teacher", courseService.findTeacher(id));
        model.addAttribute("course", c);
        model.addAttribute("sCount", courseService.findStudentsOnCourse(id).size());
        model.addAttribute("grade", courseGradeService.findByUserAndCourse(db_user,c));
        if (overCourseService.findOneByUserAndCourse(db_user,c) != null)
        model.addAttribute("overCourse",overCourseService.isUserOverCourse(db_user,c));

        if (db_user != null) {
            for (Course course : db_user.getCourses()) {
                if (course.getId() == id) {
                    model.addAttribute("teach_course", "added");
                    break;
                }
            }
        }
        if (db_user == null) model.addAttribute("teach_course","not_auth");
        if (db_user !=null && db_user.getId() == courseService.findOne(id).getTeacher().getId()){
            model.addAttribute("teach_course", "teacher");
        }
        return "course/show";
    }

    @GetMapping("/find")
    public String find(@RequestParam(value = "courses") String name, Model model){
        User user = userService.getCurrentAuthUser();
        if (user != null){
            if (userService.isAdmin(user))
                 model.addAttribute("status","admin");

        }
        model.addAttribute("course", courseService.findActiveByName(name,0));
        return "course/find";
    }

    @GetMapping("/find/category/{categoryId}")
    public String findByCategory(@RequestParam(value = "courses") String name, @PathVariable int categoryId, Model model){
        User user = userService.getCurrentAuthUser();
        if (user != null){
            if (userService.isAdmin(user))
                model.addAttribute("status","admin");

        }
        model.addAttribute("course", courseService.findActiveByName(name,categoryId));
        return "course/find";
    }

    @GetMapping("/my_courses")
    public String myCourses( Model model){
        User user = userService.getCurrentAuthUser();
        if (user !=null){
            List<Course> active = new ArrayList<>();
            active.addAll(courseService.findAllByUserAndOver(user,6));
            active.addAll(courseService.findAllByUserAndOver(user,7));
            model.addAttribute("active",active);
            model.addAttribute("over", courseService.findAllByUserAndOver(user, 8));
            model.addAttribute("not_begin", courseService.findAllByUserAndOver(user, 0));
            if (courseService.findByUserId(user.getId()).isEmpty()) model.addAttribute("is_empty",true);
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
        User db_user = userService.getCurrentAuthUser();
        if (db_user != null){
            if (courseService.findTeacher(id).getId() == db_user.getId())
            {
                courseService.delete(id);
                return "redirect:/course/teach";
            }
            return "error/not_access";
        }
        return "error/not_auth";
    }

    @PostMapping("/block/{id}")
    public String block(@PathVariable int id){
        User user = userService.getCurrentAuthUser();
        if (user != null){
            if (userService.isAdmin(user)){
                courseService.changeStatus(id,2);
                return "redirect: /course/index/0";
            }

            else return "error/not_access";
        }
        return "error/not_auth";
    }

    @GetMapping("/create")
    public String create(@ModelAttribute("course") Course course, Model model) {
        User user = userService.getCurrentAuthUser();
        if (user != null){
            if (userService.isTeacher(user)){
                model.addAttribute("categories",categoryService.findAllNames());
                return "course/create";
            }
            return "error/not_access";
        }
        return "error/not_auth";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("course") @Valid Course course,
                         BindingResult bindingResult, Model model) {
        User user = userService.getCurrentAuthUser();
        if (user != null){
            if (userService.isTeacher(user)){
                if (bindingResult.hasErrors())
                {
                    model.addAttribute("categories",categoryService.findAllNames());
                    return "course/create";
                }
                courseService.save(course, user);
                String role = "ROLE_TEACHER";
                userService.setRoles(user,role);
                return "redirect:/course/teach";
            }
            return "error/not_access";
        }
        return "error/not_auth";
    }

    @PostMapping("/add/{id}")
    public String add(@PathVariable int id){
        User user = userService.getCurrentAuthUser();
        if (user != null){
            if (userService.isStudent(user)){
                Course course = courseService.findOne(id);
                courseService.add(user,course);
                return "redirect:/course/" + id;
            }
            return "error/not_access";
        }
        return "error/not_auth";
    }

    @PostMapping("/close/{id}")
    public String close(@PathVariable int id){
        User user = userService.getCurrentAuthUser();
        if (user != null){
            Course course = courseService.findOne(id);
            if (courseService.isTeacher(user,course)){
                courseService.changeStatus(course.getId(),3);
                return "redirect: /course/" + id;
            }
            return "error/not_access";
        }
        return "error/not_auth";
    }

    @PostMapping("/publication/{id}")
    public String publication(@PathVariable int id){
        User user = userService.getCurrentAuthUser();
        if (user != null){
            Course course = courseService.findOne(id);
            if (courseService.isTeacher(user,course)){
                courseService.changeStatus(course.getId(),1);
                return "redirect: /course/" + id;
            }
            return "error/not_access";
        }
        return "error/not_auth";
    }
}
