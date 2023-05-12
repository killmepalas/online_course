package kill.me.palas.services;

import kill.me.palas.models.Course;
import kill.me.palas.models.Question;
import kill.me.palas.models.Topic;
import kill.me.palas.repositories.StatusRepository;
import kill.me.palas.repositories.TopicGradeRepository;
import kill.me.palas.repositories.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class TopicService {

    private final TopicRepository topicRepository;
    private final StatusRepository statusRepository;
    private final CourseGradeService courseGradeService;

    @Autowired
    public TopicService(TopicRepository topicRepository, StatusRepository statusRepository,
                        @Lazy CourseGradeService courseGradeService){
        this.topicRepository = topicRepository;
        this.statusRepository = statusRepository;
        this.courseGradeService = courseGradeService;
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

    public List<Topic> findAllActiveTopicsByCourseId(int courseId){
        return topicRepository.findAllByCourseIdAndStatusId(courseId,1);
    }

    public void changeStatus(int topicId, int statusId){
        Topic topic = topicRepository.findById(topicId);
        topic.setStatus(statusRepository.findById(statusId));
        topicRepository.save(topic);
    }

    public void save(Topic topic, Course course){
        topic.setCourse(course);
        topic.setStatus(statusRepository.findById(3));
        topicRepository.save(topic);
        courseGradeService.updateByCourse(topic.getCourse());
    }

    public Course findCourse(int id){
        return topicRepository.findById(id).getCourse();
    }

    public void update(int id, Topic newTopic,Course course){
        Topic oldTopic = topicRepository.findById(id);
        newTopic.setStatus(oldTopic.getStatus());
        newTopic.setCourse(oldTopic.getCourse());
        newTopic.setId(oldTopic.getId());
        topicRepository.save(newTopic);
    }

    public void delete(int id){
        courseGradeService.updateByCourse(topicRepository.findById(id).getCourse());
        topicRepository.deleteById(id);
    }

    public Topic findUncheckedTopicOfCourse(List<Topic> topicChecks,Course course){
        List<Topic> allTopics = topicRepository.findAllByCourse(course);
        Collections.shuffle(allTopics);
        for (Topic topic: allTopics){
            boolean flag = true;
            for (Topic t: topicChecks){
                if (topic.getId() == t.getId()) {
                    flag = false;
                    break;
                }
            }
            if (flag) return topic;
        }
        return null;
    }
}
