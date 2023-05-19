package kill.me.palas.services;

import kill.me.palas.models.Lecture;
import kill.me.palas.models.Test;
import kill.me.palas.models.Topic;
import kill.me.palas.repositories.LectureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static kill.me.palas.server.Client.notification;

@Service
public class LectureService {

    private final LectureRepository lectureRepository;
    private final TopicService topicService;

    @Autowired
    public LectureService(LectureRepository lectureRepository, TopicService topicService){
        this.lectureRepository = lectureRepository;
        this.topicService = topicService;
    }

    public List<Lecture> findByTopic(int id){
        return lectureRepository.findAllByTopicId(id);
    }

    public void save(Lecture lecture, int topicId){
        lecture.setTopic(topicService.findOne(topicId));
        lectureRepository.save(lecture);
        updateNotification(lecture,0);
    }

    public Lecture findOne(int id){
        return lectureRepository.findById(id);
    }

    public Topic findTopic(int id){
        return lectureRepository.findById(id).getTopic();
    }

    public void update(int id,Lecture lecture, Topic topic){
        lecture.setId(id);
        lecture.setTopic(topic);
        lectureRepository.save(lecture);
        updateNotification(lecture,1);
    }

    public void delete(int id){
        updateNotification(lectureRepository.findById(id),2);
        lectureRepository.deleteById(id);
    }

    public void updateNotification(Lecture lecture, int action){
        notification(lecture.getTopic().getCourse(), 2,action,lecture.getName());
    }
}
