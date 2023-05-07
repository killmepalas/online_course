package kill.me.palas.models;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "Course")
public class Course {
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

    @Column(name="photo")
    private String photolink;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="teacher_id")
    private User teacher;

    @ManyToMany(mappedBy = "courses")
    private List<User> users;

    @OneToMany(mappedBy="course", cascade = CascadeType.ALL)
    private List<Topic> topics;

    @OneToMany(mappedBy="course", cascade = CascadeType.ALL)
    private List<CourseGrade> courseGrades;

    @ManyToOne()
    @JoinColumn(name = "status_id")
    private Status status;

    @ManyToOne()
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy="course", cascade = CascadeType.ALL)
    private List<OverCourse> overCourses;

    public Course(String name, String description, int price) {
        this.name = name;
        this.description = description;
    }

    public Course(){

    }

    public String getPhotolink() {
        return photolink;
    }

    public void setPhotolink(String photolink) {
        this.photolink = photolink;
    }

    public User getTeacher() {
        return teacher;
    }

    public void setTeacher(User teacher) {
        this.teacher = teacher;
    }

    public List<Topic> getTopics() {
        return topics;
    }

    public void setTopics(List<Topic> topics) {
        this.topics = topics;
    }

    public List<CourseGrade> getCourseGrades() {
        return courseGrades;
    }

    public void setCourseGrades(List<CourseGrade> courseGrades) {
        this.courseGrades = courseGrades;
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

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<OverCourse> getOverCourses() {
        return overCourses;
    }

    public void setOverCourses(List<OverCourse> overCourses) {
        this.overCourses = overCourses;
    }
}
