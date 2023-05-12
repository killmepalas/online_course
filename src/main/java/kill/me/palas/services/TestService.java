package kill.me.palas.services;

import kill.me.palas.models.*;
import kill.me.palas.repositories.StatusRepository;
import kill.me.palas.repositories.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Lazy;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class TestService {
    private final TestRepository testRepository;
    private final TopicService topicService;
    private final StatusRepository statusRepository;
    private final TopicGradeService topicGradeService;

    @Autowired
    public TestService(TestRepository testRepository,TopicService topicService,
                       StatusRepository statusRepository, @Lazy TopicGradeService topicGradeService) {
        this.testRepository = testRepository;
        this.topicService = topicService;
        this.statusRepository = statusRepository;
        this.topicGradeService = topicGradeService;
    }

    public List<Test> findAll() {
        return testRepository.findAll();
    }

    public Test findOne(int id) {
        return testRepository.findById(id);
    }

    public void save(Test test, int id) {
        test.setTopic(topicService.findOne(id));
        test.setStatus(statusRepository.findById(3));
        test.setMix(false);
        test.setCount(5);
        testRepository.save(test);
        topicGradeService.updateByTopic(test.getTopic());
    }

    public List<Test> findByName(String name){
        return testRepository.findTestByName(name);
    }


    public void update(int id, Test updatedTest, Topic topic) {
        Test oldTest = testRepository.findById(id);
        updatedTest.setId(id);
        updatedTest.setTopic(topic);
        updatedTest.setStatus(oldTest.getStatus());
        testRepository.save(updatedTest);
    }

    public void updateMix(int id, Test updatedTest){
        Test oldTest = testRepository.findById(id);
        oldTest.setMix(updatedTest.isMix());
        if (updatedTest.isMix()) oldTest.setCount(updatedTest.getCount());
        testRepository.save(oldTest);
    }

    public void delete(int id) {
        topicGradeService.updateByTopic(testRepository.findById(id).getTopic());
        testRepository.deleteById(id);
    }

    public List<Test> findTestByTopic(int id){
        return testRepository.findTestByTopic(topicService.findOne(id));
    }

    public Topic findTopic(int id){
        Test foundTest = testRepository.findById(id);
        return foundTest.getTopic();
    }

    public List<Test> findAllByCourse(Course course){
        List<Topic> topics = topicService.findAllByCourse(course);
        List<Test> tests = new ArrayList<>();
        for (Topic topic: topics)
            tests.addAll(findTestByTopic(topic.getId()));
        return tests;
    }

    public List<Test> findAllActiveByCourse(Course course){
        List<Topic> topics = topicService.findAllByCourse(course);
        List<Test> tests = new ArrayList<>();
        for (Topic topic: topics)
            tests.addAll(testRepository.findAllByTopicAndStatusId(topic,1));
        return tests;
    }

    public int findCountActiveTests(Topic topic){
        List<Test> allTests = testRepository.findTestByTopic(topic);
        List<Test> activeTests = new ArrayList<>();
        for (Test test: allTests){
            if (test.getStatus().getId() == 1) activeTests.add(test);
        }
        return activeTests.size();
    }

    public void changeStatus(int testId, int statusId){
        Test test = testRepository.findById(testId);
        test.setStatus(statusRepository.findById(statusId));
        testRepository.save(test);
    }

    public Test findUncheckedTestOfCourse(List<Test> testChecks,Course course){
        List<Topic> allTopics = topicService.findAllActiveTopicsByCourseId(course.getId());
        Collections.shuffle(allTopics);
        for (Topic topic: allTopics){
            for (Test test: testRepository.findAllByTopicAndStatusId(topic,1)){
                boolean flag = true;
                for (Test t: testChecks){
                    if (test.getId() == t.getId()) {
                        flag = false;
                        break;
                    }
                }
                if (flag) return test;
            }
        }
        return null;
    }
}
