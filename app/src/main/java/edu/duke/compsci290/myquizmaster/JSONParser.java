package edu.duke.compsci290.myquizmaster;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import edu.duke.compsci290.myquizmaster.quizzes.PersonalityQuestion;
import edu.duke.compsci290.myquizmaster.quizzes.PersonalityQuiz;

/**
 * Created by Robert on 2/25/2018.
 */

public class JSONParser {

    // using the input jsonstring, the personalityParse class returns a PersonalityQuestion array with all the components parsed.
    public static PersonalityQuestion[] personalityParse(String jString){
        try {
            JSONObject all = new JSONObject(jString);
            String qtitle = all.getString("title");
            JSONArray array = all.getJSONArray("questions");
            PersonalityQuestion[] questions = new PersonalityQuestion[array.length()];
            Log.d("quizmaster", "created json object");
            for(int k=0; k < array.length(); k++){
                JSONObject current = array.getJSONObject(k);
                String pQuestion = current.getString("pquestion");
                JSONArray options = current.getJSONArray("options");
                String[] optionAnswers = new String[options.length()];
                for(int j=0; j < optionAnswers.length; j++){
                    optionAnswers[j] = options.getString(j);
                }
                Log.d("quizmaster", "filled options");

                JSONArray scores = current.getJSONArray("scores");
                String[] scoreAnswers = new String[scores.length()];
                for(int j=0; j < scoreAnswers.length; j++){
                    scoreAnswers[j] = scores.getString(j);
                }
                Log.d("quizmaster", "filled scores");

                PersonalityQuestion q = new PersonalityQuestion(pQuestion,optionAnswers,scoreAnswers);
                questions[k] = q;
            }
            return questions;
        } catch (JSONException e) {
            Log.d("json parse","error in parsing");
            e.printStackTrace();
        }
        return null;
    }
}

