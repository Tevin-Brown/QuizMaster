package edu.duke.compsci290.myquizmaster.quizzes;

import java.util.Map;

/**
 * Created by tevin on 2/15/2018.
 */

public interface IFactory {
    //May change map to iterable (what about adaptive quizzes?)
    Map process(int ID);
}
