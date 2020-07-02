package com.bmn.bookfinder.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.bmn.bookfinder.R;
import com.bmn.bookfinder.adapters.BestShareListAdapter;
import com.bmn.bookfinder.adapters.TopPicksAdapter;
import com.bmn.bookfinder.adapters.TopicsAdapter;
import com.bmn.bookfinder.data.room.AppDatabase;
import com.bmn.bookfinder.data.room.BookEntity;
import com.bmn.bookfinder.databinding.FragmentDiscoverBinding;
import com.bmn.bookfinder.dummydata.DummyData;
import com.bmn.bookfinder.helpers.SharedPrefUtils;
import com.bmn.bookfinder.models.TopPick;
import com.bmn.bookfinder.models.Topic;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DiscoverFragment extends Fragment implements TopPicksAdapter.ItemClickListener, TopicsAdapter.OnTopicsSelectChange {

    private FragmentDiscoverBinding binding;
    private ArrayList<Topic> selectedTopics;
    private TopPicksAdapter topPicksAdapter;
    private TopicsAdapter topicsAdapter;

    public DiscoverFragment() {

    }

    public static DiscoverFragment newInstance() {
        return new DiscoverFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_discover, container, false);
        selectedTopics = getSelectedTopics();
        initData();
        return binding.getRoot();
    }

    private void initData() {
        topicsAdapter = new TopicsAdapter(
                getContext(), selectedTopics
        );
        binding.topicsRv.setAdapter(topicsAdapter);
        topicsAdapter.setClickListener(this);

        updateSelectedTopics();
    }

    private ArrayList<Topic> getSelectedTopics() {
        ArrayList<Topic> allTopics = DummyData.getFirstUseTopics();
        ArrayList<Topic> selectedTopics = new ArrayList<>();
        ArrayList<Integer> ids = SharedPrefUtils.getFavoriteIds(getContext());
        for (Topic topic : allTopics) {
            if (ids.contains(topic.id)) {
                selectedTopics.add(topic);
            }
        }
        return selectedTopics;
    }

    @Override
    public void onItemClick(View view, String id) {
        DiscoverFragmentDirections.ActionDiscoverFragmentToBookActivity action =
                DiscoverFragmentDirections.actionDiscoverFragmentToBookActivity();
        action.setBookId(id);
        Navigation.findNavController(view).navigate(action);
    }

    @Override
    public void onUpdate() {
        updateCheckedTopics();
    }

    private void updateCheckedTopics() {
        ArrayList<BookEntity> entities = new ArrayList<>();
        for (Topic topic : topicsAdapter.getCheckedTopics()) {
            entities.addAll(AppDatabase.getDatabase(getContext()).getBookDao().getBookByTopicId(topic.id));
        }
        Collections.reverse(entities);
        BestShareListAdapter bestShareListAdapter = new BestShareListAdapter(getContext(), entities);
        binding.checkedTopicsBooksRv.setAdapter(bestShareListAdapter);
    }

    private void updateSelectedTopics() {
        ArrayList<BookEntity> entities = new ArrayList<>();
        for (Topic topic : selectedTopics) {
            entities.add(AppDatabase.getDatabase(getContext()).getBookDao().getCheckedTopic(topic.id));
        }
        topPicksAdapter = new TopPicksAdapter(getContext(), entities);
        topPicksAdapter.setClickListener(this);
        binding.topPicksRv.setAdapter(topPicksAdapter);

    }
}