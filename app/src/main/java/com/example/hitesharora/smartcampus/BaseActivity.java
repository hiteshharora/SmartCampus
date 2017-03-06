package com.example.hitesharora.smartcampus;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by hitesharora on 3/4/17.
 */

public class BaseActivity extends AppCompatActivity {


    public void startActivity(Activity activity, Class target){
        Intent intent = new Intent(activity,target);
        startActivity(intent);
    }
}

