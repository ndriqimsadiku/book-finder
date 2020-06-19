package com.bmn.bookfinder.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.bmn.bookfinder.R;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(this::updateUI, 2000);
    }

    private void updateUI() {
        startActivity(new Intent(this, FirstUserSliderActivity.class));
    }
}