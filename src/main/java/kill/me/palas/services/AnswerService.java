package kill.me.palas.services;

import kill.me.palas.models.Answer;
import kill.me.palas.models.Question;
import kill.me.palas.repositories.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class AnswerService {
    private final AnswerRepository answerRepository;
    private final QuestionService questionService;

    @Autowired
    public AnswerService(AnswerRepository answerRepository, QuestionService questionService) {
        this.questionService = questionService;
        this.answerRepository = answerRepository;
    }

    public List<Answer> findAll() {
        return answerRepository.findAll();
    }

    public Answer findOne(int id) {
        Answer foundAnswer = answerRepository.findById(id);
        return foundAnswer;
    }

    public void save(Answer answer, int id) {
        answer.setQuestion(questionService.findOne(id));
        answerRepository.save(answer);
    }

    public void update(int id, Answer updatedAnswer, Question question) {
        updatedAnswer.setId(id);
        updatedAnswer.setQuestion(question);
        answerRepository.save(updatedAnswer);
    }

    public void delete(int id) {
        answerRepository.deleteById(id);
    }

    public List<Answer> findAnswerByQuestion(int test_id){
        List<Answer> answers = answerRepository.findAnswerByQuestion(questionService.findOne(test_id));
        return answers;
    }

    public Question findQuestion(int id){
        Answer foundAnswer = answerRepository.findById(id);
        Question question = foundAnswer.getQuestion();
        return question;
    }

    public List<Answer> findMixedAnswerByQuestion(int test_id){
        List<Answer> answers = answerRepository.findAnswerByQuestion(questionService.findOne(test_id));
        Collections.shuffle(answers);
        return answers;
    }

}
