package kill.me.palas.services;

import kill.me.palas.classes.QuestionCheck;
import kill.me.palas.models.Question;
import kill.me.palas.models.Test;
import kill.me.palas.models.Topic;
import kill.me.palas.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final TestService testService;

    @Autowired
    public QuestionService(QuestionRepository questionRepository, TestService testService) {
        this.questionRepository = questionRepository;
        this.testService = testService;
    }

    public List<Question> findAll() {
        return questionRepository.findAll();
    }

    public Question findOne(int id) {
        Question foundQuestion = questionRepository.findById(id);
        return foundQuestion;
    }

    public void save(Question question, int id) {
        question.setTest(testService.findOne(id));
        questionRepository.save(question);
    }

    public void update(int id, Question updatedQuestion, Test test) {
        updatedQuestion.setId(id);
        updatedQuestion.setTest(test);
        questionRepository.save(updatedQuestion);
    }

    public List<Question> findQuestionByTest(int test_id){
        List<Question> questions = questionRepository.findQuestionByTest(testService.findOne(test_id));
        return questions;
    }

    public List<Question> findByText(String text){
        List<Question> questions = questionRepository.findQuestionByText(text);
        return questions;
    }

    public void delete(int id) {
        questionRepository.deleteById(id);
    }

    public Test findTest(int id){
        Question foundQuestion = questionRepository.findById(id);
        Test test = foundQuestion.getTest();
        return test;
    }

    public Question findQuestionByTestById(int test_id, int question_id){
        Test test = testService.findOne(test_id);
        List<Question> questions = questionRepository.findQuestionByTest(test);
        return questions.get(question_id);
    }

    public Question findUncheckedQuestionOfTest(List<Question> questionChecks, Test test){
        List<Question> allQuestions = questionRepository.findQuestionByTest(test);
        Collections.shuffle(allQuestions);
        for (Question question: allQuestions){
            boolean flag = true;
            for (Question q: questionChecks){
                if (question.getId() == q.getId()) {
                    flag = false;
                    break;
                }
            }
            if (flag) return question;
        }
        return null;
    }

    public Question findRandomQuestionOfTopic(Topic topic){
        List<Test> tests = testService.findTestByTopic(topic.getId());
        Collections.shuffle(tests);
        List<Question> questions = findQuestionByTest(tests.get(0).getId());
        Collections.shuffle(questions);
        return questions.get(0);
    }
}
