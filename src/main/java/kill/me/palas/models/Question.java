package kill.me.palas.models;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "Question")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Вопрос не может быть пустым")
    @Size(min=2,max=200,message = "Вопрос не должен быть короче 2 и длиннее 200 символов")
    @Column(name = "text")
    private String text;

    @ManyToOne()
    private Test test;

    @OneToMany(mappedBy="question", cascade = CascadeType.ALL)
    private List<Answer> answers;

    public Question(){

    }

    public Question(String text) {
        this.text = text;
    }

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

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
}
