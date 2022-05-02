package kill.me.palas.services;

import kill.me.palas.models.Test;
import kill.me.palas.repositories.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class TestService {
    private final TestRepository testRepository;

    @Autowired
    public TestService(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    public List<Test> findAll() {
        return testRepository.findAll();
    }

    public Test findOne(int id) {
        Optional<Test> foundTest = testRepository.findById(id);
        return foundTest.orElse(null);
    }

    @Transactional
    public void save(Test test) {
        testRepository.save(test);
    }

    @Transactional
    public void update(int id, Test updatedTest) {
        updatedTest.setId(id);
        testRepository.save(updatedTest);
    }

    @Transactional
    public void delete(int id) {
        testRepository.deleteById(id);
    }
}
