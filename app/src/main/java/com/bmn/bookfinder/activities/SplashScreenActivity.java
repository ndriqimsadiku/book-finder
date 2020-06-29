package com.bmn.bookfinder.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.bmn.bookfinder.R;
import com.bmn.bookfinder.helpers.SharedPrefUtils;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(this::updateUI, 2000);
    }

    private void updateUI() {
        if (SharedPrefUtils.loadFirstTimeUsedTimestamp(getApplicationContext()) != 0L) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        } else {
            startActivity(new Intent(getApplicationContext(), FirstUseSliderActivity.class));
        }
    }
}