package kill.me.palas.services;

import kill.me.palas.models.Comment;
import kill.me.palas.models.Topic;
import kill.me.palas.models.User;
import kill.me.palas.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository){
        this.commentRepository = commentRepository;
    }

    public List<Comment> findAllByTopic(Topic topic){
        return commentRepository.findAllByTopic(topic);
    }

    public void save(String text, Topic topic, User user){
        Comment comment = new Comment();
        comment.setText(text);
        comment.setDate(Calendar.getInstance());
        comment.setTopic(topic);
        comment.setUser(user);
        commentRepository.save(comment);
    }
}
