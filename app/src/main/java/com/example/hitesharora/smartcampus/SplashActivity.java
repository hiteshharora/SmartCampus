package com.example.hitesharora.smartcampus;

import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;

/**
 */

public class SplashActivity extends BaseActivity {

    final int mTimeInMillis = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(SplashActivity.this,MainActivity.class);
                finish();
            }
        },mTimeInMillis);
    }
}
