package edu.duke.compsci290.myquizmaster;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import edu.duke.compsci290.myquizmaster.quizzes.PersonalityQuestion;

import static org.junit.Assert.assertEquals;

/**
 * Created by Robert on 3/7/2018.
 */

// Test for the PersonalityQuestion class

public class PersonalityQuestionUnitTest {
    String mQuestion = "Testing is such an interesting process, don't you think";
    String[] mAnswers = new String[4];
    String[] mScores = new String[4];

    @Test
    // Test for the getPersonalityQuestion method
    public void getPersonalityQuestion_isCorrect() throws Exception {
        PersonalityQuestion mPersonalityQuestion = new PersonalityQuestion(mQuestion,mAnswers,mScores);
        assertEquals(mQuestion, mPersonalityQuestion.getPersonalityQuestion());
    }

    @Test
    // Test for the getPossibleAnswers method
    public void getPossibleAnswers_isCorrect() throws Exception {
        mAnswers[0] = "zero";
        mAnswers[1] = "one";
        mAnswers[2] = "two";
        mAnswers[3] = "three";
        PersonalityQuestion mPersonalityQuestion = new PersonalityQuestion(mQuestion,mAnswers,mScores);

        assertEquals(mAnswers[0], mPersonalityQuestion.getPossibleAnswers()[0]);
        assertEquals(mAnswers[1], mPersonalityQuestion.getPossibleAnswers()[1]);
        assertEquals(mAnswers[2], mPersonalityQuestion.getPossibleAnswers()[2]);
        assertEquals(mAnswers[3], mPersonalityQuestion.getPossibleAnswers()[3]);

    }

    @Test
    // Test for the getScores method
    public void getScores_isCorrect() throws Exception {
        mScores[0] = "zero";
        mScores[1] = "un";
        mScores[2] = "deux";
        mScores[3] = "trois";
        PersonalityQuestion mPersonalityQuestion = new PersonalityQuestion(mQuestion,mAnswers,mScores);

        assertEquals(mScores[0], mPersonalityQuestion.getScores()[0]);
        assertEquals(mScores[1], mPersonalityQuestion.getScores()[1]);
        assertEquals(mScores[2], mPersonalityQuestion.getScores()[2]);
        assertEquals(mScores[3], mPersonalityQuestion.getScores()[3]);

    }

    @Test
    // Test for the getOptions method
    public void getOptions_isCorrect() throws Exception {
        mScores[0] = "THIS";
        mScores[1] = "WILL";
        mScores[2] = "WORK";
        mScores[3] = "NOW";
        PersonalityQuestion mPersonalityQuestion = new PersonalityQuestion(mQuestion,mAnswers,mScores);
        String[] returnedArray = mPersonalityQuestion.getOptions();
        List<String> returnedList = Arrays.asList(returnedArray);
        assertEquals(true, returnedList.contains(mScores[0]));
        assertEquals(true, returnedList.contains(mScores[1]));
        assertEquals(true, returnedList.contains(mScores[2]));
        assertEquals(true, returnedList.contains(mScores[3]));

    }


}
