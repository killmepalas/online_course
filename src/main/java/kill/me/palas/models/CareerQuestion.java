package kill.me.palas.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Career_guidance_test_question")
public class CareerQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String text;

    @OneToMany(mappedBy="question", cascade = CascadeType.ALL)
    private List<Answer> answers;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public CareerQuestion() {
    }
}
