package kill.me.palas.services;

import kill.me.palas.models.*;
import kill.me.palas.repositories.StatusRepository;
import kill.me.palas.repositories.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TestService {
    private final TestRepository testRepository;
    private final TopicService topicService;
    private final StatusRepository statusRepository;

    @Autowired
    public TestService(TestRepository testRepository,TopicService topicService,
                       StatusRepository statusRepository) {
        this.testRepository = testRepository;
        this.topicService = topicService;
        this.statusRepository = statusRepository;
    }

    public List<Test> findAll() {
        return testRepository.findAll();
    }

    public Test findOne(int id) {
        Test foundTest = testRepository.findById(id);
        return foundTest;
    }

    public void save(Test test, int id) {
        test.setTopic(topicService.findOne(id));
        test.setStatus(statusRepository.findById(3));
        test.setMix(false);
        test.setCount(5);
        testRepository.save(test);
    }

    public List<Test> findByName(String name){
        List<Test> tests = testRepository.findTestByName(name);
        return tests;
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
        testRepository.deleteById(id);
    }

    public List<Test> findTestByTopic(int id){
        return testRepository.findTestByTopic(topicService.findOne(id));
    }

    public Topic findTopic(int id){
        Test foundTest = testRepository.findById(id);
        return foundTest.getTopic();
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
}
