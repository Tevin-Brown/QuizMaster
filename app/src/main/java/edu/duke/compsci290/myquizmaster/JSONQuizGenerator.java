package edu.duke.compsci290.myquizmaster;
import android.content.Context;

import java.net.MalformedURLException;


/**
 * Created by Robert on 2/26/2018.
 */

// the JSONQuizGenerator fetches the JSON string within the res/values using the resourceID.
public class JSONQuizGenerator {
    private int mQuizID;
    private static String sTAG = "JSONCODE";
    // this is to capture the resourceID of the JSONString
    public JSONQuizGenerator(int quizID) {
        mQuizID = quizID;
    }
    // this gets the actual json string which will be fed into the JSONParser class
    public String getJSON(final Context quizMain){
        Context c = quizMain.getApplicationContext();
        return c.getResources().getString(mQuizID);
    }

}
