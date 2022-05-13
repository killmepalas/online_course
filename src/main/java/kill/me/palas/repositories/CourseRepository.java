package kill.me.palas.repositories;

import kill.me.palas.models.Course;
import kill.me.palas.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Integer> {
    Course findById(int id);
    List<Course> findCourseByName(String name);
}
