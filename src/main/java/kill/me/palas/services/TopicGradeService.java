package kill.me.palas.services;

import kill.me.palas.models.*;
import kill.me.palas.repositories.TopicGradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TopicGradeService {

    private final TopicGradeRepository topicGradeRepository;
    private final TopicService topicService;

    @Autowired
    public TopicGradeService(TopicGradeRepository topicGradeRepository, TopicService topicService){
        this.topicGradeRepository = topicGradeRepository;
        this.topicService = topicService;
    }

    public List<TopicGrade> findByUser(User user){
        return topicGradeRepository.findAllByUser(user);
    }

    public List<TopicGrade> findByUserAndCourse(User user, Course course){
        List<Topic> topics = topicService.findAllByCourse(course);
        List<TopicGrade> topicGrades = new ArrayList<>();
        for(Topic topic: topics){
            List<TopicGrade> topicGradeList = topicGradeRepository.findAllByUserAndAndTopic(user,topic);
            if (!topicGradeList.isEmpty()) topicGrades.addAll(topicGradeList);
        }
        return topicGrades;
    }
}
