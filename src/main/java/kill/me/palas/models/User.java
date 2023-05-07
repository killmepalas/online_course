package kill.me.palas.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name = "firstname")
    private String name;

    @Column(name = "midname")
    private String midname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "username")
    private String username;

    @Transient
    private String confirmPassword;

    @Transient
    private String oldPassword;

    @Column(name = "password")
    private String password;

    @Column(name = "date_of_birth")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(iso= DateTimeFormat.ISO.DATE)
    private Date dateOfBirth;

    @Column(name="photo")
    private String photolink;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="user_role", joinColumns = @JoinColumn(name="users_id"),
    inverseJoinColumns = @JoinColumn(name="roles_id"))
    private Set<Role> roles;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name="user_course", joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name="course_id"))
    private List<Course> courses;

    @OneToMany(mappedBy="user",cascade = CascadeType.ALL)
    private List<TestGrade> testGrades;

    @OneToMany(mappedBy="user", cascade = CascadeType.ALL)
    private List<CourseGrade> courseGrades;

    @OneToMany(mappedBy="user", cascade = CascadeType.ALL)
    private List<TopicGrade> topicGrades;

    @OneToMany(mappedBy="user", cascade = CascadeType.ALL)
    private List<OverCourse> overCourses;

    @Column(name = "rating")
    private int rating;

    @ManyToOne()
    @JoinColumn(name = "status_id")
    private Status status;

    @Column(name = "email")
    private String email;

    public User() {

    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getPhotolink() {
        return photolink;
    }

    public void setPhotolink(String photolink) {
        this.photolink = photolink;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
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

    public String getMidname() {
        return midname;
    }

    public void setMidname(String midname) {
        this.midname = midname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public List<TestGrade> getTestGrades() {
        return testGrades;
    }

    public void setTestGrades(List<TestGrade> testGrades) {
        this.testGrades = testGrades;
    }

    public List<CourseGrade> getCourseGrades() {
        return courseGrades;
    }

    public void setCourseGrades(List<CourseGrade> courseGrades) {
        this.courseGrades = courseGrades;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public List<TopicGrade> getTopicGrades() {
        return topicGrades;
    }

    public void setTopicGrades(List<TopicGrade> topicGrades) {
        this.topicGrades = topicGrades;
    }

    public List<OverCourse> getOverCourses() {
        return overCourses;
    }

    public void setOverCourses(List<OverCourse> overCourses) {
        this.overCourses = overCourses;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
