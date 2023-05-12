package kill.me.palas.services;

import kill.me.palas.models.CareerQuestion;
import kill.me.palas.repositories.CareerQuestionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CareerQuestionService {

    private final CareerQuestionRepository careerQuestionRepository;

    public CareerQuestionService(CareerQuestionRepository careerQuestionRepository) {
        this.careerQuestionRepository = careerQuestionRepository;
    }

    public List<CareerQuestion> findAll(){
        return careerQuestionRepository.findAll();
    }

    public CareerQuestion findOne(int id){
        return careerQuestionRepository.findById(id);
    }
}
