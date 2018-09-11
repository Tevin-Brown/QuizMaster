package edu.duke.compsci290.myquizmaster.quizzes;

import android.content.Context;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import edu.duke.compsci290.myquizmaster.JSONParser;
import edu.duke.compsci290.myquizmaster.JSONQuizGenerator;

import static android.content.ContentValues.TAG;

/**
 * Created by tevin on 2/15/2018.
 */
//QuestionFactory uses Personality LinearQuestion --> PersonalityQuiz
public class QuestionFactory implements IFactory{
    //This class builds  all the questions and puts them in a map that is key = number, value = question object
    String [] mQuestions;
    Map<Integer,LinearQuestion> mQuestionMap;
    Map<Integer,PersonalityQuestion> myPersonalityQuestionMap;
    Context mContext;

    public QuestionFactory(Context context, String[] questions) {
        this.mQuestions = questions;
        this.mContext = context;
    }

    public QuestionFactory(){}

    @Override
    public Map process(int id) {
        //make more general (Only works for a map/input quiz)
        //maybe change map to array?
        mQuestionMap = new HashMap<Integer, LinearQuestion>();
        int mcount = 0;
        for (String country: mQuestions){
            LinearQuestion quest = new LinearQuestion(country,null,country);
            Log.d(TAG,"idk what's wrong");
            Log.d(TAG,quest.getmCorrectAnswer());
            mQuestionMap.put(mcount,quest);
            mcount++;
        }
        return mQuestionMap;
    }

    public PersonalityQuestion[] processPersonalityJSON(Context mContext,int quizID) {
        JSONQuizGenerator mJSONQuizGenerator = new JSONQuizGenerator(quizID);
        String json = mJSONQuizGenerator.getJSON(mContext);
        Log.d("tag1", "Json: " + json);
        return JSONParser.personalityParse(json);
    }





}
// Have Quetsion factory have processJSON classs --> that gets uses JSONParser