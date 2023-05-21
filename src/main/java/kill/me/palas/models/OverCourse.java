package kill.me.palas.models;

import javax.persistence.*;

@Entity
@Table(name = "Over_course")
public class OverCourse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne()
    @JoinColumn(name = "status_id")
    private Status status;

    @ManyToOne()
    private User user;

    @ManyToOne()
    private Course course;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public OverCourse() {
    }

    public OverCourse(Status status, User user, Course course) {
        this.status = status;
        this.user = user;
        this.course = course;
    }
}
