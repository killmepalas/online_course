package kill.me.palas.models;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "CourseGrade")
public class CourseGrade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Пожалуйста, введите оценку")
    @Min(value = 0, message = "Оценка не должна быть меньше нуля")
    @Column(name="grade")
    private int grade;

    @ManyToOne()
    @JoinColumn(name="course_id")
    private Course course;

    @ManyToOne()
    private User user;

    public CourseGrade(){

    }

    public CourseGrade(int grade) {
        this.grade = grade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
