package kill.me.palas.repositories;

import kill.me.palas.models.Question;
import kill.me.palas.models.Test;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question,Integer> {
    List<Question> findQuestionByTest(Test test);
    List<Question> findQuestionByText(String text);
    Question findById(int id);
}
