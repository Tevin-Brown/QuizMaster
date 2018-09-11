package edu.duke.compsci290.myquizmaster;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by tevin on 2/13/2018.
 */

public class QuizCategoryAdapter extends RecyclerView.Adapter<QuizCategoryAdapter.ViewHolder> {
    private Context mContext;
    private SharedPreferences mPref;
    private SharedPreferences mScorePref;
    private String[] mQuizTypes;
    private String mUserName;
    private String[] mStatus;
    private String[] mScore;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mQuizType;
        public TextView mStatusText;
        public TextView mScoreText;
        public Button mChangeStatus;

        public ViewHolder(View itemView) {
            super(itemView);
            this.mQuizType = itemView.findViewById(R.id.quiz_type);
            this.mStatusText = itemView.findViewById(R.id.completion_status);
            this.mChangeStatus = itemView.findViewById(R.id.change_status);
            this.mScoreText = itemView.findViewById(R.id.score_board);
        }
    }

    public QuizCategoryAdapter(Context context, String[] quizTypes, String username, String[] Status, String[] score, SharedPreferences pref, SharedPreferences scorepref) {
        this.mContext = context;
        this.mQuizTypes = quizTypes;
        this.mUserName = username;
        this.mStatus = Status;
        this.mPref = pref;
        this.mScore = score;
        this.mScorePref = scorepref;
    }



    @Override
    public int getItemCount(){
        return this.mQuizTypes.length;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        //create inflater and viewholder
        LayoutInflater mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = mInflater.inflate(R.layout.quiztype_viewer, parent, false);
        final ViewHolder QuiztypeHolder = new ViewHolder(row);
        return QuiztypeHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mQuizType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //goes to the quiz
                //use try-catch to be cautious of errors
                Log.d("tag1", mStatus[position]);
                if (checkComplete(position) == 0){
                    Toast toast = Toast.makeText(mContext, "Must Change Status to Take Quiz", Toast.LENGTH_LONG);
                    toast.show();
                }
                if (checkComplete(position) != 0){
                    try {
                        startGame(mUserName,position);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        holder.mQuizType.setText(mQuizTypes[position]);
        if (checkComplete(position) == 0){
            holder.mQuizType.setTextColor(Color.RED);
        }
        if (checkComplete(position) == 1){
            holder.mQuizType.setTextColor(Color.BLUE);
        }
        if (checkComplete(position) == 2){
            holder.mQuizType.setTextColor(Color.GREEN);
        }
        holder.mStatusText.setText(mStatus[position]);
        holder.mScoreText.setText(mScore[position]);
        holder.mChangeStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //changes the shared preference to recognize the updated status
                updatePref(mPref,mScorePref,position);
                holder.mQuizType.setTextColor(Color.GREEN);
                holder.mStatusText.setText(mStatus[position]);
                holder.mScoreText.setText(mScore[position]);
            }
        });

    }


    private int checkComplete(int pos) {
        if (mStatus[pos].equals("Complete")){
            return 0;
        }
        if(mStatus[pos].equals("Incomplete")){
            return 2;
        }
        return 1;
    }

    private void updatePref(SharedPreferences statusPref, SharedPreferences scorePref,int position) {
        // update the shared preferences
        SharedPreferences.Editor editor = statusPref.edit();
        SharedPreferences.Editor scoreeditor = scorePref.edit();
        if (mStatus[position] != "Incomplete"){
            editor.putString(mQuizTypes[position],"Incomplete");
            mStatus[position] = "Incomplete";
            scoreeditor.putString(mQuizTypes[position],"0/0");
            mScore[position] = "0/0";
        }
        editor.commit();
        scoreeditor.commit();
    }

    public void startGame(String username,int pos) throws ClassNotFoundException {
        //changes to the new activity
        // get the correct strings
        String beg = parseQuizNameBeginning(mQuizTypes[pos]);
        String end = parseQuizNameEnd(mQuizTypes[pos]);
        //How to references a class without explicitly stating it
        Class quiz = Class.forName("edu.duke.compsci290.myquizmaster."+ end.replaceAll("\\W+", "")+"Activity");

        //set up new intent
        Intent intent = new Intent(mContext, quiz);
        intent.putExtra("user_name_key",username);
        intent.putExtra("mScore",0);
        intent.putExtra("question_number",0);
        intent.putExtra("quiz_name",beg);
        intent.putExtra("quiz_type", mQuizTypes[pos]);
        mContext.startActivity(intent);
    }

    private String parseQuizNameEnd(String quizname){
        //get the end of the name (type of quiz)
        int pos = quizname.indexOf("(");
        String end = quizname.substring(pos+1, quizname.length()-1);
        return end;
    }

    private String parseQuizNameBeginning(String quizname){
        //get the beginning of the name (continent)
        int pos = quizname.indexOf("(");
        String beg = quizname.substring(0, pos-1);
        return beg;
    }
}
