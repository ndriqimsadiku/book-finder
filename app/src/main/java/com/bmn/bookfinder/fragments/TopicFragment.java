package com.bmn.bookfinder.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.bmn.bookfinder.adapters.TopicBooksAdapter;
import com.bmn.bookfinder.data.room.AppDatabase;
import com.bmn.bookfinder.data.room.BookEntity;
import com.bmn.bookfinder.databinding.FragmentTopicBinding;

import java.util.List;

/**
 * Created by Ndriçim Sadiku on 01 July 2020
 * ndricim@frakton.com
 */
public class TopicFragment extends Fragment implements TopicBooksAdapter.OnItemTopicBookClick {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentTopicBinding binding = FragmentTopicBinding.inflate(getLayoutInflater());

        TopicFragmentArgs args = TopicFragmentArgs.fromBundle(getArguments());

        binding.topicTitle.setText(args.getTopicTitle());

        binding.back.setOnClickListener(view -> getActivity().onBackPressed());

        List<BookEntity> list = AppDatabase.getDatabase(getContext()).getBookDao().getBookByTopicId(args.getTopicId());
        TopicBooksAdapter booksAdapter = new TopicBooksAdapter(getContext());
        booksAdapter.setListener(this);
        booksAdapter.setBooks(list);

        binding.bookList.setAdapter(booksAdapter);
        return binding.getRoot();
    }

    @Override
    public void onItemTopicBookClick(View view, String bookId) {
        TopicFragmentDirections.ActionTopicFragmentToBookActivity action =
                TopicFragmentDirections.actionTopicFragmentToBookActivity();
        action.setBookId(bookId);
        Navigation.findNavController(view).navigate(action);
    }
}
