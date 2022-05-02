package kill.me.palas.repositories;

import kill.me.palas.models.TestGrade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestGradeRepository extends JpaRepository<TestGrade,Integer> {
}
