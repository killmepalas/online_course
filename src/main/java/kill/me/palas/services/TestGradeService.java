package kill.me.palas.services;

import kill.me.palas.models.*;
import kill.me.palas.repositories.OverCourseRepository;
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
    private final TopicService topicService;
    private final TestService testService;
    private final TopicGradeService topicGradeService;
    private final OverCourseService overCourseService;
    private final CourseGradeService courseGradeService;

    @Autowired
    public TestGradeService(TestGradeRepository testGradeRepository, TopicService topicService, TestService testService,
                            TopicGradeService topicGradeService, OverCourseService overCourseService,
                            CourseGradeService courseGradeService) {
        this.testGradeRepository = testGradeRepository;
        this.topicService = topicService;
        this.testService = testService;
        this.topicGradeService = topicGradeService;
        this.overCourseService = overCourseService;
        this.courseGradeService = courseGradeService;

    }

    public List<TestGrade> findAll() {
        return testGradeRepository.findAll();
    }

    public TestGrade findOne(int id) {
        Optional<TestGrade> foundTestGrade = testGradeRepository.findById(id);
        return foundTestGrade.orElse(null);
    }


    public void save(User user, Test test, int grade) {
        TestGrade testGrade = testGradeRepository.findByUserAndTest(user, test);
        if (testGrade != null) {
            if (grade > testGrade.getGrade()) {
                testGrade.setGrade(grade);
                testGradeRepository.save(testGrade);
            }
        } else {
            TestGrade tg = new TestGrade();
            tg.setUser(user);
            tg.setTest(test);
            tg.setGrade(grade);
            testGradeRepository.save(tg);
            Course course = test.getTopic().getCourse();
            if (courseGradeService.findByUserAndCourse(user, course) == null)
                overCourseService.save(user, test.getTopic().getCourse(), 6);
            else if (findAllByUserAndCourse(user, course).size() == testService.findAllActiveByCourse(course).size())
                overCourseService.update(overCourseService.findOneByUserAndCourse(user,course), 7);

        }
    }

    public List<TestGrade> findByUser(User user) {
        return testGradeRepository.findByUser(user);
    }

    public List<TestGrade> findAllByUserAndCourse(User user, Course course) {
        List<TestGrade> testGrades = testGradeRepository.findByUser(user);
        List<TestGrade> result = new ArrayList<>();
        for (TestGrade testGrade : testGrades)
            if (testGrade.getTest().getTopic().getCourse().getId() == course.getId()) result.add(testGrade);
        return result;
    }

    public List<TestGrade> findByUserAndTopic(User user, Topic topic) {
        List<TestGrade> testGrades = testGradeRepository.findByUser(user);
        List<TestGrade> result = new ArrayList<>();
        for (TestGrade testGrade : testGrades) {
            if (testGrade.getTest().getTopic().getId() == topic.getId()) result.add(testGrade);
        }
        return result;
    }

    public void update(int id, TestGrade updatedTestGrade) {
        updatedTestGrade.setId(id);
        testGradeRepository.save(updatedTestGrade);
    }


    public void delete(int id) {
        testGradeRepository.deleteById(id);
    }
}
