package kill.me.palas.controllers;

import kill.me.palas.models.Lecture;
import kill.me.palas.models.Test;
import kill.me.palas.models.Topic;
import kill.me.palas.models.User;
import kill.me.palas.services.CourseService;
import kill.me.palas.services.LectureService;
import kill.me.palas.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/lecture")
public class LectureController {

    private final LectureService lectureService;
    private final UserServiceImpl userService;
    private final CourseService courseService;

    @Autowired
    public LectureController(LectureService lectureService, UserServiceImpl userService,
                             CourseService courseService) {
        this.lectureService = lectureService;
        this.userService = userService;
        this.courseService = courseService;
    }

    @GetMapping("/create/{topic_id}")
    public String create(@ModelAttribute("lecture") Lecture lecture, @PathVariable("topic_id") int topicId, Model model) {
        User curUser = userService.getCurrentAuthUser();
        if (curUser != null) {
            if (curUser.getId() == lecture.getTopic().getCourse().getTeacher().getId()) {
                model.addAttribute("topic", topicId);
                return "lecture/create";
            }
            return "error/not_access";
        }
        return "error/not_auth";
    }

    @PostMapping("/create/{topic_id}")
    public String create(@ModelAttribute("lecture") @Valid Lecture lecture,
                         BindingResult bindingResult, @PathVariable("topic_id") int topicId) {
        User curUser = userService.getCurrentAuthUser();
        if (curUser != null) {
            if (curUser.getId() == lecture.getTopic().getCourse().getTeacher().getId()) {
                if (bindingResult.hasErrors())
                    return "topic/create";
                lectureService.save(lecture, topicId);
//        courseGradeService.recalc(courseService.findOne(id_course),"create",0);
                return "redirect:/topic/show/" + topicId;
            }
            return "error/not_access";
        }
        return "error/not_auth";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable("id") int id, Model model) {
        User curUser = userService.getCurrentAuthUser();
        if (curUser != null) {
            Lecture lecture = lectureService.findOne(id);
            if (curUser.getId() == lecture.getTopic().getCourse().getTeacher().getId()) {
                model.addAttribute("lecture", lecture);
                return "lecture/update";
            }
            return "error/not_access";
        }
        return "error/not_auth";
    }

    @PostMapping("/update/{id}")
    public String update(@ModelAttribute("test") @Valid Lecture lecture, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        User curUser = userService.getCurrentAuthUser();
        if (curUser != null) {
            Topic topic = lectureService.findTopic(id);
            if (curUser.getId() == topic.getCourse().getTeacher().getId()) {
                if (bindingResult.hasErrors())
                    return "lecture/update";
                lectureService.update(id, lecture, topic);
                return "redirect:/lecture/show/" + lecture.getId();
            }
            return "error/not_access";
        }
        return "error/not_auth";
    }

    @GetMapping("/show/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        User curUser = userService.getCurrentAuthUser();
        if (curUser != null) {
            Lecture lecture = lectureService.findOne(id);
            boolean allow = false;
            if (curUser.getId() == lecture.getTopic().getCourse().getTeacher().getId()) {
                allow = true;
                model.addAttribute("status",2);
            }
            else if (courseService.isStudent(curUser, lecture.getTopic().getCourse()))
            {
                allow = true;
                model.addAttribute("status",1);
            };
            if (allow){
                model.addAttribute("lecture", lectureService.findOne(id));
                return "lecture/show";
            }
            return "error/not_access";
        }
        return "error/not_auth";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable int id){
        User user = userService.getCurrentAuthUser();
        if (user != null){
            Topic topic = lectureService.findTopic(id);
            if (user.getId() == topic.getCourse().getTeacher().getId()){
                lectureService.delete(id);
                return "redirect:/topic/show/"+topic.getId();
            }
            return "error/not_access";
        }
        return "error/not_auth";
    }

}
