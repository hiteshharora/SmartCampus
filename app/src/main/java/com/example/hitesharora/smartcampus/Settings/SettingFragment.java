package com.example.hitesharora.smartcampus.Settings;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hitesharora.smartcampus.BaseFragment;
import com.example.hitesharora.smartcampus.Emergency.EmergencyItem;
import com.example.hitesharora.smartcampus.R;
import com.example.hitesharora.smartcampus.adapter.EmergencyAdapter;
import com.example.hitesharora.smartcampus.adapter.VerticalSpaceItemDecoration;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


@SuppressLint("InflateParams")
public class SettingFragment extends BaseFragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, null);

//        TextView text = (TextView) view.findViewById(R.id.settingName);
//        text.setText("Settings");
        return view;
//        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        TextView settingName = (TextView) view.findViewById(R.id.settingName);
////
//        settingName.setText("Notification");


    }
}
