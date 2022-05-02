package kill.me.palas.models;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @NotEmpty(message = "Пожалуйста, введите имя")
    @Size(min = 2, max = 30, message = "Имя не должно быть короче 2 и длиннее 30 символов")
    @Column(name = "name")
    private String name;

    @NotEmpty(message = "Пожалуйста, введите отчество")
    @Size(min = 2, max = 30, message = "Отчество не должно быть короче 2 и длиннее 30 символов")
    @Column(name = "midname")
    private String midname;

    @NotEmpty(message = "Пожалуйста, введите фамилию")
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

    @NotEmpty(message = "Пожалуйста, введите возраст")
    @Min(value = 0, message = "Слишком маленький возраст")
    @Column(name = "age")
    private int age;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Course> courses;

    private int rating;

    public Account() {

    }

    public Account(String login, String password) {
        this.login = login;
        this.password = password;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
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
}
