package com.bmn.bookfinder;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bmn.bookfinder.adapters.TopPicksAdapter;
import com.bmn.bookfinder.adapters.TopicsAdapter;
import com.bmn.bookfinder.databinding.FragmentDiscoverBinding;
import com.bmn.bookfinder.models.Topic;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DiscoverFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DiscoverFragment extends Fragment {

    FragmentDiscoverBinding binding;

    private DiscoverFragment() {

    }

    public static DiscoverFragment newInstance() {
        return new DiscoverFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_discover, container, false);
        initData();
        return binding.getRoot();
    }

    private void initData() {
        List<Topic> topics = new ArrayList<>();
        topics.add(new Topic("Add", R.drawable.first_image_topics_list));
        for (int i = 0; i < 40; i++) {
            topics.add(new Topic("Topic", R.drawable.history_topic));
        }
        TopicsAdapter topicsAdapter = new TopicsAdapter(
                getContext(), topics
        );
        binding.topicsRv.setAdapter(topicsAdapter);

        topics.clear();
        for (int i = 0; i < 10; i++) {
            topics.add(new Topic("Lorem ipsum", R.drawable.fatherhood));
        }
        TopPicksAdapter topPicksAdapter = new TopPicksAdapter(
                getContext(), topics
        );
        binding.topPicksRv.setAdapter(topPicksAdapter);
    }
}