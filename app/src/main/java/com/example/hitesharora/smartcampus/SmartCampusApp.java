package com.example.hitesharora.smartcampus;

import android.app.Application;

import com.onesignal.OneSignal;

/**
 * Created by Ravi.Kumar on 4/22/2017.
 */

public class SmartCampusApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        OneSignal.startInit(this).init();
    }
}
