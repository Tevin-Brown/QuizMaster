package edu.duke.compsci290.myquizmaster;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.Console;

import edu.duke.compsci290.myquizmaster.quizzes.PersonalityQuestion;
import edu.duke.compsci290.myquizmaster.quizzes.PersonalityQuiz;


public class PersonalityQuizActivity extends AppCompatActivity {
    private PersonalityQuiz mPersonalityQuiz;
    private PersonalityQuestion mPersonalityQuestion;
    private String[] mOptions;
    private String[] mScores;
    private String username;
    private String mPersonality;
    private TextView mTextView;
    private int mLength;
    private int mPersonality1;
    private int mPersonality2;
    private int mPersonality3;
    private int mPersonality4;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private int currentIndex;
    private String mquizName;
    private String mQuizType;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personality_quiz);
        View view = this.findViewById(R.id.quiz_main_personality_layout);
        Intent receivedIntent = this.getIntent();
        username = receivedIntent.getStringExtra("user_name_key");
        mquizName = receivedIntent.getStringExtra("quiz_name");
        mQuizType = receivedIntent.getStringExtra("quiz_type");

        //initialize the xml views
        this.button1 = view.findViewById(R.id.Question1);
        this.button2 = view.findViewById(R.id.Question2);
        this.button3 = view.findViewById(R.id.Question3);
        this.button4 = view.findViewById(R.id.Question4);
        this.mTextView = view.findViewById(R.id.personalityquestion);
        currentIndex = 0;

        // Grab JSon String and make quiz
        int quizID = this.getResources().getIdentifier(mquizName.toLowerCase().replaceAll("\\W+", "_"),"string",this.getPackageName());
        mPersonalityQuiz = new PersonalityQuiz(this, 0, quizID);


        //assigning values to the instance variables
        mLength = mPersonalityQuiz.getQuizLength();
        mPersonalityQuestion = mPersonalityQuiz.getPersonalityQuestion(currentIndex);
        mOptions = mPersonalityQuestion.getOptions();

        Continue();
        //the first button is assigned to the first possible answer of the PersonalityQuestion object
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mScores[0].equals(mOptions[0])){
                    mPersonality1++;
                }
                else if(mScores[0].equals(mOptions[1])){
                    mPersonality2++;
                }
                else if(mScores[0].equals(mOptions[2])){
                    mPersonality3++;
                }
                else if(mScores[0].equals(mOptions[3])){
                    mPersonality4++;
                }
                currentIndex++;
                Continue();

            }
        });
        //the second button is assigned to the second possible answer of the PersonalityQuestion object
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mScores[1].equals(mOptions[0])){
                    mPersonality1++;
                }
                else if(mScores[1].equals(mOptions[1])){
                    mPersonality2++;
                }
                else if(mScores[1].equals(mOptions[2])){
                    mPersonality3++;
                }
                else if(mScores[1].equals(mOptions[3])){
                    mPersonality4++;
                }
                currentIndex++;
                Continue();

            }
        });
        //the third button is assigned to the third possible answer of the PersonalityQuestion object
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mScores[2].equals(mOptions[0])){
                    mPersonality1++;
                }
                else if(mScores[2].equals(mOptions[1])){
                    mPersonality2++;
                }
                else if(mScores[2].equals(mOptions[2])){
                    mPersonality3++;
                }
                else if(mScores[2].equals(mOptions[3])){
                    mPersonality4++;
                }
                currentIndex++;
                Continue();

            }
        });

        //the fourth button is assigned to the fourth possible answer of the PersonalityQuestion object

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mScores[3].equals(mOptions[0])){
                    mPersonality1++;
                }
                else if(mScores[3].equals(mOptions[1])){
                    mPersonality2++;
                }
                else if(mScores[3].equals(mOptions[2])){
                    mPersonality3++;
                }
                else if(mScores[3].equals(mOptions[3])){
                    mPersonality4++;
                }
                currentIndex++;
                Continue();

            }
        });


    }

    // The continue class populates every button and TextView w/ the data of the PersonalityQuiz that is assigned to the current Index
    public void Continue(){
        if(currentIndex != mLength) {
            mPersonalityQuestion = mPersonalityQuiz.getPersonalityQuestion(currentIndex);
            mTextView.setText(mPersonalityQuestion.getPersonalityQuestion());
            mScores = mPersonalityQuestion.getScores();
            button1.setText(mPersonalityQuestion.getPossibleAnswers()[0]);
            button2.setText(mPersonalityQuestion.getPossibleAnswers()[1]);
            button3.setText(mPersonalityQuestion.getPossibleAnswers()[2]);
            button4.setText(mPersonalityQuestion.getPossibleAnswers()[3]);
        } else {
            if(mPersonality1 > mPersonality2 || mPersonality1 > mPersonality3 || mPersonality1 > mPersonality4) {
                mPersonality = "You are (a) " + mOptions[0];
            }
            else if(mPersonality2 > mPersonality1 || mPersonality2 > mPersonality3 || mPersonality2 > mPersonality4) {
                mPersonality = "You are (a) " + mOptions[1];
            }
            else if(mPersonality3 > mPersonality1 || mPersonality3 > mPersonality2 || mPersonality3 > mPersonality4) {
                mPersonality = "You are (a) " + mOptions[2];
            }
            else {
                mPersonality = "You are (a) " + mOptions[3];

            }
            endGame();

        }

    }

    // This ends the PersonalityQuiz and calls the EndActivity class
    public void endGame(){
        Intent intent = new Intent(this, EndActivity.class);
        intent.putExtra("user_name_key",username);
        intent.putExtra("final_message",mPersonality);
        intent.putExtra("quiz_type",mQuizType);
        this.startActivity(intent);
    }
}

