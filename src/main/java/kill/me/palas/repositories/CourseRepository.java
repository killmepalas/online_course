package kill.me.palas.repositories;

import kill.me.palas.models.Course;
import kill.me.palas.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Integer> {
    Course findById(int id);
    List<Course> findCourseByName(String name);
    List<Course> findCourseByUsers(User user);
    List<Course> findCourseByTeacher(User user);
    List<Course> findByCategoryId(int categoryId);
    List<Course> findAllByStatusId(int statusId);
    List<Course> findAllByCategoryIdAndStatusId(int category, int status);
    List<Course> findAllByUsersAndStatusId(User user, int status);
}
