package edu.duke.compsci290.myquizmaster;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertEquals;

/**
 * Created by Robert on 3/9/2018.
 */

// test for the JSONQuizGenerator class
@RunWith(AndroidJUnit4.class)
public class JSONQuizGeneratorInstrumentedTest {

    @Test
    // test for the getJSON method
    public void getJSON_isCorrect() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();
        int mID = R.string.hogwarts;
        JSONQuizGenerator mJSONQuizGenerator = new JSONQuizGenerator(mID);
        String JSONString = mJSONQuizGenerator.getJSON(appContext);
        String testerString = appContext.getString(mID);
        assertEquals(testerString, JSONString);
    }


}