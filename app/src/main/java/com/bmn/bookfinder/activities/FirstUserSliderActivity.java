package com.bmn.bookfinder.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.viewpager.widget.ViewPager;

import com.bmn.bookfinder.R;
import com.bmn.bookfinder.adapters.FirstUseViewPagerAdapter;
import com.bmn.bookfinder.databinding.ActivityFirstUserSliderBinding;
import com.bmn.bookfinder.utils.DimenUtils;

public class FirstUserSliderActivity extends AppCompatActivity implements View.OnClickListener, ViewPager.OnPageChangeListener {

    private ActivityFirstUserSliderBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.IntroTheme);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_first_user_slider);
        initData();
    }

    private void initData() {
        binding.getStartedButton.setOnClickListener(this);
        binding.skipButton.setOnClickListener(this);
        binding.firstUseViewPager.setAdapter(new FirstUseViewPagerAdapter(getApplicationContext()));
        binding.firstUseViewPager.addOnPageChangeListener(this);
        binding.firstUseSliderIndicator.setViewPager(binding.firstUseViewPager);
        DimenUtils.setTopBottomPadding(binding.getRoot());
    }


    @Override
    public void onClick(View v) {
        startActivity(new Intent(getApplicationContext(), ChooseTopicsActivity.class));
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        binding.getStartedButton.setVisibility(position == 2 ? View.VISIBLE : View.INVISIBLE);
        binding.firstUseSliderIndicator.setVisibility(position == 2 ? View.INVISIBLE : View.VISIBLE);
        if (position == 2) {
            binding.firstUseSliderIndicator.startAnimation(AnimationUtils.loadAnimation(this, R.anim.fade_out));
        }
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}