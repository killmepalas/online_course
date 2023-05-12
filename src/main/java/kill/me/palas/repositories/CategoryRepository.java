package kill.me.palas.repositories;

import kill.me.palas.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Category findById(int id);
    Category findByName(String name);
}
