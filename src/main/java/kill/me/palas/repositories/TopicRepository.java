package kill.me.palas.repositories;

import kill.me.palas.models.Course;
import kill.me.palas.models.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TopicRepository extends JpaRepository<Topic,Integer> {
    Topic findById(int id);
    List<Topic> findAllByCourse(Course course);
    List<Topic> findAllByCourseId(int courseId);
}
