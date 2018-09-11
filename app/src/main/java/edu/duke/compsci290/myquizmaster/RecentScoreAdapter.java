package edu.duke.compsci290.myquizmaster;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by tevin on 3/8/2018.
 */

public class RecentScoreAdapter extends RecyclerView.Adapter<RecentScoreAdapter.ViewHolder> {
    private Map<String,?> mMap;
    private Object[] mMapKeys;
    private Context mContext;

    public RecentScoreAdapter(Context context, Map<String,?> map){
        //map is a map of time information to quiz end message
        this.mMap = map;
        this.mMapKeys = map.keySet().toArray();
        this.mContext = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mDate;
        public TextView mScore;

        public ViewHolder(View itemView) {
            super(itemView);
            this.mDate = itemView.findViewById(R.id.date);
            this.mScore = itemView.findViewById(R.id.score_message);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //create inflater and viewholder
        LayoutInflater mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = mInflater.inflate(R.layout.recentscore_viewer, parent, false);
        final RecentScoreAdapter.ViewHolder recentScore = new ViewHolder(row);
        return recentScore;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //Set the two text views with the date and time of quiz & message saved
        holder.mDate.setText(mMapKeys[position].toString());
        holder.mScore.setText(mMap.get(mMapKeys[position]).toString());
    }


    @Override
    public int getItemCount() {
        return mMapKeys.length;
    }
}
