package kill.me.palas.repositories;

import kill.me.palas.models.Topic;
import kill.me.palas.models.TopicGrade;
import kill.me.palas.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TopicGradeRepository extends JpaRepository<TopicGrade,Integer> {
    List<TopicGrade> findAllByUser(User user);
    List<TopicGrade> findAllByUserAndTopic(User user, Topic topic);
    TopicGrade findByUserAndTopic(User user, Topic topic);
    List<TopicGrade> findAllByTopic(Topic topic);
}
