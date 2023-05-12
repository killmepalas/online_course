package kill.me.palas.repositories;

import kill.me.palas.models.Test;
import kill.me.palas.models.TestGrade;
import kill.me.palas.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestGradeRepository extends JpaRepository<TestGrade,Integer> {
    List<TestGrade> findByUser(User user);
    List<TestGrade> findByTest(Test test);
    TestGrade findByUserAndTest(User user, Test test);
}
