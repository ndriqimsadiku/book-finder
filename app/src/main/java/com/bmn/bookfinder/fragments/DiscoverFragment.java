package com.bmn.bookfinder.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.bmn.bookfinder.R;
import com.bmn.bookfinder.adapters.BestShareListAdapter;
import com.bmn.bookfinder.adapters.TopPicksAdapter;
import com.bmn.bookfinder.adapters.TopicsAdapter;
import com.bmn.bookfinder.databinding.FragmentDiscoverBinding;
import com.bmn.bookfinder.dummydata.DummyData;
import com.bmn.bookfinder.helpers.SharedPrefUtils;
import com.bmn.bookfinder.models.BestShareModel;
import com.bmn.bookfinder.models.TopPick;
import com.bmn.bookfinder.models.Topic;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class DiscoverFragment extends Fragment implements TopPicksAdapter.ItemClickListener, TopicsAdapter.ItemTopicsClickListener {

    FragmentDiscoverBinding binding;
    private List<TopPick> topics;
    private ArrayList<Topic> checkedTopics;
    private ArrayList<Topic> selectedTopics;

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
        initData();
        selectedTopics = getSelectedTopics();
        return binding.getRoot();
    }

    private void initData() {

        List<BestShareModel> bestShareModels = new ArrayList<>();

        TopicsAdapter topicsAdapter = new TopicsAdapter(
                getContext(), selectedTopics
        );
        binding.topicsRv.setAdapter(topicsAdapter);

        for (int i = 0; i < 20; i++) {
            bestShareModels.add(new BestShareModel("Title", R.drawable.fatherhood, "Author"));
        }
        BestShareListAdapter bestShareListAdapter = new BestShareListAdapter(getContext(), bestShareModels);
        binding.bestShareRv.setAdapter(bestShareListAdapter);
        binding.recentleViewedRv.setAdapter(bestShareListAdapter);
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
    public void onItemClick(View view, int position) {
        DiscoverFragmentDirections.ActionDiscoverFragmentToBookActivity action =
                DiscoverFragmentDirections.actionDiscoverFragmentToBookActivity();
        action.setBookId(topics.get(position).getBookId());
        Navigation.findNavController(view).navigate(action);
    }

    @Override
    public void onItemTopicsClick(View view, int position) {
        RelativeLayout checkedLayer = view.findViewById(R.id.topic_checked);
        checkedLayer.setVisibility(checkedLayer.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
        if (checkedLayer.getVisibility() == View.VISIBLE) {
            checkedTopics.add(topics.get(position));
        }
    }
}