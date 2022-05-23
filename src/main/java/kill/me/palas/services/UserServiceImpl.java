package kill.me.palas.services;

import kill.me.palas.models.Course;
import kill.me.palas.models.CourseGrade;
import kill.me.palas.models.User;
import kill.me.palas.models.Role;
import kill.me.palas.repositories.CourseRepository;
import kill.me.palas.repositories.UserRepository;
import kill.me.palas.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService{


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private CourseRepository courseRepository;

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

    public void setRoles(int user, int role){
        User user1 = userRepository.findById(user);
        Set<Role> roles = user1.getRoles();
        roles.add(roleRepository.findById(role));
        user1.setRoles(roles);
        userRepository.save(user1);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findOne(int id) {
        User foundUser = userRepository.findById(id);
        return foundUser;
    }

    public void update(int id, User updatedUser, User oldUser) {
        updatedUser.setId(id);
        if (updatedUser.getPassword() == oldUser.getPassword()){
            updatedUser.setPassword(oldUser.getPassword());
        } else updatedUser.setPassword(bCryptPasswordEncoder.encode(updatedUser.getPassword()));
        updatedUser.setRoles(oldUser.getRoles());
        updatedUser.setCourses(oldUser.getCourses());
        userRepository.save(updatedUser);
    }

    @Override
    public void update(int id, User updatedUser) {
        updatedUser.setId(id);
        userRepository.save(updatedUser);
    }

    public void delete(int id) {
        User user = userRepository.findById(id);
        List<Course> courses = courseRepository.findCourseByTeacher(user);
        for (Course course: courses) {
            course.setTeacher(userRepository.findById(13));
            courseRepository.save(course);
        }
        userRepository.deleteById(id);
    }

    public User findByCourseGrades(List<CourseGrade> courseGrades){
//        User user = userRepository.findByCourseGrades(courseGrades);
        return null;
    }
}