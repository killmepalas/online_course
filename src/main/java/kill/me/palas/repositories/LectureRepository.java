package kill.me.palas.repositories;

import kill.me.palas.models.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LectureRepository extends JpaRepository<Lecture, Integer> {
    List<Lecture> findAllByTopicId(int topicId);
}
