package com.bmn.bookfinder.activities

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.bmn.bookfinder.R
import com.bmn.bookfinder.databinding.ActivityMainBinding
import com.bmn.bookfinder.utils.DimenUtils
import java.util.*

class MainActivity : AppCompatActivity() {
     private lateinit var binding: ActivityMainBinding;
    public override fun onCreate(savedInstanceState: Bundle?) {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.SOFT_INPUT_MASK_ADJUST
        )
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initData()
        DimenUtils.setTopPadding(binding.root)
    }

    private fun initData() {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment?
        NavigationUI.setupWithNavController(
            binding.navigation, Objects.requireNonNull(navHostFragment)!!.navController
        )
    }
}