package kill.me.palas.services;

import kill.me.palas.models.*;
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

    @Autowired
    public TestService(TestRepository testRepository,TopicService topicService) {
        this.testRepository = testRepository;
        this.topicService = topicService;
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
        testRepository.save(test);
    }

    public List<Test> findByName(String name){
        List<Test> tests = testRepository.findTestByName(name);
        return tests;
    }


    public void update(int id, Test updatedTest, Topic topic) {
        updatedTest.setId(id);
        updatedTest.setTopic(topic);
        testRepository.save(updatedTest);
    }

    public void delete(int id) {
        testRepository.deleteById(id);
    }

    public List<Test> findTestByTopic(int id){
        List<Test> tests = testRepository.findTestByTopic(topicService.findOne(id));
        return tests;
    }

    public Topic findTopic(int id){
        Test foundTest = testRepository.findById(id);
        return foundTest.getTopic();
    }
}
