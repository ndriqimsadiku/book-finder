package com.bmn.bookfinder.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.bmn.bookfinder.R
import com.bmn.bookfinder.helpers.SharedPrefUtils

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        Handler().postDelayed({ updateUI() }, 2000)
    }

    private fun updateUI() {
        if (SharedPrefUtils.loadFirstTimeUsedTimestamp(applicationContext) != 0L) {
            startActivity(Intent(applicationContext, MainActivity::class.java))
        } else {
            startActivity(Intent(applicationContext, FirstUseSliderActivity::class.java))
        }
    }
}