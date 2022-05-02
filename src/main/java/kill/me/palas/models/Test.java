package kill.me.palas.models;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Entity
@Table(name = "Test")
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Пожалуйста, введите название")
    @Size(min = 2, max = 30, message = "Название не должно быть короче 2 и длиннее 30 символов")
    @Column(name = "name")
    private String name;

    @NotEmpty(message = "Пожалуйста, введите описание")
    @Size(max = 1000, message = "Описание не должно быть длиннее 1000 символов")
    @Column(name = "description")
    private String description;

    @NotEmpty(message = "Пожалуйста, введите дату начала")
    @Column(name = "start")
    @Temporal(TemporalType.TIMESTAMP)
    private Date Start;

    @NotEmpty(message = "Пожалуйста, введите дату окончания")
    @Column(name = "stop")
    @Temporal(TemporalType.TIMESTAMP)
    private Date Stop;

    @ManyToOne()
    @JoinColumn(name = "course_id")
    private Course course;

    @OneToMany()
    private List<Question> questions;

    @OneToMany()
    private List<TestGrade> testGrades;

    public Test(){

    }

    public Test(String name, String description, Date start, Date stop) {
        this.name = name;
        this.description = description;
        Start = start;
        Stop = stop;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStart() {
        return Start;
    }

    public void setStart(Date start) {
        Start = start;
    }

    public Date getStop() {
        return Stop;
    }

    public void setStop(Date stop) {
        Stop = stop;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public List<TestGrade> getTestGrades() {
        return testGrades;
    }

    public void setTestGrades(List<TestGrade> testGrades) {
        this.testGrades = testGrades;
    }
}
