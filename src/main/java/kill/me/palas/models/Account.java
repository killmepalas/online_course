package kill.me.palas.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Size(min = 2, max = 30, message = "Имя не должно быть короче 2 и длиннее 30 символов")
    @Column(name = "name")
    private String name;

    @Size(min = 2, max = 30, message = "Отчество не должно быть короче 2 и длиннее 30 символов")
    @Column(name = "midname")
    private String midname;

    @Size(min = 2, max = 30, message = "Фамилия не должна быть короче 2 и длиннее 30 символов")
    @Column(name = "lastname")
    private String lastname;

    @NotEmpty(message = "Пожалуйста, введите логин")
    @Size(min = 2, max = 30, message = "Логин не должна быть короче 2 и длиннее 30 символов")
    @Column(name = "login")
    private String login;

    @NotEmpty(message = "Пожалуйста, введите пароль")
    @Size(min = 6, max = 30, message = "Фамилия не должна быть короче 6 и длиннее 30 символов")
    @Column(name = "password")
    private String password;

    @Column(name = "date_of_birth")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy") // дд/мм/гггг
    private Date dateOfBirth;

    @Column(name="photo")
    private String photolink;

    @ManyToMany()
    private Set<Role> roles;

    @ManyToMany()
    private List<Course> courses;

    @OneToMany()
    private List<TestGrade> testGrades;

    @OneToMany()
    private List<CourseGrade> courseGrades;

    @Column(name="rating")
    private int rating;

    public Account() {

    }

    public Account(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getPhotolink() {
        return photolink;
    }

    public void setPhotolink(String photolink) {
        this.photolink = photolink;
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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
}
