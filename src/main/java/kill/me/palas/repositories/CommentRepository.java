package kill.me.palas.repositories;

import kill.me.palas.models.Comment;
import kill.me.palas.models.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    Comment findById(int id);
    List<Comment> findAllByTopic(Topic topic);
}
