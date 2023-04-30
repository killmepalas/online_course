package kill.me.palas.repositories;

import kill.me.palas.models.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status, Integer> {
    Status findById(int id);
}
