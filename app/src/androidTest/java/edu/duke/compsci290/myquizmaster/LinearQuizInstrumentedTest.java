package edu.duke.compsci290.myquizmaster;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Map;

import edu.duke.compsci290.myquizmaster.quizzes.LinearQuestion;
import edu.duke.compsci290.myquizmaster.quizzes.LinearQuiz;
import edu.duke.compsci290.myquizmaster.quizzes.PersonalityQuiz;

import static org.junit.Assert.assertEquals;

/**
 * Created by Robert on 3/9/2018.
 */
// Test for the LinearQuiz class

@RunWith(AndroidJUnit4.class)
public class LinearQuizInstrumentedTest {
    public Context mContext;
    public Map<Integer,LinearQuestion> mQuestionMap;
    public String[] mQuestions;
    public Integer mScore;


    @Test
    // Test for the getQuizLength method
    public void getQuizLength_isCorrect() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();
        int quizID = appContext.getResources().getIdentifier("north_america","array",appContext.getPackageName());
        mQuestions = appContext.getResources().getStringArray(quizID);
        LinearQuiz mLinearQuiz = new LinearQuiz(appContext, 0, mQuestions);
        //there are three questions for north america quiz, as you can check in the res/values/quiz.xml

        assertEquals(3, mLinearQuiz.getQuizLength());
    }

    @Test
    // test for the getQuestion method
    public void getQuestion_isCorrect() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();
        int quizID = appContext.getResources().getIdentifier("north_america","array",appContext.getPackageName());
        mQuestions = appContext.getResources().getStringArray(quizID);
        LinearQuiz mLinearQuiz = new LinearQuiz(appContext, 0, mQuestions);
        //there are three questions for north america quiz, as you can check in the res/values/quiz.xml
        mLinearQuiz.getQuestions();
        LinearQuestion firstQuestion = mLinearQuiz.mQuestionMap.get(0);
        LinearQuestion secondQuestion = mLinearQuiz.mQuestionMap.get(1);
        LinearQuestion thirdQuestion = mLinearQuiz.mQuestionMap.get(2);

        assertEquals("Canada", firstQuestion.getmCorrectAnswer());
        assertEquals("Costa Rica", secondQuestion.getmCorrectAnswer());
        assertEquals("Belize", thirdQuestion.getmCorrectAnswer());
    }


    @Test
    // test for the setContext method
    public void setContext_isCorrect() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();
        int quizID = appContext.getResources().getIdentifier("north_america","array",appContext.getPackageName());
        mQuestions = appContext.getResources().getStringArray(quizID);
        LinearQuiz mLinearQuiz = new LinearQuiz(appContext, 0, mQuestions);
        mLinearQuiz.setContext(appContext);

        Context contextCheck = mLinearQuiz.mContext;

        assertEquals(appContext, contextCheck);
    }

    @Test
    // test for the updateScore method
    public void updateScore_isCorrect() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();
        int quizID = appContext.getResources().getIdentifier("north_america","array",appContext.getPackageName());
        mQuestions = appContext.getResources().getStringArray(quizID);
        LinearQuiz mLinearQuiz = new LinearQuiz(appContext, 0, mQuestions);
        mLinearQuiz.updateScore();
        int newScore = mLinearQuiz.mScore;
        assertEquals(1, newScore);
    }


}
