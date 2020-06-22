package com.bmn.bookfinder.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;

import com.bmn.bookfinder.R;
import com.bmn.bookfinder.adapters.ChooseTopicsLayoutAdapter;
import com.bmn.bookfinder.databinding.ActivityChooseTopicsBinding;
import com.bmn.bookfinder.models.Topic;
import com.bmn.bookfinder.utils.DimenUtils;

import java.util.ArrayList;
import java.util.List;

public class ChooseTopicsActivity extends AppCompatActivity implements View.OnClickListener {

   private ActivityChooseTopicsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_topics);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_choose_topics);
        binding.topicsGrid.setLayoutManager(new GridLayoutManager(getApplicationContext(), 3));

        DimenUtils.setTopBottomPadding(binding.getRoot());
        List<Topic> topics = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            topics.add(new Topic("Topic", R.drawable.history_topic));
        }
        ChooseTopicsLayoutAdapter chooseTopicsLayoutAdapter = new ChooseTopicsLayoutAdapter(
                getApplicationContext(), topics
        );

        binding.topicsGrid.setAdapter(chooseTopicsLayoutAdapter);
        binding.applyTopicsButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }
}