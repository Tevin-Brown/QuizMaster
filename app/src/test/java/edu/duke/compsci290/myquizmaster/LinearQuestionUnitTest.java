package edu.duke.compsci290.myquizmaster;

import org.junit.Test;

import edu.duke.compsci290.myquizmaster.quizzes.LinearQuestion;
import edu.duke.compsci290.myquizmaster.quizzes.PersonalityQuestion;

import static org.junit.Assert.assertEquals;

/**
 * Created by Robert on 3/9/2018.
 */
// Test for the LinearQuestion class
public class LinearQuestionUnitTest {
    String mStatement;
    String [] possibleAnswers = new String[4];;
    String mCorrectAnswer;

    @Test

    // test for the getmStatement method
    public void getmStatement_isCorrect() throws Exception {
        mStatement = "Testing at 4:30pm before the deadline is beautifulness";
        LinearQuestion mLinearQuestion = new LinearQuestion(mStatement, possibleAnswers, mCorrectAnswer);
        assertEquals(mStatement, mLinearQuestion.getmStatement());

    }

    @Test
    // test for the getPossibleAnswers method
    public void getPossibleAnswers_isCorrect() throws Exception {
        possibleAnswers[0] = "zero";
        possibleAnswers[1] = "one";
        possibleAnswers[2] = "two";
        possibleAnswers[3] = "three";
        LinearQuestion mLinearQuestion = new LinearQuestion(mStatement, possibleAnswers, mCorrectAnswer);
        assertEquals(possibleAnswers[0], mLinearQuestion.getPossibleAnswers()[0]);
        assertEquals(possibleAnswers[1], mLinearQuestion.getPossibleAnswers()[1]);
        assertEquals(possibleAnswers[2], mLinearQuestion.getPossibleAnswers()[2]);
        assertEquals(possibleAnswers[3], mLinearQuestion.getPossibleAnswers()[3]);

    }

    @Test
    // test for the getmCorrectAnswer method
    public void getmCorrectAnswer_isCorrect() throws Exception {
        mCorrectAnswer = "Testing at 4:30pm before the deadline is beautifulness";
        LinearQuestion mLinearQuestion = new LinearQuestion(mStatement, possibleAnswers, mCorrectAnswer);
        assertEquals(mCorrectAnswer, mLinearQuestion.getmCorrectAnswer());

    }

}
