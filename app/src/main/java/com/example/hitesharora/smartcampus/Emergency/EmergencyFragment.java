package com.example.hitesharora.smartcampus.Emergency;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.hitesharora.smartcampus.BaseFragment;
import com.example.hitesharora.smartcampus.R;
import com.example.hitesharora.smartcampus.adapter.EmergencyAdapter;
import com.example.hitesharora.smartcampus.adapter.NewsAdapter;
import com.example.hitesharora.smartcampus.adapter.VerticalSpaceItemDecoration;

import java.util.ArrayList;
import java.util.List;


@SuppressLint("InflateParams")
public class EmergencyFragment extends BaseFragment {

    public ArrayList<EmergencyItem> mEmergencyItemsList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_emergency, null);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mEmergencyItemsList = new ArrayList<>();
        for (int i = 0;i<2;i++){
            EmergencyItem itemObj1 = new EmergencyItem();
            itemObj1.Title = "Emergency";
            itemObj1.Description = "911";
            mEmergencyItemsList.add(itemObj1);
            EmergencyItem itemObj2 = new EmergencyItem();
            itemObj2.Title = "Jonesboro Police (non-emergency)";
            itemObj2.Description = "(870)935-5553";
            mEmergencyItemsList.add(itemObj2);
            EmergencyItem itemObj3 = new EmergencyItem();
            itemObj3.Title = "University Police";
            itemObj3.Description = "(870)972-2093";
            mEmergencyItemsList.add(itemObj3);
            EmergencyItem itemObj4 = new EmergencyItem();
            itemObj4.Title = "Jonesboro Police (non-emergency)";
            itemObj4.Description = "(870)972-2093";
            mEmergencyItemsList.add(itemObj4);
            EmergencyItem itemObj5 = new EmergencyItem();
            itemObj5.Title = "Safety Concern Email";
            itemObj5.Description = "safe@astate.edu";
            mEmergencyItemsList.add(itemObj5);
        }


        final RecyclerView recyclerView = (RecyclerView) getView().findViewById(R.id.list_emergency);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        final EmergencyAdapter emergencyAdapter = new EmergencyAdapter(mEmergencyItemsList, R.layout.emergency_feed, this.getActivity());
        recyclerView.addItemDecoration(new VerticalSpaceItemDecoration(getActivity()));
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(emergencyAdapter);

//        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
//            @Override
//            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
//
//                return false;
//            }
//
//            @Override
//            public void onTouchEvent(RecyclerView rv, MotionEvent e) {
//                Log.d("On click","On Item Touch - Emergency Fragment");
//            }
//
//            @Override
//            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
//
//            }
//        });
//        recyclerView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d("On click","Emergency Fragment");
//            }
//        });





        List<String> emergencyArrayList = new ArrayList<String>();
        emergencyArrayList.add("Emergency");
        emergencyArrayList.add("Jonesboro Police (non-emergency)");
        emergencyArrayList.add("University Police");
        emergencyArrayList.add("Jonesboro Police (non-emergency)");
        emergencyArrayList.add("Safety Concern Email");
        emergencyArrayList.add("Ways to report incidents");

    }
}
