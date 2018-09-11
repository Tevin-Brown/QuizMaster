package edu.duke.compsci290.myquizmaster;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import edu.duke.compsci290.myquizmaster.quizzes.PersonalityQuestion;
import edu.duke.compsci290.myquizmaster.quizzes.PersonalityQuiz;
import edu.duke.compsci290.myquizmaster.quizzes.QuestionFactory;

import static org.junit.Assert.assertEquals;

/**
 * Created by Robert on 3/9/2018.
 */

// test for the PersonalityQuiz class

@RunWith(AndroidJUnit4.class)
public class PersonalityQuizInstrumentedTest {
    public int mScore;
    public PersonalityQuestion[] mQuestions;
    public int mID = R.string.hogwarts;

    // we will utilize the hogwarts quiz for the data used in this test


    @Test

    // test fot the getQuizLength method
    public void getQuizLength_isCorrect() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();
        PersonalityQuiz mPersonalityQuiz = new PersonalityQuiz(appContext, 0, mID);
        //quiz length of hogwarts JSON string is 3 -- as you can check in res/values/quiz.xml

        assertEquals(3, mPersonalityQuiz.getQuizLength());
    }
    @Test
    // test for the getPersonalityQuestion method
    public void getPersonalityQuestion_isCorrect() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();
        PersonalityQuiz mPersonalityQuiz = new PersonalityQuiz(appContext, 0, mID);
        QuestionFactory QF = new QuestionFactory();
        mQuestions = QF.processPersonalityJSON(appContext,mID);
        assertEquals(mQuestions[0].getPersonalityQuestion(), mPersonalityQuiz.getPersonalityQuestion(0).getPersonalityQuestion());
    }

    @Test
    // test for the getPersonalityQuestionSSS method
    public void getPersonalityQuestions_isCorrect() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();
        PersonalityQuiz mPersonalityQuiz = new PersonalityQuiz(appContext, 0, mID);


        assertEquals("What are you thoughts on snakes?", mPersonalityQuiz.getPersonalityQuestion(0).getPersonalityQuestion());
        assertEquals("Someone wants to Avada Kedavra your family. What do you do", mPersonalityQuiz.getPersonalityQuestion(1).getPersonalityQuestion());
        assertEquals("Preferred Pet?", mPersonalityQuiz.getPersonalityQuestion(2).getPersonalityQuestion());
    }



}
