package kill.me.palas.models;

import javax.persistence.*;

@Entity
@Table(name = "career_guidance_test_answer")
public class CareerAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String text;

    @ManyToOne()
    private Category category;

    @ManyToOne()
    private CareerQuestion question;

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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public CareerQuestion getQuestion() {
        return question;
    }

    public void setQuestion(CareerQuestion question) {
        this.question = question;
    }

    public CareerAnswer() {
    }
}
