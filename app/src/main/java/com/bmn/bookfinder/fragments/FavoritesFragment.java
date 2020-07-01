package com.bmn.bookfinder.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.bmn.bookfinder.adapters.FavoriteTopicsAdapter;
import com.bmn.bookfinder.data.room.AppDatabase;
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
        FavoritesFragmentDirections.ActionFavoritesFragmentToTopicFragment action = FavoritesFragmentDirections.actionFavoritesFragmentToTopicFragment();

        FavoriteTopicsAdapter adapter = new FavoriteTopicsAdapter(book -> {
            action.setTopicId((int)book.getTopicId());
            action.setTopicTitle(book.getTopic());
            Navigation.findNavController(binding.getRoot()).navigate(action);
        });

        adapter.submitList(AppDatabase.getDatabase(getContext()).getBookDao().getFavoriteTopics());
        binding.favoritesList.setAdapter(adapter);

        return binding.getRoot();
    }

    private void init() {
        topics = new ArrayList<>();
    }
}