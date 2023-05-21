package kill.me.palas.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name= "Category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    private String description;

    @OneToMany(mappedBy="category", cascade = CascadeType.ALL)
    private List<Course> courses;

    @OneToMany(mappedBy="category", cascade = CascadeType.ALL)
    private List<CareerAnswer> answers;

    public Category() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public void setAnswers(List<CareerAnswer> answers) {
        this.answers = answers;
    }

    public List<CareerAnswer> getAnswers() {
        return answers;
    }

    public String getDescription() {

        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
