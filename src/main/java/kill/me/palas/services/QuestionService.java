package kill.me.palas.services;

import kill.me.palas.models.Question;
import kill.me.palas.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
    private final QuestionRepository questionRepository;

    @Autowired
    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public List<Question> findAll() {
        return questionRepository.findAll();
    }

    public Question findOne(int id) {
        Optional<Question> foundQuestion = questionRepository.findById(id);
        return foundQuestion.orElse(null);
    }

    public void save(Question question) {
        questionRepository.save(question);
    }

    public void update(int id, Question updatedQuestion) {
        updatedQuestion.setId(id);
        questionRepository.save(updatedQuestion);
    }

    public void delete(int id) {
        questionRepository.deleteById(id);
    }
}
