package edu.duke.compsci290.myquizmaster.quizzes;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Robert on 2/25/2018.
 */
// LinearQuestion Factory

public class PersonalityQuiz {
    public int mScore;
    public PersonalityQuestion[] mQuestions;
    public Context mContext;
    public int mID;

    //class to create the PersonalityQuiz object
    public PersonalityQuiz(Context context, int score, int ID) {
        this.mScore = score;
        this.mContext = context;
        this.mID = ID;
        getPersonalityQuestions();
    }
    // returns the amount of questions within the PersonalityQuiz object
    public int getQuizLength(){
        return mQuestions.length;
    }

    // returns the PersonalityQuestion at a specific index
    public PersonalityQuestion getPersonalityQuestion(int id) {
        return mQuestions[id];
    }

    // populates the PersonalityQuiz with PersonalityQuestions using QuestionFactory class
    private void getPersonalityQuestions() {
        QuestionFactory QF = new QuestionFactory();
        this.mQuestions = QF.processPersonalityJSON(mContext,mID);
    }


}
