package kill.me.palas.repositories;

import kill.me.palas.models.Course;
import kill.me.palas.models.Test;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestRepository extends JpaRepository<Test,Integer> {
    List<Test> findTestByCourse(Course course);
    List<Test> findTestByName(String name);
    Test findById(int id);
}
