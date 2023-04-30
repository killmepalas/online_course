package kill.me.palas.repositories;

import kill.me.palas.models.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LecturesRepository extends JpaRepository<Lecture, Integer> {
}
