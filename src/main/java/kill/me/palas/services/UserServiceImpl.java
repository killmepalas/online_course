package kill.me.palas.services;

import kill.me.palas.models.User;
import kill.me.palas.models.Role;
import kill.me.palas.repositories.UserRepository;
import kill.me.palas.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService{


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.getOne(1));
        user.setRoles(roles);
        userRepository.save(user);
    }

    public User getCurrentAuthUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return findByUsername(auth.getName());
    }

    public void setRoles(User user, String role){
        Set<Role> roles = user.getRoles();
        roles.add(roleRepository.findRoleByName(role));
        user.setRoles(roles);
        userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findOne(int id) {
        Optional<User> foundUser = userRepository.findById(id);
        return foundUser.orElse(null);
    }

    @Override
    public void update(int id, User updatedUser) {
        updatedUser.setId(id);
        userRepository.save(updatedUser);
    }

    public void delete(int id) {
        userRepository.deleteById(id);
    }

}