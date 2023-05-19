package kill.me.palas.services;

import kill.me.palas.config.MailSender;
import kill.me.palas.models.*;
import kill.me.palas.repositories.CourseGradeRepository;
import kill.me.palas.repositories.TestGradeRepository;
import kill.me.palas.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CourseGradeService {
    private final CourseGradeRepository courseGradeRepository;
    private final TopicGradeService topicGradeService;
    private final TopicService topicService;
    private final UserServiceImpl userService;
    private final OverCourseService overCourseService;
    private MailSender mailSender;

    @Autowired
    public CourseGradeService(CourseGradeRepository courseGradeRepository, TopicGradeService topicGradeService,
                              TopicService topicService, UserServiceImpl userService, OverCourseService overCourseService) {
        this.courseGradeRepository = courseGradeRepository;
        this.topicGradeService = topicGradeService;
        this.topicService = topicService;
        this.userService = userService;
        this.overCourseService = overCourseService;
    }

    public List<CourseGrade> findAll() {
        return courseGradeRepository.findAll();
    }

    public CourseGrade findOne(int id) {
        Optional<CourseGrade> foundCourseGrade = courseGradeRepository.findById(id);
        return foundCourseGrade.orElse(null);
    }

    public void save(CourseGrade courseGrade) {
        courseGradeRepository.save(courseGrade);
    }


    public void update(int id, CourseGrade updatedCourseGrade, int grade) {
        updatedCourseGrade.setId(id);
        updatedCourseGrade.setGrade(grade);
        courseGradeRepository.save(updatedCourseGrade);
    }


    public void delete(int id) {
        courseGradeRepository.deleteById(id);
    }

    public CourseGrade findByUserAndCourse(User user, Course course) {
        List<CourseGrade> courseGrades = courseGradeRepository.findByUser(user);
        CourseGrade result = null;
        for (CourseGrade courseGrade : courseGrades) {
            if (courseGrade.getCourse().getId() == course.getId()) result = courseGrade;
        }
        return result;
    }

    public void add(User user, Course course) {
        List<TopicGrade> topicGrades = topicGradeService.findByUserAndCourse(user, course);
        CourseGrade courseGrade = findByUserAndCourse(user, course);
        int result = 0;
        int grades = 0;
        for (TopicGrade topicGrade : topicGrades) {
            grades += topicGrade.getGrade();
        }
        if (courseGrade == null) {
            result = grades / topicService.findAllActiveTopicsByCourseId(course.getId()).size();
            courseGrade = new CourseGrade();
            courseGrade.setCourse(course);
            courseGrade.setUser(user);
            courseGrade.setGrade(result);
            courseGradeRepository.save(courseGrade);
        } else {
            if (result != courseGrade.getGrade()) {
                int count = topicService.findAllActiveTopicsByCourseId(course.getId()).size();
                result = grades / count;
                courseGrade.setGrade(result);
                courseGradeRepository.save(courseGrade);
                if (courseGrade.getFinalTest() >= 50 && courseGrade.getGrade() >= 50 && overCourseService.isUserOverCourse(user,course) != 8){
                    overCourseService.update(overCourseService.findOneByUserAndCourse(user, course), 8);
                    mailSender = new MailSender();
                    mailSender.Send(user.getEmail(), course.getName(), user.getLastname() + " " + user.getName() + " " + user.getMidname());
                }
            }
        }
    }

    public int getRating(User user) {
        int grades = 0;
        List<CourseGrade> courseGrades = courseGradeRepository.findByUser(user);
        List<OverCourse> overCourses = overCourseService.findAllByUser(user);
        for (OverCourse overCourse : overCourses) {
            for (CourseGrade courseGrade : courseGrades) {
                if (courseGrade.getCourse().getId() == overCourse.getCourse().getId())
                    grades += courseGrade.getGrade();
            }
        }
        if (!overCourses.isEmpty())
            return grades / overCourses.size();
        return 0;
    }

    public List<CourseGrade> findAllByUser(User user) {
        return courseGradeRepository.findByUser(user);
    }

    public void updateByCourse(Course course) {
        List<CourseGrade> courseGrades = courseGradeRepository.findByCourse(course);
        for (CourseGrade courseGrade : courseGrades) {
            if (overCourseService.isUserOverCourse(courseGrade.getUser(), course) != 8) {
                List<TopicGrade> topicGrades = topicGradeService.findByUserAndCourse(courseGrade.getUser(), course);
                if (!topicGrades.isEmpty()) {
                    int sum = 0;
                    for (TopicGrade topicGrade : topicGrades) sum += topicGrade.getGrade();
                    courseGrade.setGrade(sum / topicService.findAllByCourse(course).size());
                    courseGradeRepository.save(courseGrade);
                }
            }
        }
    }

    public void saveFinalTesting(Course course, User user, int mark) {
        CourseGrade courseGrade = courseGradeRepository.findByCourseAndUser(course, user);
        courseGrade.setFinalTest(mark);
        courseGradeRepository.save(courseGrade);
    }
}
