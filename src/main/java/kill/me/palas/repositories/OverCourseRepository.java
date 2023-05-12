package kill.me.palas.repositories;

import kill.me.palas.models.Course;
import kill.me.palas.models.OverCourse;
import kill.me.palas.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OverCourseRepository extends JpaRepository<OverCourse, Integer> {
    OverCourse findById(int id);
    List<OverCourse> findByUser(User user);
    List<OverCourse> findByCourse(Course course);
    OverCourse findByUserAndCourse(User user, Course course);
    List<OverCourse> findAllByCourseAndStatusId(Course course, int status);
}
