package kill.me.palas.services;

import kill.me.palas.models.Answer;
import kill.me.palas.repositories.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AnswerService {
    private final AnswerRepository answerRepository;

    @Autowired
    public AnswerService(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    public List<Answer> findAll() {
        return answerRepository.findAll();
    }

    public Answer findOne(int id) {
        Optional<Answer> foundAnswer = answerRepository.findById(id);
        return foundAnswer.orElse(null);
    }

    public void save(Answer answer) {
        answerRepository.save(answer);
    }

    public void update(int id, Answer updatedAnswer) {
        updatedAnswer.setId(id);
        answerRepository.save(updatedAnswer);
    }

    public void delete(int id) {
        answerRepository.deleteById(id);
    }
}
