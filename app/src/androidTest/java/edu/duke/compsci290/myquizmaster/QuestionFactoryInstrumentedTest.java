package edu.duke.compsci290.myquizmaster;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;
import java.util.Map;

import edu.duke.compsci290.myquizmaster.quizzes.LinearQuestion;
import edu.duke.compsci290.myquizmaster.quizzes.PersonalityQuestion;
import edu.duke.compsci290.myquizmaster.quizzes.PersonalityQuiz;
import edu.duke.compsci290.myquizmaster.quizzes.QuestionFactory;

import static org.junit.Assert.assertEquals;

/**
 * Created by Robert on 3/9/2018.
 */

// test for the QuestionFactory class
@RunWith(AndroidJUnit4.class)
public class QuestionFactoryInstrumentedTest {

    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("edu.duke.compsci290.myquizmaster", appContext.getPackageName());
    }


    @Test

    // test for the process method
    public void process_isCorrect() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();
        int quizID = appContext.getResources().getIdentifier("north_america","array",appContext.getPackageName());
        String[] mQuestions = appContext.getResources().getStringArray(quizID);
        QuestionFactory mQuestionFactory = new QuestionFactory(appContext, mQuestions);
        Map<Integer,LinearQuestion> mQuestionMap = new HashMap<Integer, LinearQuestion>();
        mQuestionMap =  mQuestionFactory.process(0);

        LinearQuestion firstQuestion = mQuestionMap.get(0);
        LinearQuestion secondQuestion = mQuestionMap.get(1);
        LinearQuestion thirdQuestion = mQuestionMap.get(2);

        assertEquals("Canada", firstQuestion.getmCorrectAnswer());
        assertEquals("Costa Rica", secondQuestion.getmCorrectAnswer());
        assertEquals("Belize", thirdQuestion.getmCorrectAnswer());
    }

    @Test

    // test for the processPersonalityJSON method
    public void processPersonalityJSON_isCorrect() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();
        int mID = R.string.hogwarts;
        QuestionFactory mQuestionFactory = new QuestionFactory();
        PersonalityQuestion[] mPersonalityQuestions =  mQuestionFactory.processPersonalityJSON(appContext, mID);

        assertEquals("What are you thoughts on snakes?", mPersonalityQuestions[0].getPersonalityQuestion());
        assertEquals("Someone wants to Avada Kedavra your family. What do you do", mPersonalityQuestions[1].getPersonalityQuestion());
        assertEquals("Preferred Pet?", mPersonalityQuestions[2].getPersonalityQuestion());
    }

}
