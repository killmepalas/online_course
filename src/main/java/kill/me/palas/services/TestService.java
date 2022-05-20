package kill.me.palas.services;

import kill.me.palas.models.Course;
import kill.me.palas.models.Question;
import kill.me.palas.models.Test;
import kill.me.palas.models.User;
import kill.me.palas.repositories.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TestService {
    private final TestRepository testRepository;
    private final CourseService courseService;

    @Autowired
    public TestService(TestRepository testRepository, CourseService courseService) {
        this.testRepository = testRepository;
        this.courseService = courseService;
    }

    public List<Test> findAll() {
        return testRepository.findAll();
    }

    public Test findOne(int id) {
        Test foundTest = testRepository.findById(id);
        return foundTest;
    }

    public void save(Test test, int id) {
        test.setCourse(courseService.findOne(id));
        testRepository.save(test);
    }

    public List<Test> findByName(String name){
        List<Test> tests = testRepository.findTestByName(name);
        return tests;
    }


    public void update(int id, Test updatedTest, Course course) {
        updatedTest.setId(id);
        updatedTest.setCourse(course);
        testRepository.save(updatedTest);
    }

    public void delete(int id) {
        testRepository.deleteById(id);
    }

    public List<Test> findTestByCourse(int id){
        List<Test> tests = testRepository.findTestByCourse(courseService.findOne(id));
        return tests;
    }

    public Course findCourse(int id){
        Test foundTest = testRepository.findById(id);
        Course course = foundTest.getCourse();
        return course;
    }
}
