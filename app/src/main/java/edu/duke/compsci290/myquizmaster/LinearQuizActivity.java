package edu.duke.compsci290.myquizmaster;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import edu.duke.compsci290.myquizmaster.quizzes.LinearQuestion;
import edu.duke.compsci290.myquizmaster.quizzes.LinearQuiz;

public class LinearQuizActivity extends AppCompatActivity {
    private int mCurrent;
    private String mUsername;
    private int mScore;
    private int mCount;
    private String[] mQuestions;
    private String mQuizName;
    private LinearQuestion mQuest;
    private LinearQuiz mQuiz;
    private String mQuizType;
    private ImageView mImageView;
    private TextView mTextView;
    private TextView mScoreTextview;
    private EditText mEditText;
    private Button mAnswerButton;
    private SharedPreferences mStatusPref;
    private SharedPreferences mSavedPref;
    private String mStatusSuffix = "_quizStatus";
    private String mMidQuiz = "_midquiz";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear_quiz);
        View view = this.findViewById(R.id.quiz_main_linear_layout);

        //Grab intent information
        Intent receivedIntent = this.getIntent();
        mUsername = receivedIntent.getStringExtra("user_name_key");
        mScore = receivedIntent.getIntExtra("mScore", 0);
        mCount = receivedIntent.getIntExtra("question_number", 0);
        mQuizName = receivedIntent.getStringExtra("quiz_name");
        mQuizType = receivedIntent.getStringExtra("quiz_type");

        //initialize views & grab info
        int quizID = this.getResources().getIdentifier(mQuizName.toLowerCase().replaceAll("\\W+", "_"),"array",this.getPackageName());
        //Log.d(TAG, "location:" + mQuizName.toLowerCase().replaceAll("\\W+", "_"));
        mQuestions = this.getResources().getStringArray(quizID);
        this.mImageView = view.findViewById(R.id.answer_image);
        this.mTextView = view.findViewById(R.id.generic_question);
        this.mEditText = view.findViewById(R.id.user_input);
        this.mScoreTextview = view.findViewById(R.id.score);
        this.mAnswerButton = view.findViewById(R.id.question_button);

        //Grab data from prefs
        mCurrent = 0;
        mSavedPref = getApplicationContext().getSharedPreferences(mUsername+ mMidQuiz, 0);
        String current = mSavedPref.getString(mQuizType,"");
        Log.d("tag1", "Your state: "+ current);
        if (!current.equals("")){
            Log.d("tag1", "Your state: "+ current);
            String[] temp = current.split("/");
            mCurrent = Integer.parseInt(temp[1]);
            mCount = mCurrent;
            Log.d("tag1", "Your current: "+ mCurrent);
            mScore = Integer.parseInt(temp[0]);
            Log.d("tag1", "Your score: "+ mScore);
        }


        //create a linearQuiz Object
        mQuiz = new LinearQuiz(this, mScore,mQuestions);
        mQuest = mQuiz.getQuestion(mCurrent);

        //set up page
        String mDescription = this.getResources().getString(R.string.Description);
        mTextView.setText(mDescription);
        mScoreTextview.setText(mUsername + "'s score is : " + mScore + "/" + mCount);
        int imageID= this.getResources().getIdentifier(mQuest.getmCorrectAnswer().toLowerCase().replaceAll("\\W+", ""),"drawable",this.getPackageName());
        Drawable countryImage = this.getDrawable(imageID);
        mImageView.setImageDrawable(countryImage);

        //set on click listeners
        mAnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //They have submitted their answer
                String input = mEditText.getText().toString();

                //Used to check data
                //Log.d(TAG, "You guessed: "+ input);
                //Log.d(TAG, "Answer: "+ mQuest.getmCorrectAnswer() );

                //check if input is correct
                if(input.equals(mQuest.getmCorrectAnswer())){ mScore++;}
                mCount ++;
                if (mCount == mQuiz.getQuizLength()){endGame();}
                else{Continue();}
            }
        });
    }



    @Override
    public void onStop(){
        super.onStop();
        //On Stop, save the score and position. Update the status pref to In Progress
        mSavedPref = getApplicationContext().getSharedPreferences(mUsername+ mMidQuiz, 0);
        SharedPreferences.Editor save = mSavedPref.edit();
        if (mCount != mQuiz.getQuizLength()){
            mStatusPref = getApplicationContext().getSharedPreferences(mUsername+ mStatusSuffix, 0);
            SharedPreferences.Editor statuseditor = mStatusPref.edit();
            statuseditor.putString(mQuizType,"In Progress");
            statuseditor.commit();
            save.putString(mQuizType,mScore+"/"+mCurrent);
            save.commit();
        }
        else{
            save.putString(mQuizType,"");
            save.commit();
        }
    }



    public void endGame(){
        //goes to the end game page
        Intent intent = new Intent(this, EndActivity.class);
        intent.putExtra("user_name_key", mUsername);
        intent.putExtra("final_message","Your score is: " + mScore + "/" + mCount);
        intent.putExtra("quiz_type",mQuizType);
        this.startActivity(intent);
    }

    public void Continue(){
        //Updates the image and score
        mCurrent++;
        mQuest = mQuiz.getQuestion(mCurrent);
        int imageID= this.getResources().getIdentifier(mQuest.getmCorrectAnswer().toLowerCase().replaceAll("\\W+", ""),"drawable",this.getPackageName());
        Drawable countryImage = this.getDrawable(imageID);
        mImageView.setImageDrawable(countryImage);
        mScoreTextview.setText(mUsername + "'s score is : " + mScore + "/" + mCount);
        mEditText.setText("");
    }
}
