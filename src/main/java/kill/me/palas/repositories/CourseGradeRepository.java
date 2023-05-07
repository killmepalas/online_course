package kill.me.palas.repositories;

import kill.me.palas.models.Course;
import kill.me.palas.models.CourseGrade;
import kill.me.palas.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseGradeRepository extends JpaRepository<CourseGrade,Integer> {
    List<CourseGrade> findByUser(User user);
    List<CourseGrade> findByCourse(Course course);
    CourseGrade findByCourseAndUser(Course course, User user);
}
