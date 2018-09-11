package edu.duke.compsci290.myquizmaster.quizzes;

/**
 * Created by tevin on 2/15/2018.
 */

public class LinearQuestion {
    // Creates a question object that has a Statement,correct answers, and possible answers
    String mStatement;
    String [] possibleAnswers;
    String mCorrectAnswer;

    public LinearQuestion(String mStatement, String[] possibleAnswers, String mCorrectAnswer) {
        this.mStatement = mStatement;
        this.possibleAnswers = possibleAnswers;
        this.mCorrectAnswer = mCorrectAnswer;
    }

    public String getmStatement() {
        //used for quizzes with a unique heading statement
        //not necessarily unique (should use for the map-input quiz as well)
        return mStatement;
    }

    public String[] getPossibleAnswers() {
        //used for quizzes with multiple choices
        return possibleAnswers;
    }

    public String getmCorrectAnswer() {
        //used with quizzes with one correct answer
        return mCorrectAnswer;
    }
}
