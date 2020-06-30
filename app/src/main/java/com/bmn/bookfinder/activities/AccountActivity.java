package com.bmn.bookfinder.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.bmn.bookfinder.adapters.CreateAccountViewPagerAdapter;
import com.bmn.bookfinder.databinding.ActivityAccountBinding;
import com.bmn.bookfinder.fragments.LoginFragment;
import com.bmn.bookfinder.fragments.SignUpFragment;

public class AccountActivity extends AppCompatActivity {

    private ActivityAccountBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAccountBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        CreateAccountViewPagerAdapter adapter = new CreateAccountViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new LoginFragment());
        adapter.addFragment(new SignUpFragment());
        binding.createAccountViewpager.setAdapter(adapter);
        binding.tabs.setupWithViewPager(binding.createAccountViewpager);
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
