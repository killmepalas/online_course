package kill.me.palas.services;

import kill.me.palas.classes.CheckRoles;
import kill.me.palas.models.*;
import kill.me.palas.repositories.CourseRepository;
import kill.me.palas.repositories.StatusRepository;
import kill.me.palas.repositories.UserRepository;
import kill.me.palas.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StatusRepository statusRepository;

    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.getOne(1));
        user.setRoles(roles);
        userRepository.save(user);
    }

    public void block(int userId){
        User user = userRepository.findById(userId);
        user.setStatus(statusRepository.findById(2));
        userRepository.save(user);
    }

    public User getCurrentAuthUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return findByUsername(auth.getName());
    }

    public void modifyRoles(CheckRoles checkRoles, int mode) {
        List<Integer> admins = checkRoles.getAdmRoles();
        List<Integer> teachers = checkRoles.getTeachRoles();
        List<Integer> students = checkRoles.getStuRoles();
        List<List<Integer>> users = new ArrayList<>();

        users.add(admins);
        users.add(teachers);
        users.add(students);

        boolean admin = false;
        boolean teacher = false;
        boolean student = false;

        for (List<Integer> userVar : users) {
            int role;
            if ((!admin)&&(userVar.equals(admins))) {
                role = 3;
                admin = true;
            }
            else if ((!teacher)&&(userVar.equals(teachers))) {
                role = 2;
                teacher = true;
            }
            else {
                role = 1;
                student = true;
            }
            for (Integer var : userVar) {
                User user = userRepository.findById((int) var);
                Set<Role> roles = user.getRoles();
                if (mode == 1) roles.add(roleRepository.findById(role));
                else roles.removeIf(currentElement -> currentElement.getId() == role);

                user.setRoles(roles);
                userRepository.save(user);
            }
        }
    }

    public void setRoles(User user, String role) {
        Set<Role> roles = user.getRoles();
        roles.add(roleRepository.findRoleByName(role));
        user.setRoles(roles);
        userRepository.save(user);
    }

    public void setRoles(int user, int role) {
        User user1 = userRepository.findById(user);
        Set<Role> roles = user1.getRoles();
        roles.add(roleRepository.findById(role));
        user1.setRoles(roles);
        userRepository.save(user1);
    }

    public void deleteRoles(int user, int role) {
        User user1 = userRepository.findById(user);
        Set<Role> roles = user1.getRoles();
        Iterator<Role> setIterator = roles.iterator();
        while (setIterator.hasNext()) {
            Role currentElement = setIterator.next();
            if (currentElement.getName().equals(roleRepository.findById(role).getName())) {
                setIterator.remove();
            }
        }
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

    @Override
    public void update(int id, User updatedUser) {
        updatedUser.setId(id);
        User oldUser = userRepository.findById(id);
        updatedUser.setPassword(oldUser.getPassword());
        updatedUser.setUsername(oldUser.getUsername());
        updatedUser.setRoles(oldUser.getRoles());
        updatedUser.setCourses(oldUser.getCourses());
        userRepository.save(updatedUser);
    }

    public void updateUsername(int id, User updatedUser) {
        User user = userRepository.findById(id);
        user.setUsername(updatedUser.getUsername());
        userRepository.save(user);
    }

    public void updatePassword(int id, User updatedUser) {
        User user = userRepository.findById(id);
        user.setPassword(bCryptPasswordEncoder.encode(updatedUser.getPassword()));
        userRepository.save(user);
    }

    public void delete(int id) {
        User user = userRepository.findById(id);
        List<Course> courses = courseRepository.findCourseByTeacher(user);
        for (Course course : courses) {
            course.setTeacher(userRepository.findById(13));
            courseRepository.save(course);
        }
        userRepository.deleteById(id);
    }

    public List<User> findAllStudents(){
        List<User> users = userRepository.findAll();
        List<User> students = new ArrayList<>();
        for (User user: users){
            for (Role role: user.getRoles()){
                if (role.getId() == 1) students.add(user);
            }
        }
        return students;
    }

}