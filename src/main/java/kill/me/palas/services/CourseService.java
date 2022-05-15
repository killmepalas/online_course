package kill.me.palas.services;

import kill.me.palas.models.Course;
import kill.me.palas.models.User;
import kill.me.palas.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final UserServiceImpl userService;

    @Autowired
    public CourseService(CourseRepository courseRepository, UserServiceImpl userService) {
        this.courseRepository = courseRepository;
        this.userService = userService;
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

    public List<Course> findByName(String name){
        List<Course> courses = courseRepository.findCourseByName(name);
        return courses;
    }

    public List<Course> findByUserId(int id){
        User user = userService.findOne(id);
        List<Course> courses = courseRepository.findCourseByUsers(user);
        return courses;
    }

    public List<Course> findTeacherCourses(User teacher){
        List<Course> courses = courseRepository.findCourseByTeacher(teacher);
        return courses;
    }

    public void save(Course course, User teacher) {
        course.setTeacher(teacher);
        courseRepository.save(course);
    }

    public void update(int id, Course updatedCourse, User teacher) {
        updatedCourse.setId(id);
        updatedCourse.setTeacher(teacher);
        courseRepository.save(updatedCourse);
    }

    public void delete(int id) {
        courseRepository.deleteById(id);
    }
}
