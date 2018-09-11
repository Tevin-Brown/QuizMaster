package edu.duke.compsci290.myquizmaster;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

public class EndActivity extends AppCompatActivity {
    private TextView congrats;
    private TextView score;
    private String username;
    private String mMessage;
    private Button mChangeUser;
    private Button mNewQuiz;
    private Context mContext;
    private String mQuizType;
    private String mStatusSuffix = "_quizStatus";
    private String mScoreSuffix = "_quizScore";
    private String mRecentSuffix ="_recentScores";
    private SharedPreferences mPref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //View shows the user that they have finished the game, and what their score was
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);
        Intent receivedIntent = this.getIntent();
        username = receivedIntent.getStringExtra("user_name_key");
        mMessage = receivedIntent.getExtras().getString("final_message");
        mQuizType = receivedIntent.getStringExtra("quiz_type");
        congrats = findViewById(R.id.congrats);
        score = findViewById(R.id.score2);
        congrats.setText("Congratulations " + username + "!");
        score.setText(mMessage);
        mContext = this;

        //update status shared preference to complete
        mPref = getApplicationContext().getSharedPreferences(username+mStatusSuffix, 0);
        SharedPreferences.Editor statuseditor = mPref.edit();
        statuseditor.putString(mQuizType,"Complete");
        statuseditor.commit();


        //update Score shared Prefernce to mMessage
        mPref = getApplicationContext().getSharedPreferences(username+mScoreSuffix, 0);
        SharedPreferences.Editor scoreeditor = mPref.edit();
        scoreeditor.putString(mQuizType,mMessage);
        scoreeditor.commit();

        //Save the score in recent preferences for the quiz
        mPref = getApplicationContext().getSharedPreferences(username+"_"+mQuizType+mRecentSuffix,0);
        SharedPreferences.Editor recenteditor = mPref.edit();
        String currentTime = Calendar.getInstance().getTime().toString();
        recenteditor.putString(currentTime,mMessage);
        recenteditor.commit();
        final Map<String,?> recentScores= mPref.getAll();


        //set up recycler view
        RecyclerView rv = findViewById(R.id.recent_score);
        rv.setAdapter(new RecentScoreAdapter(this, recentScores));
        rv.setLayoutManager(new LinearLayoutManager(this));

        //handle buttons
        mChangeUser = findViewById(R.id.new_user);
        mNewQuiz = findViewById(R.id.new_quiz);
        mChangeUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //set up new intent
                Intent intent = new Intent(mContext, MainActivity.class);
                intent.putExtra("user_name_key",username);
                mContext.startActivity(intent);

            }
        });
        mNewQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //set up new intent
                Intent intent = new Intent(mContext, ChooseQuizActivity.class);
                intent.putExtra("user_name_key",username);
                mContext.startActivity(intent);

            }
        });

    }
}
