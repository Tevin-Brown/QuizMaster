package edu.duke.compsci290.myquizmaster;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.Map;

public class ChooseQuizActivity extends AppCompatActivity {
    private String[] mQuizTypes;
    private String[] mStatus;
    private String[] mScore;
    private String muserName;
    private TextView mTextView;
    private SharedPreferences mStatusPref;
    private SharedPreferences mScorePref;
    private String mStatusSuffix = "_quizStatus";
    private String mScoreSuffix = "_quizScore";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_quiz);

        //Grab intent information
        Intent receivedIntent = this.getIntent();
        muserName = receivedIntent.getStringExtra("user_name_key");
        mQuizTypes = this.getResources().getStringArray(R.array.quiz_names);
        mStatus = new String[mQuizTypes.length];
        mScore = new String[mQuizTypes.length];
        boolean newuser = receivedIntent.getBooleanExtra("new_user",false);

        //create and/or grab shared preference file
        mStatusPref = getApplicationContext().getSharedPreferences(muserName+ mStatusSuffix, 0);
        mScorePref = getApplicationContext().getSharedPreferences(muserName+ mScoreSuffix, 0);
        updatePrefs(mStatusPref,mScorePref);

        //Set up text view
        mTextView = findViewById(R.id.User_choose);
        String message = "";
        if (!newuser){message = "Welcome back " + muserName + ", take another quiz!";}
        if (newuser){message = "Welcome " + muserName + ", choose a quiz!";}
        mTextView.setText(message);

        //set up recycler view
        RecyclerView rv = findViewById(R.id.choose_quiz_activity_recycler_view);
        rv.setAdapter(new QuizCategoryAdapter(this, mQuizTypes,muserName,mStatus, mScore,mStatusPref,mScorePref));
        rv.setLayoutManager(new LinearLayoutManager(this));
    }

    private void updatePrefs(SharedPreferences status, SharedPreferences score) {
        SharedPreferences.Editor statuseditor = status.edit();
        SharedPreferences.Editor scoreeditor = score.edit();
        Map<String,?> mypref = status.getAll();
        Map<String,?> mypref2 = score.getAll();
        if (mypref.size() == 0){
            // if you are a new user, initialize status
            for (int i = 0; i < mQuizTypes.length; i++){
                statuseditor.putString(mQuizTypes[i], "Incomplete");
                scoreeditor.putString(mQuizTypes[i], "0/0");
                mScore[i] = "0/0";
                mStatus[i]= "Incomplete";

            }
            statuseditor.commit();
            scoreeditor.commit();
        }
        if (mypref.size() == mQuizTypes.length){
            //if you are a returning user update status
            for(int i = 0; i < mQuizTypes.length; i++){
                mStatus[i] = mypref.get(mQuizTypes[i]).toString();
                mScore[i] = mypref2.get(mQuizTypes[i]).toString();
            }
            statuseditor.commit();
            scoreeditor.commit();
        }
        if (mypref.size() != mQuizTypes.length){
            // if new quizzes were added, initialize status
            for (int i = 0; i < mQuizTypes.length; i++){
                if (mypref.get(mQuizTypes[i]) == null){
                    statuseditor.putString(mQuizTypes[i], "Incomplete");
                    mStatus[i] = "Incomplete";
                    scoreeditor.putString(mQuizTypes[i], "0/0");
                    mScore[i] = "0/0";
                }
                else {
                    mStatus[i] = mypref.get(mQuizTypes[i]).toString();
                    mScore[i] = mypref2.get(mQuizTypes[i]).toString();
                }
            }
            statuseditor.commit();
            scoreeditor.commit();
        }
    }
}
