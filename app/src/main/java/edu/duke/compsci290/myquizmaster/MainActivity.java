package edu.duke.compsci290.myquizmaster;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private TextView mTextView;
    private EditText mEditText;
    private String[] quizTypes;
    private Button mButton;
    private Context mContext;
    private String mPrefFileName = "myPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //create and/or grab shared preference file
        final SharedPreferences pref = getApplicationContext().getSharedPreferences(mPrefFileName, 0);


        //initialize view
        this.mTextView = findViewById(R.id.main_title);
        this.mEditText = findViewById(R.id.user_name);
        this.mButton = findViewById(R.id.start);
        this.mContext = this;

        //grab data and set views
        quizTypes = this.getResources().getStringArray(R.array.quiz_names);


        //should not hardcode this statement
        this.mTextView.setText("Welcome to Quiz Master, enter your Username below!");

        //add onclick for button
        //Sends User to next page
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = mEditText.getText().toString();
                Boolean NewUser = checkUsername(username,pref);
                //set up new intent
                Intent intent = new Intent(mContext, ChooseQuizActivity.class);
                intent.putExtra("user_name_key",username);
                intent.putExtra("new_user",NewUser);
                mContext.startActivity(intent);

            }
        });

    }

    public Boolean checkUsername(String username, SharedPreferences pref){
        //check through th preference to see if the user is new or not
        Map<String,?> users = pref.getAll();
        SharedPreferences.Editor editor = pref.edit();
        if (users.get(username) == null){
            editor.putString(username,"hi");
            editor.commit();
            return true;
        }
        return false;
    }


}
