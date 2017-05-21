package com.example.hidai.retrofittest2;

import java.util.List;

/**
 * Created by Hidai on 16/04/2017.
 */

public class Question {
    String question;
    List<Answer> answers;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
}
