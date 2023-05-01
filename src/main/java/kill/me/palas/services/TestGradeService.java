package kill.me.palas.services;

import kill.me.palas.models.*;
import kill.me.palas.repositories.TestGradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TestGradeService {
    private final TestGradeRepository testGradeRepository;

//    private final CourseGradeService courseGradeService;

    private final CourseService courseService;

    @Autowired
    public TestGradeService(TestGradeRepository testGradeRepository, /*CourseGradeService courseGradeService,*/
                            CourseService courseService) {
        this.testGradeRepository = testGradeRepository;
//        this.courseGradeService = courseGradeService;
        this.courseService = courseService;
    }

    public List<TestGrade> findAll() {
        return testGradeRepository.findAll();
    }

    public TestGrade findOne(int id) {
        Optional<TestGrade> foundTestGrade = testGradeRepository.findById(id);
        return foundTestGrade.orElse(null);
    }


    public void save(User user, Test test, int grade) {
        List<TestGrade> testGrades = testGradeRepository.findAll();
        boolean un = true;
        if (!testGrades.isEmpty())
            for (TestGrade tg : testGrades)
                if (user.getId() == tg.getUser().getId() && test.getId() == tg.getTest().getId() && grade > tg.getGrade()) {
                    testGradeRepository.delete(tg);
                    break;
                } else if (user.getId() == tg.getUser().getId() && test.getId() == tg.getTest().getId()){
                    un = false;
                    break;
                }
        if (un){
            TestGrade testGrade = new TestGrade();
            testGrade.setUser(user);
            testGrade.setTest(test);
            testGrade.setGrade(grade);
            testGradeRepository.save(testGrade);
        }
    }

    public List<TestGrade> findByUser(User user){
        List<TestGrade> testGrades = testGradeRepository.findByUser(user);
        return testGrades;
    }

    public List<TestGrade> findByUserAndCourse(User user, Course course){
        List<TestGrade> testGrades = testGradeRepository.findByUser(user);
        List<TestGrade> result = new ArrayList<>();
        for (TestGrade testGrade : testGrades){
//            if (testGrade.getTest().getCourse().getId() == course.getId()) result.add(testGrade);
        }
        return  result;
    }

    public List<TestGrade> findByUserAndTopic(User user, Topic topic){
        List<TestGrade> testGrades = testGradeRepository.findByUser(user);
        List<TestGrade> result = new ArrayList<>();
        for (TestGrade testGrade : testGrades){
            if (testGrade.getTest().getTopic().getId() == topic.getId()) result.add(testGrade);
        }
        return  result;
    }

    public void update(int id, TestGrade updatedTestGrade) {
        updatedTestGrade.setId(id);
        testGradeRepository.save(updatedTestGrade);
    }


    public void delete(int id) {
        testGradeRepository.deleteById(id);
    }
}
