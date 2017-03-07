package com.example.hitesharora.smartcampus.adapter;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hitesharora.smartcampus.Emergency.EmergencyItem;
import com.example.hitesharora.smartcampus.MainActivity;
import com.example.hitesharora.smartcampus.R;

import java.util.ArrayList;

/**
 * Created by hitesharora on 3/5/17.
 */

public class EmergencyAdapter extends RecyclerView.Adapter<EmergencyAdapter.EmergencyViewHolder> {
    private ArrayList<EmergencyItem> emergency;
    private int rowLayout;
    private Context context;
    private final int CALL_PHONE = 1;


    public EmergencyAdapter(Context context) {
        this.context = context;
    }

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

    public void notifyDataChanged(ArrayList<EmergencyItem> emergency) {
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
                Log.d("On adapter click", emergency.get(position).Description);
                //Log.d("test", "Testing...");
                placeACall(emergency.get(position).Description);
            }
        });
    }


    public void placeACall(String number) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        String uri = "tel:" + number.trim();
        intent.setData(Uri.parse(uri));
        Log.d("On click", uri);

        if ( ActivityCompat.checkSelfPermission(this.context, Manifest.permission.CALL_PHONE ) != PackageManager.PERMISSION_GRANTED ) {

            ActivityCompat.requestPermissions((MainActivity) this.context, new String[] {  Manifest.permission.CALL_PHONE  },
                    CALL_PHONE );
        } else {
            Log.d("here", "failed");
        }

        //callIntent.setData(Uri.parse("tel:"+number));
//        if (ActivityCompat.checkSelfPermission(this.context, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
//            this.context.startActivity(intent);
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//               public void onRequestPermissionsResult(int requestCode, String[] permissions,
//                                                      int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//            return;
//        }
//        this.context.startActivity(intent);
    }

    public void sendEmail(String emailID){
        
    }


    @Override
    public int getItemCount() {
        return emergency.size();
    }
}
