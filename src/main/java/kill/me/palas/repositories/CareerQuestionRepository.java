package kill.me.palas.repositories;

import kill.me.palas.models.CareerQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CareerQuestionRepository extends JpaRepository<CareerQuestion, Integer> {
    CareerQuestion findById(int id);
}
