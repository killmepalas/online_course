package kill.me.palas.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "Topic")
public class Topic {

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

    @ManyToOne()
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne()
    @JoinColumn(name = "status_id")
    private Status status;

    @OneToMany(mappedBy="topic", cascade = CascadeType.ALL)
    private List<Test> tests;

    @OneToMany(mappedBy="topic", cascade = CascadeType.ALL)
    private List<Lecture> lectures;

    @OneToMany(mappedBy="topic", cascade = CascadeType.ALL)
    private List<TopicGrade> topicGrades;

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

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Test> getTests() {
        return tests;
    }

    public void setTests(List<Test> tests) {
        this.tests = tests;
    }

    public List<Lecture> getLectures() {
        return lectures;
    }

    public void setLectures(List<Lecture> lectures) {
        this.lectures = lectures;
    }

    public List<TopicGrade> getTopicGrades() {
        return topicGrades;
    }

    public void setTopicGrades(List<TopicGrade> topicGrades) {
        this.topicGrades = topicGrades;
    }

    public Topic() {
    }
}
