package kill.me.palas.services;

import com.google.protobuf.CodedInputStream;
import kill.me.palas.models.Course;
import kill.me.palas.models.OverCourse;
import kill.me.palas.models.User;
import kill.me.palas.repositories.OverCourseRepository;
import kill.me.palas.repositories.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OverCourseService {

    private final OverCourseRepository overCourseRepository;
    private final StatusRepository statusRepository;

    @Autowired
    public OverCourseService(OverCourseRepository overCourseRepository, StatusRepository statusRepository){
        this.overCourseRepository = overCourseRepository;
        this.statusRepository = statusRepository;
    }

    public List<OverCourse> findAllByCourse(Course course){
        return overCourseRepository.findByCourse(course);
    }

    public List<OverCourse> findAllByUser(User user){
        return overCourseRepository.findByUser(user);
    }

    public OverCourse findOneByUserAndCourse(User user, Course course){
        return overCourseRepository.findByUserAndCourse(user, course);
    }

    public void save(User user, Course course, int statusId){
        OverCourse overCourse = new OverCourse(statusRepository.findById(statusId),user,course);
        overCourseRepository.save(overCourse);
    }

    public void update(OverCourse overCourse, int statusId){
        overCourse.setStatus(statusRepository.findById(statusId));
        overCourseRepository.save(overCourse);
    }

    public void delete(OverCourse overCourse){
        overCourseRepository.delete(overCourse);
    }

    public List<User> findAllUsersByOver(Course course, int status){
        List<OverCourse> overCourses = overCourseRepository.findAllByCourseAndStatusId(course,status);
        List<User> users = new ArrayList<>();
        for (OverCourse overCourse: overCourses){
            users.add(overCourse.getUser());
        }
        return users;
    }

    public int isUserOverCourse(User user, Course course){
        return overCourseRepository.findByUserAndCourse(user, course).getStatus().getId();
    }
}
