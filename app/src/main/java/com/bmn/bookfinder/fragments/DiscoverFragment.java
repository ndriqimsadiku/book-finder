package com.bmn.bookfinder.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.bmn.bookfinder.R;
import com.bmn.bookfinder.adapters.TopicBooksAdapter;
import com.bmn.bookfinder.data.network.remote.ApiFunctions;
import com.bmn.bookfinder.data.network.remote.ApiInterfaces;
import com.bmn.bookfinder.data.room.AppDatabase;
import com.bmn.bookfinder.data.room.BookEntity;
import com.bmn.bookfinder.databinding.FragmentDiscoverBinding;
import com.bmn.bookfinder.models.ApiResponse;
import com.bmn.bookfinder.models.googlebooks.GBResponse;
import com.bmn.bookfinder.models.googlebooks.ResponseItem;
import com.bmn.bookfinder.models.googlebooks.ResponseVolumeInfo;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class DiscoverFragment extends Fragment implements ApiInterfaces.onApiResponse, TopicBooksAdapter.OnItemTopicBookClick {

    private FragmentDiscoverBinding binding;
    private ApiFunctions mApiFunctions;
    private ProgressDialog progressDialog;


    public DiscoverFragment() {

    }

    public static HomeFragment newInstance() {
        return new HomeFragment();
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
        mApiFunctions = new ApiFunctions();
        mApiFunctions.setApiGenresResponseListener(this);
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage(getString(R.string.searching));
        progressDialog.setCancelable(false);

        binding.iconSearch.setOnClickListener(v -> {
            if (binding.searchEditText.getText().toString().isEmpty()) {
                binding.searchEditText.requestFocus();
                binding.searchEditText.setError(getString(R.string.type_something));
                return;
            }
            progressDialog.show();
            mApiFunctions.searchBooks(getContext(), binding.searchEditText.getText().toString());
        });
    }

    @Override
    public void onApiResponse(boolean status, ApiResponse apiResponse, String message) {
        progressDialog.cancel();
        if (status) {
            ArrayList<BookEntity> bookEntities = new ArrayList<>();

            GBResponse gbResponse = (GBResponse) apiResponse;
            if (gbResponse != null) {
                for (ResponseItem item : gbResponse.getItems()) {

                    if (item.getVolumeInfo().getImageLinks() != null) {
                        bookEntities.add(getBookFromNetwork(item));
                    }
                }
                TopicBooksAdapter booksAdapter = new TopicBooksAdapter(getContext());
                booksAdapter.setListener(this);
                booksAdapter.setBooks(bookEntities);
                AppDatabase.getDatabase(getContext()).getBookDao().insertBooks(bookEntities);

                binding.searchResultsRv.setAdapter(booksAdapter);
            }
        }
    }

    private BookEntity getBookFromNetwork(ResponseItem item) {
        ResponseVolumeInfo info = item.getVolumeInfo();
        return new BookEntity(
                item.getId(),
                0,
                "General",
                info.getTitle(),
                info.getDescription(),
                info.getImageLinks().getThumbnail(),
                info.getAuthors(),
                info.getAverageRating(),
                info.getPageCount(),
                info.getPublishedDate(),
                false);
    }

    @Override
    public void onItemTopicBookClick(View view, String bookId) {
        DiscoverFragmentDirections.ActionDiscoverFragmentToBookActivity action =
                DiscoverFragmentDirections.actionDiscoverFragmentToBookActivity();
        action.setBookId(bookId);
        Navigation.findNavController(view).navigate(action);
    }
}