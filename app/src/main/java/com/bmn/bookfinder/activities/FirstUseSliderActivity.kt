package com.bmn.bookfinder.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.bmn.bookfinder.R
import com.bmn.bookfinder.adapters.FirstUseViewPagerAdapter
import com.bmn.bookfinder.databinding.ActivityFirstUserSliderBinding
import com.bmn.bookfinder.utils.setBottomPadding
import com.bmn.bookfinder.utils.setTopBottomPadding

class FirstUseSliderActivity : AppCompatActivity(), View.OnClickListener, OnPageChangeListener {
    private lateinit var binding: ActivityFirstUserSliderBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.IntroTheme)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_first_user_slider)
        initData()
    }

    private fun initData() {
        binding.getStartedButton.setOnClickListener(this)
        binding.skipButton.setOnClickListener(this)
        binding.firstUseViewPager.adapter = FirstUseViewPagerAdapter(applicationContext)
        binding.firstUseViewPager.addOnPageChangeListener(this)
        binding.firstUseSliderIndicator.setViewPager(binding.firstUseViewPager)
        binding.root.setTopBottomPadding()
    }

    override fun onClick(v: View) {
        startActivity(Intent(applicationContext, ChooseTopicsActivity::class.java))
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        binding.getStartedButton.visibility =
            if (position == 2) View.VISIBLE else View.INVISIBLE
        binding.firstUseSliderIndicator.visibility =
            if (position == 2) View.INVISIBLE else View.VISIBLE
        if (position == 2) {
            binding.firstUseSliderIndicator.startAnimation(
                AnimationUtils.loadAnimation(
                    this,
                    R.anim.fade_out
                )
            )
        }
    }

    override fun onPageSelected(position: Int) {}
    override fun onPageScrollStateChanged(state: Int) {}
}