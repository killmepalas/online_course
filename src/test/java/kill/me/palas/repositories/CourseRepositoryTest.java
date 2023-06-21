package kill.me.palas.repositories;

import kill.me.palas.models.Course;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.mockito.BDDMockito.given;

public class CourseRepositoryTest {

    @Autowired
    private CourseRepository courseRepository;

    @Test
    public void findById_Should_Return_True() {
        Course spring = courseRepository.findById(1);

    }

    @Test
    public void findCourseByName() {
    }

    @Test
    public void findCourseByUsers() {
    }

    @Test
    public void findCourseByTeacher() {
    }

    @Test
    public void findByCategoryId() {
    }

    @Test
    public void findAllByStatusId() {
    }

    @Test
    public void findAllByCategoryIdAndStatusId() {
    }

    @Test
    public void findAllByUsersAndStatusId() {
    }
}