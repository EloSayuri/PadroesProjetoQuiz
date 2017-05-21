package com.example.hidai.retrofittest2;

/**
 * Created by Hidai on 09/05/2017.
 */

public class Answer extends Question {

    String answer = "";
    Boolean correct = false;

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Boolean getCorrect() {
        return correct;
    }

    public void setCorrect(Boolean correct) {
        this.correct = correct;
    }
}
