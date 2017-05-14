package com.example.hidai.retrofittest2;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Hidai on 29/03/2017.
 */

public class Exam {


    private String title;
    private String type;
    private String description;
    private String author;
    private List<Question> questions = new LinkedList<Question>();

    /*public Exam(String title, String type, String description, String author, List<Question> questions) {
        this.title = title;
        this.type = type;
        this.description = description;
        this.author = author;
        this.questions = questions;
    }*/

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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



}
