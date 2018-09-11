package edu.duke.compsci290.myquizmaster;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import edu.duke.compsci290.myquizmaster.quizzes.PersonalityQuestion;

import static org.junit.Assert.assertEquals;

/**
 * Created by Robert on 3/9/2018.
 */

@RunWith(AndroidJUnit4.class)

// Test for the JSONParser class
public class JSONParserInstrumentedTest {

    @Test
    // tests the personalityParse method
    public void personalityParse_isCorrect() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();
        int mID = R.string.hogwarts;
        JSONQuizGenerator mJSONQuizGenerator = new JSONQuizGenerator(mID);
        String JSONString = mJSONQuizGenerator.getJSON(appContext);
        PersonalityQuestion[] mPersonalityQuestions = JSONParser.personalityParse(JSONString);

        assertEquals("What are you thoughts on snakes?", mPersonalityQuestions[0].getPersonalityQuestion());
        assertEquals("Someone wants to Avada Kedavra your family. What do you do", mPersonalityQuestions[1].getPersonalityQuestion());
        assertEquals("Preferred Pet?", mPersonalityQuestions[2].getPersonalityQuestion());
    }


}