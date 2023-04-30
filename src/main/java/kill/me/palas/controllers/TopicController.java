package kill.me.palas.controllers;

import kill.me.palas.models.Course;
import kill.me.palas.models.Test;
import kill.me.palas.models.Topic;
import kill.me.palas.models.User;
import kill.me.palas.services.CourseService;
import kill.me.palas.services.TopicGradeService;
import kill.me.palas.services.TopicService;
import kill.me.palas.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/topic")
public class TopicController {

    private final UserServiceImpl userService;
    private final TopicService topicService;
    private final TopicGradeService topicGradeService;
    private final CourseService courseService;

    @Autowired
    public TopicController(UserServiceImpl userService, TopicService topicService, TopicGradeService topicGradeService,
                           CourseService courseService){
        this.topicService = topicService;
        this.userService = userService;
        this.topicGradeService = topicGradeService;
        this.courseService = courseService;
    }


    @GetMapping("/{id_course}")
    public String index(Model model, @PathVariable("id_course") int id){
        User db_user = userService.getCurrentAuthUser();

        boolean check = false;

        if (db_user != null){
            for (Course course: db_user.getCourses()){
                if (course.getId() == id ){
                    check = true;
                    model.addAttribute("status","student");
                    model.addAttribute("grades", topicGradeService.findByUserAndCourse(db_user,course));
                    break;
                }
            }
        }

        if (db_user != null && db_user.getId() == courseService.findTeacher(id).getId()){
            model.addAttribute("status", "teacher");
        } else if (db_user == null || check == false) {return "error/not_access";}


        List<Topic> topics = topicService.findAllByCourseId(id);
        model.addAttribute("topics", topics);
        return "topic/index";
    }

}
