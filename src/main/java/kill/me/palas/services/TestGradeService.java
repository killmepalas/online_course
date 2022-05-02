package kill.me.palas.services;

import kill.me.palas.models.TestGrade;
import kill.me.palas.repositories.TestGradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class TestGradeService {
    private final TestGradeRepository testGradeRepository;

    @Autowired
    public TestGradeService(TestGradeRepository testGradeRepository) {
        this.testGradeRepository = testGradeRepository;
    }

    public List<TestGrade> findAll() {
        return testGradeRepository.findAll();
    }

    public TestGrade findOne(int id) {
        Optional<TestGrade> foundTestGrade = testGradeRepository.findById(id);
        return foundTestGrade.orElse(null);
    }

    @Transactional
    public void save(TestGrade testGrade) {
        testGradeRepository.save(testGrade);
    }

    @Transactional
    public void update(int id, TestGrade updatedTestGrade) {
        updatedTestGrade.setId(id);
        testGradeRepository.save(updatedTestGrade);
    }

    @Transactional
    public void delete(int id) {
        testGradeRepository.deleteById(id);
    }
}
