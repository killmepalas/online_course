package kill.me.palas.services;

import kill.me.palas.models.Lecture;
import kill.me.palas.repositories.LectureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LectureService {

    private final LectureRepository lectureRepository;

    @Autowired
    public LectureService(LectureRepository lectureRepository){
        this.lectureRepository = lectureRepository;
    }

    public List<Lecture> findByTopic(int id){
        return lectureRepository.findAllByTopicId(id);
    }
}
