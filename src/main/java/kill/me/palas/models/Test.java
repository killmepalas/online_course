package kill.me.palas.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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
    @Size(min = 2, max = 50, message = "Название не должно быть короче 2 и длиннее 50 символов")
    @Column(name = "name")
    private String name;

    @Size(max = 1000, message = "Описание не должно быть длиннее 1000 символов")
    @Column(name = "description")
    private String description;

    @NotNull
    @Column(name = "start")
    @DateTimeFormat(iso= DateTimeFormat.ISO.DATE)
    @Temporal(TemporalType.TIMESTAMP)
    private Date start;

    @Column(name = "stop")
    @NotNull
    @DateTimeFormat(iso= DateTimeFormat.ISO.DATE)
    @Temporal(TemporalType.TIMESTAMP)
    private Date stop;

    @ManyToOne()
    @JoinColumn(name = "course_id")
    private Course course;

    @OneToMany(mappedBy="test", cascade = CascadeType.ALL)
    private List<Question> questions;

    @OneToMany(mappedBy="test", cascade = CascadeType.ALL)
    private List<TestGrade> testGrades;

    public Test(){

    }
    public Test(String name, String description, Date start, Date stop) {
        this.name = name;
        this.description = description;
        this.start = start;
        this.stop = stop;
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
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getStop() {
        return stop;
    }

    public void setStop(Date stop) {
        this.stop = stop;
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
