package kill.me.palas.repositories;

import kill.me.palas.models.CourseGrade;
import kill.me.palas.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
    User findById(int id);
}
