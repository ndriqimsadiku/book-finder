package com.bmn.bookfinder.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bmn.bookfinder.adapters.CreateAccountViewPagerAdapter
import com.bmn.bookfinder.databinding.ActivityAccountBinding
import com.bmn.bookfinder.fragments.LoginFragment
import com.bmn.bookfinder.fragments.SignUpFragment

class AccountActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAccountBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val adapter = CreateAccountViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(LoginFragment())
        adapter.addFragment(SignUpFragment())
        binding.createAccountViewpager.adapter = adapter
        binding.tabs.setupWithViewPager(binding.createAccountViewpager)
    }

    override fun onBackPressed() {
        finish()
    }
}