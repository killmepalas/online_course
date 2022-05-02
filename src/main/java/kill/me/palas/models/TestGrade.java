package kill.me.palas.models;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="TestGrade")
public class TestGrade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Пожалуйста, введите оценку")
    @Min(value = 0, message = "Оценка не должна быть меньше нуля")
    @Column(name="grade")
    private int grade;

    @ManyToOne()
    @JoinColumn(name="test_id")
    private Test test;

    @ManyToOne()
    @JoinColumn(name="account_id")
    private Account account;

    public TestGrade(){}

    public TestGrade(int grade) {
        this.grade = grade;
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

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
