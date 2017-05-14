package com.example.hidai.retrofittest2;

import java.util.List;

/**
 * Created by Hidai on 03/05/2017.
 */

public class ExamTest {
    String userName;
    String examTitle;
    String author;
    String description;
    String title;
    List<Question> questions;


    public ExamTest(){

    }

    public ExamTest(String userName, String examTitle) {
        this.userName = userName;
        this.examTitle = examTitle;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getExamTitle() {
        return examTitle;
    }

    public void setExamTitle(String examTitle) {
        this.examTitle = examTitle;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
