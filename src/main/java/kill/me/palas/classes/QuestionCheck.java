package kill.me.palas.classes;

import kill.me.palas.models.Question;

public class QuestionCheck {
    private Question question;
    private boolean present;

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public boolean isPresent() {
        return present;
    }

    public void setPresent(boolean present) {
        this.present = present;
    }

    public QuestionCheck(){}
}
