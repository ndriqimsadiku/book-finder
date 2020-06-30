package com.bmn.bookfinder.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.bmn.bookfinder.R;
import com.bmn.bookfinder.adapters.BestShareListAdapter;
import com.bmn.bookfinder.adapters.TopPicksAdapter;
import com.bmn.bookfinder.adapters.TopicsAdapter;
import com.bmn.bookfinder.data.network.remote.ApiFunctions;
import com.bmn.bookfinder.data.network.remote.ApiInterfaces;
import com.bmn.bookfinder.databinding.FragmentDiscoverBinding;
import com.bmn.bookfinder.dummydata.DummyData;
import com.bmn.bookfinder.helpers.SharedPrefUtils;
import com.bmn.bookfinder.models.ApiResponse;
import com.bmn.bookfinder.models.BestShareModel;
import com.bmn.bookfinder.models.TopPick;
import com.bmn.bookfinder.models.Topic;
import com.bmn.bookfinder.models.googlebooks.GBResponse;
import com.bmn.bookfinder.models.googlebooks.ResponseItem;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class DiscoverFragment extends Fragment implements ApiInterfaces.onApiResponse {

    FragmentDiscoverBinding binding;

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
        return binding.getRoot();
    }

    private void initData() {
        ApiFunctions apiFunctions = new ApiFunctions();
        apiFunctions.setApiGenresResponseListener(this);
        apiFunctions.getBooksBySubject(getContext(), "History");

        List<BestShareModel> bestShareModels = new ArrayList<>();

        TopicsAdapter topicsAdapter = new TopicsAdapter(
                getContext(), getSelectedTopics()
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
    public void onApiResponse(boolean status, ApiResponse apiResponse, String message) {
        if (apiResponse instanceof GBResponse) {
            GBResponse gbResponse = (GBResponse) apiResponse;
            List<TopPick> topics = new ArrayList<>();
            for (ResponseItem responseItem : gbResponse.getItems()) {

                topics.add(new TopPick(
                        responseItem.getVolumeInfo().getTitle(),
                        responseItem.getVolumeInfo().getImageLinks().getThumbnail()));

                TopPicksAdapter topPicksAdapter = new TopPicksAdapter(
                        getContext(), topics
                );
                binding.topPicksRv.setAdapter(topPicksAdapter);
            }
        }
    }
}