package edu.duke.compsci290.myquizmaster.quizzes;

import android.content.Context;

import java.util.Map;

/**
 * Created by tevin on 2/15/2018.
 */

public class LinearQuiz implements IQuiz {
    public Context mContext;
    public Map<Integer,LinearQuestion> mQuestionMap;
    public String[] mQuestions;
    public Integer mScore;

    public LinearQuiz(Context mContext, Integer mScore, String[] questions) {
        this.mScore = mScore;
        this.mQuestions=questions;
        getQuestions();
    }

    public int getQuizLength(){
        return this.mQuestionMap.size();
    }



    @Override
    public LinearQuestion getQuestion(int id) {
        return mQuestionMap.get(id);
    }

    @Override
    public void getQuestions() {
        QuestionFactory QF = new QuestionFactory(mContext,mQuestions);
        this.mQuestionMap = QF.process(0);
    }

    @Override
    public void setContext(Context context) {
        this.mContext = context;
    }

    @Override
    public void updateScore() {
        mScore++;
    }



}
