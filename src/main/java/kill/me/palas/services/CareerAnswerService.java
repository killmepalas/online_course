package kill.me.palas.services;

import kill.me.palas.models.CareerAnswer;
import kill.me.palas.models.CareerQuestion;
import kill.me.palas.repositories.CareerAnswerRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CareerAnswerService {

    private final CareerAnswerRepository careerAnswerRepository;

    public CareerAnswerService(CareerAnswerRepository careerAnswerRepository){
        this.careerAnswerRepository = careerAnswerRepository;
    }

    public List<CareerAnswer> findByQuestion(CareerQuestion careerQuestion){
        List<CareerAnswer> careerAnswers = careerAnswerRepository.findByQuestion(careerQuestion);
        Collections.shuffle(careerAnswers);
        return careerAnswers;
    }

    public CareerAnswer findOne(int id){
        return careerAnswerRepository.findById(id);
    }
}
