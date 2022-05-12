package kill.me.palas.repositories;

import kill.me.palas.models.Course;
import kill.me.palas.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Integer> {
    Course findById(int id);
}
