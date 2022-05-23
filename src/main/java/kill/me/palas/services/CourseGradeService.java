package kill.me.palas.services;

import kill.me.palas.models.*;
import kill.me.palas.repositories.CourseGradeRepository;
import kill.me.palas.repositories.TestGradeRepository;
import kill.me.palas.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseGradeService {
    private final CourseGradeRepository courseGradeRepository;
    private final TestGradeService testGradeService;
    private final TestService testService;
    private final UserServiceImpl userService;

    @Autowired
    public CourseGradeService(CourseGradeRepository courseGradeRepository, TestGradeService testGradeService,
                              TestService testService, UserServiceImpl userService) {
        this.courseGradeRepository = courseGradeRepository;
        this.testGradeService = testGradeService;
        this.testService = testService;
        this.userService = userService;
    }

    public List<CourseGrade> findAll() {
        return courseGradeRepository.findAll();
    }

    public CourseGrade findOne(int id) {
        Optional<CourseGrade> foundCourseGrade = courseGradeRepository.findById(id);
        return foundCourseGrade.orElse(null);
    }

    public void save(CourseGrade courseGrade) {
        courseGradeRepository.save(courseGrade);
    }


    public void update(int id, CourseGrade updatedCourseGrade, int grade) {
        updatedCourseGrade.setId(id);
        updatedCourseGrade.setGrade(grade);
        courseGradeRepository.save(updatedCourseGrade);
    }


    public void delete(int id) {
        courseGradeRepository.deleteById(id);
    }

    public CourseGrade findByUserAndCourse(User user, Course course){
        List<CourseGrade> courseGrades = courseGradeRepository.findByUser(user);
        CourseGrade result = null;
        for (CourseGrade courseGrade : courseGrades){
            if (courseGrade.getCourse().getId() == course.getId()) result = courseGrade;
        }
        return  result;
    }

    public void add(User user, Course course){
        List<TestGrade> testGrades = testGradeService.findByUserAndCourse(user,course);
        CourseGrade courseGrade = findByUserAndCourse(user,course);
        int result = 0;
        int grades = 0;
        for (TestGrade tg: testGrades){
            grades +=tg.getGrade();
        }
        result = grades/testService.findTestByCourse(course.getId()).size();
        if (courseGrade == null){
            courseGrade = new CourseGrade();
            courseGrade.setCourse(course);
            courseGrade.setUser(user);
            courseGrade.setGrade(result);
            courseGradeRepository.save(courseGrade);
        } else {
            courseGrade.setGrade(result);
            courseGradeRepository.save(courseGrade);
        }
    }

    public void recalc(Course course, String method, int test_id){
        for (CourseGrade courseGrade : courseGradeRepository.findByCourse(course)){
            if (method == "create"){
                courseGrade.setGrade(courseGrade.getGrade()*(testService.findTestByCourse(course.getId()).size()-1)/testService.findTestByCourse(course.getId()).size());
            }
            if (method == "delete"){
                if (testService.findTestByCourse(course.getId()).size() == 0) courseGrade.setGrade(0);
                else{
                    int tg = 0;
                    User user = courseGrade.getUser();
                    for (TestGrade testGrade: testGradeService.findByUserAndCourse(user,course)){
                        if (testGrade.getTest().getId()==test_id) tg = testGrade.getGrade();
                    }
                    courseGrade.setGrade((courseGrade.getGrade()*(testService.findTestByCourse(course.getId()).size()+1)-tg)/testService.findTestByCourse(course.getId()).size());
                }
            }
            courseGradeRepository.save(courseGrade);
        }
    }

    public int getRating(User user){
        int grades = 0;
        List<CourseGrade> courseGrades = courseGradeRepository.findByUser(user);
        for (CourseGrade cg: courseGrades){
            grades +=cg.getGrade();
        }
        int rating = 0;
        if (courseGrades.size() != 0) {rating = grades / courseGrades.size();}
        return rating;
    }
}
