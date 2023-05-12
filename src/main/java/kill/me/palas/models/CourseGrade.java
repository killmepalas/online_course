package kill.me.palas.models;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "coursegrade")
public class CourseGrade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="grade")
    private int grade;

    @ManyToOne()
    private Course course;

    @ManyToOne()
    private User user;

    @Column(name = "final_test")
    private int finalTest;

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

    public int getFinalTest() {
        return finalTest;
    }

    public void setFinalTest(int finalTest) {
        this.finalTest = finalTest;
    }
}
