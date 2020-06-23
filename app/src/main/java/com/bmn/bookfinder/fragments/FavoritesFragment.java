package com.bmn.bookfinder.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.bmn.bookfinder.adapters.FavoriteTopicsAdapter;
import com.bmn.bookfinder.databinding.FragmentFavoritesBinding;
import com.bmn.bookfinder.dummydata.DummyData;
import com.bmn.bookfinder.models.Topic;

import java.util.ArrayList;

public class FavoritesFragment extends Fragment {

    private ArrayList<Topic> topics;

    public FavoritesFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentFavoritesBinding binding = FragmentFavoritesBinding.inflate(getLayoutInflater());

        init();

        topics = DummyData.getDummyTopics();

        FavoriteTopicsAdapter adapter = new FavoriteTopicsAdapter(topic -> {
            //todo open activity for books of clicked topic
        });
        adapter.submitList(topics);
        binding.favoritesList.setAdapter(adapter);

        return binding.getRoot();
    }

    private void init() {
        topics = new ArrayList<>();
    }
}