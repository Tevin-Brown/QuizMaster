package edu.duke.compsci290.myquizmaster.quizzes;

import android.content.Context;

/**
 * Created by tevin on 2/15/2018.
 */

public interface IQuiz {
    //This interface it to be used for quizzes
    //don't think setContext is necessarry. Score is held by Activity?
    //score maybe should by in quiz only.
    LinearQuestion getQuestion(int id);
    void getQuestions();
    void setContext(Context context);
    void updateScore();
}
