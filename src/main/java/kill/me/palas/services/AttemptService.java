package kill.me.palas.services;

import kill.me.palas.models.Attempt;
import kill.me.palas.repositories.AttemptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AttemptService {
    private final AttemptRepository attemptRepository;

    @Autowired
    public AttemptService(AttemptRepository attemptRepository) {
        this.attemptRepository = attemptRepository;
    }

    public List<Attempt> findAll() {
        return attemptRepository.findAll();
    }

    public Attempt findOne(int id) {
        Optional<Attempt> foundAttempt = attemptRepository.findById(id);
        return foundAttempt.orElse(null);
    }

    public void save(Attempt attempt) {
        attemptRepository.save(attempt);
    }

    public void update(int id, Attempt updatedAttempt) {
        updatedAttempt.setId(id);
        attemptRepository.save(updatedAttempt);
    }

    public void delete(int id) {
        attemptRepository.deleteById(id);
    }
}
