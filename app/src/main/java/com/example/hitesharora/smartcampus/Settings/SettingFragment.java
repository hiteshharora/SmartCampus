package com.example.hitesharora.smartcampus.Settings;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hitesharora.smartcampus.BaseFragment;
import com.example.hitesharora.smartcampus.R;


@SuppressLint("InflateParams")
public class SettingFragment extends BaseFragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_emergency, null);
        return view;

    }
}
