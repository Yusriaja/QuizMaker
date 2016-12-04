package com.apps.omar.quiz.Backend;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by omar on 01.12.16.
 */

public class Quiz implements Serializable {
    private String quizName = null;
    private String quizDescription = null;
    private ArrayList<Question> questions = new ArrayList<>();

    public String getQuizName() {
        return quizName;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public void setQuizName(String quizName) {
        this.quizName = quizName;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }

    public void addQuestion(Question question)
    {
        questions.add(question);

    }

    public String getQuizDescription() {
        return quizDescription;
    }

    public void setQuizDescription(String quizDescription) {
        this.quizDescription = quizDescription;
    }
}
