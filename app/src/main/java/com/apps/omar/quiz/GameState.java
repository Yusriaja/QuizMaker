package com.apps.omar.quiz;

import com.apps.omar.quiz.Backend.Answer;
import com.apps.omar.quiz.Backend.Question;
import com.apps.omar.quiz.Backend.Quiz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * Created by omar on 14.12.16.
 */

public class GameState implements Serializable{

    private AnswerHistory  answerHistory = new AnswerHistory();

    private ArrayList<Question> unanswered;

    private Question currentQuestion;
    private boolean answering = false;

    private int correctlyAnswered = 0;

    private Random random = new Random();


    private Quiz quiz;

    public GameState(Quiz quiz)
    {
        this.quiz = quiz;
        unanswered = quiz.getQuestions();
    }

    public AnswerHistory getAnswerHistory() {
        return answerHistory;
    }

    public Question getQuestion()
    {
        if(answering)
        {
            throw new RuntimeException();
        }

        answering = true;


        int position = random.nextInt(unanswered.size());
        currentQuestion = unanswered.get(position);
        unanswered.remove(position);
        return currentQuestion;
    }

    public boolean answerQuestion(Answer answer)
    {
        if(!answering)
        {
            throw new RuntimeException();
        }
        answering = false;

        boolean correct = answer == currentQuestion.getCorrectAnswer();
        answerHistory.addEntry(currentQuestion, correct);

        if(correct)
        {
            correctlyAnswered++;
        }

        return correct;
    }

    public String getScorePercent()
    {
        float score = ((float) (correctlyAnswered) / (float) (answerHistory.getQuestionCount())) * 100;
        String stringScore = Float.toString(score);
        stringScore = stringScore.substring(0, Math.min(5, stringScore.length()));
        stringScore += "%";
        return stringScore;
    }


    public boolean hasQuestion()
    {
        return !unanswered.isEmpty();
    }

    public ArrayList<Answer> getAnswers()
    {
        return currentQuestion.getAnswers();
    }



    public class AnswerHistory implements Serializable, Iterable<AnswerHistory.QuestionCorrectPair>
    {
        private ArrayList<QuestionCorrectPair> history = new ArrayList<>();

        //implemented own pair class because Pair is not serializable
        public class QuestionCorrectPair implements Serializable
        {
            public Question question;
            public boolean correct;

            public QuestionCorrectPair(Question question, boolean correct) {
                this.question = question;
                this.correct = correct;
            }
        }

        @Override
        public Iterator<QuestionCorrectPair> iterator() {
            return history.iterator();
        }

        public void addEntry(Question question, boolean correct)
        {
            history.add(new QuestionCorrectPair(question, correct));
        }

        public int getQuestionCount()
        {
            return history.size();
        }


        public QuestionCorrectPair getEntry(int position)
        {
            return history.get(position);
        }

    }


}
