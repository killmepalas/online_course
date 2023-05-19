package kill.me.palas.services;

import kill.me.palas.models.*;
import kill.me.palas.repositories.TopicGradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TopicGradeService {

    private final TopicGradeRepository topicGradeRepository;
    private final TopicService topicService;
    private final TestGradeService testGradeService;
    private final TestService testService;
    private final OverCourseService overCourseService;

    @Autowired
    public TopicGradeService(TopicGradeRepository topicGradeRepository, TopicService topicService,
                             @Lazy TestGradeService testGradeService, TestService testService, OverCourseService overCourseService) {
        this.topicGradeRepository = topicGradeRepository;
        this.topicService = topicService;
        this.testGradeService = testGradeService;
        this.testService = testService;
        this.overCourseService = overCourseService;
    }

    public List<TopicGrade> findByUser(User user) {
        return topicGradeRepository.findAllByUser(user);
    }

    public List<TopicGrade> findByUserAndCourse(User user, Course course) {
        List<Topic> topics = topicService.findAllByCourse(course);
        List<TopicGrade> topicGrades = new ArrayList<>();
        for (Topic topic : topics) {
            List<TopicGrade> topicGradeList = topicGradeRepository.findAllByUserAndTopic(user, topic);
            if (!topicGradeList.isEmpty()) topicGrades.addAll(topicGradeList);
        }
        return topicGrades;
    }

    public void save(User user, Topic topic) {
        TopicGrade topicGrade = topicGradeRepository.findByUserAndTopic(user, topic);
        List<TestGrade> testGrades = testGradeService.findActiveByUserAndTopic(user, topic);
        int grades = 0;
        int result;
        for (TestGrade testGrade : testGrades) grades += testGrade.getGrade();
        result = grades / testService.findCountActiveTests(topic);
        if (topicGrade == null) {
            topicGrade = new TopicGrade();
            topicGrade.setTopic(topic);
            topicGrade.setUser(user);
            topicGrade.setGrade(result);
            topicGradeRepository.save(topicGrade);
        } else {
            if (result != topicGrade.getGrade()) {
                topicGrade.setGrade(result);
                topicGradeRepository.save(topicGrade);
            }
        }
    }

    public void updateByTopic(Topic topic) {
        List<TopicGrade> topicGrades = topicGradeRepository.findAllByTopic(topic);
        for (TopicGrade topicGrade : topicGrades) {
            if (overCourseService.findOneByUserAndCourse(topicGrade.getUser(), topic.getCourse()) != null)
                if (overCourseService.isUserOverCourse(topicGrade.getUser(), topic.getCourse()) != 8) {
                    List<TestGrade> testGrades = testGradeService.findByUserAndTopic(topicGrade.getUser(), topic);
                    if (!testGrades.isEmpty()) {
                        int sum = 0;
                        for (TestGrade testGrade : testGrades) sum += testGrade.getGrade();
                        topicGrade.setGrade(sum / testService.findTestByTopic(topic.getId()).size());
                        topicGradeRepository.save(topicGrade);
                    }
                }
        }
    }

}
