package kill.me.palas.repositories;

import kill.me.palas.models.CareerAnswer;
import kill.me.palas.models.CareerQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CareerAnswerRepository extends JpaRepository<CareerAnswer, Integer> {
    List<CareerAnswer> findByQuestion(CareerQuestion question);
    CareerAnswer findById(int id);
}
