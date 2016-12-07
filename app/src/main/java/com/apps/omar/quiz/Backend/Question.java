package com.apps.omar.quiz.Backend;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by omar on 01.12.16.
 */

public class Question implements Serializable {
    private String question;
    private ArrayList<Answer> answers;
    private Answer correctAnswer = null;

    public Question() {

    }

    public Question(String question, ArrayList<Answer> answers) {
        for (Answer answer : answers) {
            if (answer.isCorrect()) {
                this.correctAnswer = answer;
            }
        }

        if (correctAnswer == null) {
            throw new RuntimeException("Provided answers don't contain correct option!");
        }
        this.question = question;
        this.answers = answers;
    }

    public String getQuestion() {
        return question;
    }

    public ArrayList<Answer> getAnswers() {
        return answers;
    }

    public Answer getCorrectAnswer() {
        return correctAnswer;
    }

}
