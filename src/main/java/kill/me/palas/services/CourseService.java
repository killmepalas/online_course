package kill.me.palas.services;

import kill.me.palas.models.Course;
import kill.me.palas.models.User;
import kill.me.palas.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    private final CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    public Course findOne(int id) {
        Course foundCourse = courseRepository.findById(id);
        return foundCourse;
    }

    public User findTeacher(int id){
        Course foundCourse = courseRepository.findById(id);
        User teacher = foundCourse.getTeacher();
        return teacher;
    }

    public void save(Course course) {
        courseRepository.save(course);
    }

    public void update(int id, Course updatedCourse) {
        updatedCourse.setId(id);
        courseRepository.save(updatedCourse);
    }

    public void delete(int id) {
        courseRepository.deleteById(id);
    }
}
