package kill.me.palas.repositories;

import kill.me.palas.models.Answer;
import kill.me.palas.models.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer,Integer> {
    List<Answer> findAnswerByQuestion(Question question);
    Answer findById(int id);
}
