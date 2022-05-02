package kill.me.palas.services;

import kill.me.palas.models.Role;
import kill.me.palas.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class RoleService {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    public Role findOne(int id) {
        Optional<Role> foundRole = roleRepository.findById(id);
        return foundRole.orElse(null);
    }

    @Transactional
    public void save(Role role) {
        roleRepository.save(role);
    }

    @Transactional
    public void update(int id, Role updatedRole) {
        updatedRole.setId(id);
        roleRepository.save(updatedRole);
    }

    @Transactional
    public void delete(int id) {
        roleRepository.deleteById(id);
    }
}
