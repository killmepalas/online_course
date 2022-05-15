package kill.me.palas.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name="Answer")
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Пожалуйста, введите ответ")
    @Size(min=2, max=200, message = "Ответ не должен быть короче 2 и длиннее 200 символов")
    @Column(name="text")
    private String text;

    @Column(name="is_right")
    private Boolean is_right;

    @ManyToOne()
    private Question question;

    public Answer(){

    }

    public Answer(String text, Boolean is_right) {
        this.text = text;
        this.is_right = is_right;
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

    public Boolean getIs_right() {
        return is_right;
    }

    public void setIs_right(Boolean is_right) {
        this.is_right = is_right;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
}
