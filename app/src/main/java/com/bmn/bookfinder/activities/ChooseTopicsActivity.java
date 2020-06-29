package com.bmn.bookfinder.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import com.bmn.bookfinder.R;
import com.bmn.bookfinder.adapters.ChooseTopicsLayoutAdapter;
import com.bmn.bookfinder.databinding.ActivityChooseTopicsBinding;
import com.bmn.bookfinder.dummydata.DummyData;
import com.bmn.bookfinder.helpers.SharedPrefUtils;
import com.bmn.bookfinder.utils.DimenUtils;
import com.google.android.material.snackbar.Snackbar;

public class ChooseTopicsActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityChooseTopicsBinding binding = ActivityChooseTopicsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.topicsGrid.setLayoutManager(new GridLayoutManager(getApplicationContext(), 3));
        DimenUtils.setTopBottomPadding(binding.getRoot());

        ChooseTopicsLayoutAdapter chooseTopicsLayoutAdapter = new ChooseTopicsLayoutAdapter(
                getApplicationContext(), DummyData.getFirstUseTopics()
        );

        binding.topicsGrid.setAdapter(chooseTopicsLayoutAdapter);
        binding.applyTopicsButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (!SharedPrefUtils.getFavoriteIds(getApplicationContext()).isEmpty()) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            SharedPrefUtils.storeFirstTimeUsedTimestamp(getApplicationContext(), System.currentTimeMillis());
        } else {
            Snackbar.make(findViewById(android.R.id.content), R.string.please_choose_topics, Snackbar.LENGTH_SHORT).show();
        }
    }
}