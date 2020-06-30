package com.bmn.bookfinder.activities;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import com.bmn.bookfinder.R;
import com.bmn.bookfinder.adapters.ChooseTopicsLayoutAdapter;
import com.bmn.bookfinder.data.network.remote.ApiFunctions;
import com.bmn.bookfinder.data.network.remote.ApiInterfaces;
import com.bmn.bookfinder.data.room.AppDatabase;
import com.bmn.bookfinder.data.room.BookEntity;
import com.bmn.bookfinder.data.room.DatabaseAsync;
import com.bmn.bookfinder.databinding.ActivityChooseTopicsBinding;
import com.bmn.bookfinder.dummydata.DummyData;
import com.bmn.bookfinder.helpers.SharedPrefUtils;
import com.bmn.bookfinder.models.ApiResponse;
import com.bmn.bookfinder.models.Topic;
import com.bmn.bookfinder.models.googlebooks.GBResponse;
import com.bmn.bookfinder.models.googlebooks.ResponseItem;
import com.bmn.bookfinder.models.googlebooks.ResponseVolumeInfo;
import com.bmn.bookfinder.utils.AppUtils;
import com.bmn.bookfinder.utils.DimenUtils;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class ChooseTopicsActivity extends AppCompatActivity implements View.OnClickListener, ApiInterfaces.onApiResponse {

    private ApiFunctions mApiFunctions;
    private ArrayList<Topic> selectedTopics;
    private int currentIndex;
    private AppDatabase appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityChooseTopicsBinding binding = ActivityChooseTopicsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        appDatabase = AppDatabase.getDatabase(this);
        binding.topicsGrid.setLayoutManager(new GridLayoutManager(getApplicationContext(), 3));
        DimenUtils.setTopBottomPadding(binding.getRoot());

        ChooseTopicsLayoutAdapter chooseTopicsLayoutAdapter = new ChooseTopicsLayoutAdapter(
                getApplicationContext(), DummyData.getFirstUseTopics()
        );

        binding.topicsGrid.setAdapter(chooseTopicsLayoutAdapter);
        binding.applyTopicsButton.setOnClickListener(this);

        mApiFunctions = new ApiFunctions();
        mApiFunctions.setApiGenresResponseListener(this);
    }

    @Override
    public void onClick(View v) {
        if (!SharedPrefUtils.getFavoriteIds(getApplicationContext()).isEmpty()) {
            selectedTopics = getSelectedTopics();
            if (AppUtils.isInternetAvailable(getApplicationContext())) {
                showAlertDialog();
                mApiFunctions.getBooksBySubject(getApplicationContext(), selectedTopics.get(currentIndex).getText());
            } else {
                Snackbar.make(findViewById(android.R.id.content), R.string.internet_connection_message, Snackbar.LENGTH_SHORT).show();
            }
        } else {
            Snackbar.make(findViewById(android.R.id.content), R.string.please_choose_topics, Snackbar.LENGTH_SHORT).show();
        }
    }

    private void showAlertDialog() {
        View view = getLayoutInflater().inflate(R.layout.choose_topic_custom_alert, null, false);
        AlertDialog alertDialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setView(view);
        alertDialog = builder.create();
        Window window = alertDialog.getWindow();
        if (window != null) {
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        alertDialog.show();
    }

    private ArrayList<Topic> getSelectedTopics() {
        ArrayList<Topic> allTopics = DummyData.getFirstUseTopics();
        ArrayList<Topic> selectedTopics = new ArrayList<>();
        ArrayList<Integer> ids = SharedPrefUtils.getFavoriteIds(getApplicationContext());
        for (Topic topic : allTopics) {
            if (ids.contains(topic.id)) {
                selectedTopics.add(topic);
            }
        }
        return selectedTopics;
    }

    @Override
    public void onApiResponse(boolean status, ApiResponse apiResponse, String message) {
        if (status) {
            ArrayList<BookEntity> bookEntities = new ArrayList<>();

            GBResponse gbResponse = (GBResponse) apiResponse;
            if (gbResponse != null) {
                for (ResponseItem item : gbResponse.getItems()) {
                    bookEntities.add(getBookFromNetwork(item));
                }
                DatabaseAsync databaseAsync = new DatabaseAsync(appDatabase.getBookDao(), success -> {
                    currentIndex++;
                    if (currentIndex < selectedTopics.size()) {
                        getNextTopicBooks();
                    } else {
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        SharedPrefUtils.storeFirstTimeUsedTimestamp(getApplicationContext(), System.currentTimeMillis());
                    }
                });

                databaseAsync.execute(bookEntities);
            }
        }
    }

    private void getNextTopicBooks() {
        if (AppUtils.isInternetAvailable(getApplicationContext())) {
            mApiFunctions.getBooksBySubject(getApplicationContext(), selectedTopics.get(currentIndex).getText());
        } else {
            Snackbar.make(findViewById(android.R.id.content), R.string.internet_connection_message, Snackbar.LENGTH_SHORT).show();
        }
    }


    private BookEntity getBookFromNetwork(ResponseItem item) {
        ResponseVolumeInfo info = item.getVolumeInfo();
        return new BookEntity(
                item.getId(),
                selectedTopics.get(currentIndex).id,
                info.getTitle(),
                info.getDescription(),
                info.getImageLinks().getThumbnail(),
                info.getAuthors(),
                info.getAverageRating(),
                info.getPageCount(),
                info.getPublishedDate(),
                false);
    }


}