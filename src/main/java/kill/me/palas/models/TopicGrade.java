package kill.me.palas.models;

import javax.persistence.*;

@Entity
@Table(name="TopicGrade")
public class TopicGrade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="grade")
    private int grade;

    @ManyToOne()
    private Topic topic;

    @ManyToOne()
    private User user;

    public TopicGrade() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
