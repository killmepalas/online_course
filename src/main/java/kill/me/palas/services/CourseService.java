package kill.me.palas.services;

import kill.me.palas.models.Course;
import kill.me.palas.models.CourseGrade;
import kill.me.palas.models.OverCourse;
import kill.me.palas.models.User;
import kill.me.palas.repositories.CourseRepository;
import kill.me.palas.repositories.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final UserServiceImpl userService;
    private final StatusRepository statusRepository;
    private final CategoryService categoryService;


    @Autowired
    public CourseService(CourseRepository courseRepository, UserServiceImpl userService,
                         StatusRepository statusRepository, CategoryService categoryService) {
        this.courseRepository = courseRepository;
        this.userService = userService;
        this.statusRepository = statusRepository;
        this.categoryService = categoryService;
    }

    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    public List<Course> findActiveAll(){
        return courseRepository.findAllByStatusId(1);
    }

    public Course findOne(int id) {
        return courseRepository.findById(id);
    }

    public User findTeacher(int id){
        return courseRepository.findById(id).getTeacher();
    }

    public List<Course> findByName(String name){
        List<Course> courses = courseRepository.findAll();
        List<Course> foundCourses = new ArrayList<>();
        for (Course course: courses){
            String courseName = course.getName().toLowerCase(Locale.ROOT).replaceAll("\\s+","");
            name = name.toLowerCase(Locale.ROOT).replaceAll("\\s+","");
            if (courseName.contains(name)){
                foundCourses.add(course);
            }
        }
        return foundCourses;
    }

    public List<Course> findRandomByCategory(int categoryId){
        List<Course> courses = courseRepository.findAllByCategoryIdAndStatusId(categoryId,1);
        Collections.shuffle(courses);
        return  courses.stream().limit(3).toList();
    }

    public List<Course> findByCategory(int categoryId){
        return courseRepository.findAllByCategoryIdAndStatusId(categoryId,1);
    }

    public List<Course> findPageByCategory(int categoryId, int num){
        return courseRepository.findAllByCategoryIdAndStatusId(categoryId,1).stream().skip(num * 9L).limit(9).toList();
    }

    public List<Course> findByUserId(int id){
        return courseRepository.findCourseByUsers(userService.findOne(id));
    }

    public List<Course> findAllActiveByUser(User user){
        return courseRepository.findAllByUsersAndStatusId(user, 1);
    }

    public List<Course> findTeacherCourses(User teacher){
        return courseRepository.findCourseByTeacher(teacher);
    }

    public void save(Course course, User teacher) {
        course.setTeacher(teacher);
        course.setStatus(statusRepository.findById(3));
        course.setCategory(categoryService.findByName(course.getTextCategory()));
        courseRepository.save(course);
    }

    public void update(int id, Course updatedCourse, User teacher) {
        Course course = courseRepository.findById(id);
        updatedCourse.setId(id);
        updatedCourse.setTeacher(teacher);
        updatedCourse.setStatus(course.getStatus());
        courseRepository.save(updatedCourse);
    }

    public void delete(int id) {
        courseRepository.deleteById(id);
    }

    public List<Course> getPage(int num){
        return courseRepository.findAll().stream().skip(num* 9L).limit(9).toList();
    }

    public List<Course> getActivePage(int num){
        return courseRepository.findAllByStatusId(1).stream().skip(num* 9L).limit(9).toList();
    }

    public void add(User user,Course course){
        List<Course> courses = courseRepository.findCourseByUsers(user);
        courses.add(course);
        user.setCourses(courses);
        userService.save(user);
    }

    public List<User> findStudentsOnCourse(int id){
        List<User> students = userService.findAllStudents();
        List<User> studentsOfCourse = new ArrayList<>();
        for (User user: students){
            for (Course course: user.getCourses()){
                if (course.getId() == id) studentsOfCourse.add(user);
            }
        }
        return studentsOfCourse;
    }

    public boolean isTeacher(User user, Course course){
        return user.getId() == course.getTeacher().getId();
    }

    public boolean isStudent(User user, Course course){
        List<Course> courses = courseRepository.findCourseByUsers(user);
        for (Course c: courses)
            if (c.getId() == course.getId()) return true;
        return false;
    }

    public void changeStatus(int courseId, int statusId){
        Course course = courseRepository.findById(courseId);
        course.setStatus(statusRepository.findById(statusId));
        courseRepository.save(course);
    }
}
