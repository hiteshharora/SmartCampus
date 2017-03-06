package com.example.hitesharora.smartcampus.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hitesharora.smartcampus.Emergency.EmergencyItem;
import com.example.hitesharora.smartcampus.R;

import java.util.ArrayList;

/**
 * Created by hitesharora on 3/5/17.
 */

public class EmergencyAdapter extends RecyclerView.Adapter<EmergencyAdapter.EmergencyViewHolder> {
    private ArrayList<EmergencyItem> emergency;
    private int rowLayout;
    private Context context;


    public static class EmergencyViewHolder extends RecyclerView.ViewHolder {
        TextView emergencyTitle;
        TextView emergencyDesc;



        public EmergencyViewHolder(View v) {
            super(v);
            emergencyTitle = (TextView) v.findViewById(R.id.emergency_title);
            emergencyDesc = (TextView) v.findViewById(R.id.emergency_desc);

        }
    }

    public EmergencyAdapter(ArrayList<EmergencyItem> emergency, int rowLayout, Context context) {
        this.emergency = emergency;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    public void notifyDataChanged(ArrayList<EmergencyItem> emergency){
        this.emergency = emergency;
        notifyDataSetChanged();
    }

    @Override
    public EmergencyAdapter.EmergencyViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new EmergencyAdapter.EmergencyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(EmergencyAdapter.EmergencyViewHolder holder, final int position) {
        holder.emergencyTitle.setText(emergency.get(position).Title);
        holder.emergencyDesc.setText(emergency.get(position).Description);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("test", "Testing");
            }
        });
    }

    @Override
    public int getItemCount() {
        return emergency.size();
    }
}
