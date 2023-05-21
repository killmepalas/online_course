package kill.me.palas.controllers;

import kill.me.palas.models.Comment;
import kill.me.palas.models.Topic;
import kill.me.palas.models.User;
import kill.me.palas.services.CommentService;
import kill.me.palas.services.CourseService;
import kill.me.palas.services.TopicService;
import kill.me.palas.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/comment")
public class CommentContoller {
    private final CommentService commentService;
    private final UserServiceImpl userService;
    private final TopicService topicService;
    private final CourseService courseService;

    @Autowired
    public CommentContoller(CommentService commentService, UserServiceImpl userService, TopicService topicService,
                            CourseService courseService) {
        this.commentService = commentService;
        this.userService = userService;
        this.topicService = topicService;
        this.courseService = courseService;
    }

    @PostMapping("/add/{topic_id}")
    public String add(@RequestParam(value = "comment") String text, @PathVariable("topic_id") int topicId) {
        User user = userService.getCurrentAuthUser();
        if (user != null) {
            Topic topic = topicService.findOne(topicId);
            if (courseService.isStudent(user, topic.getCourse()) || courseService.isTeacher(user,topic.getCourse())) {
                if (text != null)
                    commentService.save(text,topic, user);
                return "redirect:/topic/show/" + topicId;
            }
            return "error/not_access";
        }
        return "error/not_auth";
    }
}
