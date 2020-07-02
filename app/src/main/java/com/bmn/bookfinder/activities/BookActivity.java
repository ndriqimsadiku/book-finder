package com.bmn.bookfinder.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.bmn.bookfinder.R;
import com.bmn.bookfinder.data.room.AppDatabase;
import com.bmn.bookfinder.data.room.BookEntity;
import com.bmn.bookfinder.databinding.ActivityBookBinding;
import com.bumptech.glide.Glide;

import java.util.List;

public class BookActivity extends AppCompatActivity {

    private ActivityBookBinding binding;
    private BookEntity bookEntity;
    private String bookId;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBookBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        bookId = BookActivityArgs.fromBundle(getIntent().getExtras()).getBookId();
        setBookData(bookId);
        binding.bookRating.setOnTouchListener((v, event) -> true);
        listenBookFavoriteChanges();
    }

    void listenBookFavoriteChanges() {
        binding.favoriteBook.setOnClickListener(v -> {
            bookEntity.setFavorite(!bookEntity.isFavorite());
            binding.favoriteBook.setImageDrawable(
                    bookEntity.isFavorite() ?
                            getDrawable(R.drawable.ic_heart_on) :
                            getDrawable(R.drawable.ic_heart)
            );
            AppDatabase.getDatabase(getApplicationContext()).getBookDao().setBookAsFavoriteById(bookId, bookEntity.isFavorite());

        });

    }

    private void setBookData(String bookId) {
        bookEntity = AppDatabase.getDatabase(getApplicationContext()).getBookDao().getBookById(bookId);
        formatAuthors(bookEntity.getAuthors());
        binding.bookDescription.setText(bookEntity.getDescription());
        binding.bookPageCount.setText(getString(R.string.page_count_d, bookEntity.getPageCount()));
        binding.bookPublishedDate.setText(bookEntity.getPublishedDate());
        binding.bookTitle.setText(bookEntity.getTitle());
        binding.bookRating.setRating((float) bookEntity.getAverageRating());
        binding.favoriteBook.setImageDrawable(
                bookEntity.isFavorite() ?
                        getDrawable(R.drawable.ic_heart_on) :
                        getDrawable(R.drawable.ic_heart)
        );

        Glide.with(this)
                .load(bookEntity.getThumbnailUrl())
                .into(binding.bookImage);

    }

    private void formatAuthors(List<String> authors) {
        StringBuilder authorsBuilder = new StringBuilder();

        for (String author : authors) {
            if (authors.indexOf(author) == authors.size() - 1) {
                authorsBuilder.append(author);
            }
            authorsBuilder.append(String.format("%s, ", author));
        }
        binding.bookAuthors.setText(authorsBuilder.toString());
    }
}