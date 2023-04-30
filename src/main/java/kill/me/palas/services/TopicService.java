package kill.me.palas.services;

import kill.me.palas.models.Course;
import kill.me.palas.models.Topic;
import kill.me.palas.repositories.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicService {

    private final TopicRepository topicRepository;

    @Autowired
    public TopicService(TopicRepository topicRepository){
        this.topicRepository = topicRepository;
    }

    public Topic findOne(int id){
        return topicRepository.findById(id);
    }

    public List<Topic> findAllByCourse(Course course){
        return topicRepository.findAllByCourse(course);
    }

    public List<Topic> findAllByCourseId(int courseId){
        return topicRepository.findAllByCourseId(courseId);
    }
}
