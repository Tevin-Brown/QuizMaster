package edu.duke.compsci290.myquizmaster.quizzes;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Robert on 2/25/2018.
 */
//Has the question, with the possible answers
public class PersonalityQuestion {
    String mQuestion;
    String[] mAnswers;
    String[] mScores;

    public PersonalityQuestion(String mQuestion, String[] mAnswers, String[] mScores) {
        this.mQuestion = mQuestion;
        this.mAnswers = mAnswers;
        this.mScores = mScores;
    }

    public String getPersonalityQuestion() {
        return mQuestion;
    }

    public String[] getPossibleAnswers() {
        return mAnswers;
    }

    public String[] getScores(){
        return mScores;
    }

    // returns a String array of all the options
    public String[] getOptions() {
        ArrayList<String> options = new ArrayList<String>();
        for(String score : mScores) {
            if(! options.contains(score)){
                options.add(score);
            }
        }
        String[] deliverable = options.toArray(new String[options.size()]);
        return deliverable;
    }


}

