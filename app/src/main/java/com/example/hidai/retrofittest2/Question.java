package com.example.hidai.retrofittest2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hidai on 16/04/2017.
 */

public class Question {
    String question;
    List<Answers> answers;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<Answers> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answers> answers) {
        this.answers = answers;
    }
}
