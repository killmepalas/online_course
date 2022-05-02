package kill.me.palas.repositories;

import kill.me.palas.models.Attempt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttemptRepository extends JpaRepository<Attempt,Integer> {
}
