package kill.me.palas.repositories;

import kill.me.palas.models.Test;
import kill.me.palas.models.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestRepository extends JpaRepository<Test,Integer> {
    List<Test> findTestByTopic(Topic topic);
    List<Test> findTestByName(String name);
    Test findById(int id);
}
