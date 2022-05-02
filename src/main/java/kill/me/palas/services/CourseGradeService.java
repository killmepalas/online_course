package kill.me.palas.services;

import kill.me.palas.models.CourseGrade;
import kill.me.palas.repositories.CourseGradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class CourseGradeService {
    private final CourseGradeRepository courseGradeRepository;

    @Autowired
    public CourseGradeService(CourseGradeRepository courseGradeRepository) {
        this.courseGradeRepository = courseGradeRepository;
    }

    public List<CourseGrade> findAll() {
        return courseGradeRepository.findAll();
    }

    public CourseGrade findOne(int id) {
        Optional<CourseGrade> foundCourseGrade = courseGradeRepository.findById(id);
        return foundCourseGrade.orElse(null);
    }

    @Transactional
    public void save(CourseGrade courseGrade) {
        courseGradeRepository.save(courseGrade);
    }

    @Transactional
    public void update(int id, CourseGrade updatedCourseGrade) {
        updatedCourseGrade.setId(id);
        courseGradeRepository.save(updatedCourseGrade);
    }

    @Transactional
    public void delete(int id) {
        courseGradeRepository.deleteById(id);
    }
}
