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

    @Column(name = "mix")
    private boolean mix;

    @Column(name = "count")
    @Min(value = 1,message = "Вопросов должно быть больше 0")
    private int count;

    @ManyToOne()
    @JoinColumn(name = "topic_id")
    private Topic topic;

    @ManyToOne()
    @JoinColumn(name = "status_id")
    private Status status;

    @OneToMany(mappedBy="test", cascade = CascadeType.ALL)
    private List<Question> questions;

    @OneToMany(mappedBy="test", cascade = CascadeType.ALL)
    private List<TestGrade> testGrades;

    public Test(){

    }
    public Test(String name, String description) {
        this.name = name;
        this.description = description;
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

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public boolean isMix() {
        return mix;
    }

    public void setMix(boolean mix) {
        this.mix = mix;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
