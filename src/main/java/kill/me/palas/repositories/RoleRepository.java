package kill.me.palas.repositories;

import kill.me.palas.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findRoleByName(String name);
    Role findById(int id);
}
