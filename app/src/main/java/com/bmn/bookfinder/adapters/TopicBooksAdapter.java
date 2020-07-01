package com.bmn.bookfinder.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bmn.bookfinder.R;
import com.bmn.bookfinder.data.room.BookEntity;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ndriçim Sadiku on 01 July 2020
 * ndricim@frakton.com
 */
public class TopicBooksAdapter extends RecyclerView.Adapter<TopicBooksAdapter.ViewHolder> {

    private List<BookEntity> books;
    private Context mContext;

    public TopicBooksAdapter(Context context) {
        this.mContext = context;
        books = new ArrayList<>();
    }

    public void setBooks(List<BookEntity> topics) {
        this.books = topics;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_book, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BookEntity book = books.get(position);
        holder.title.setText(book.getTitle());
        holder.description.setText(book.getDescription());
        Glide.with(mContext).load(book.getThumbnailUrl()).into(holder.thumbnail);
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private ImageView thumbnail;
        private TextView description;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.book_title);
            description = itemView.findViewById(R.id.book_description);
            thumbnail = itemView.findViewById(R.id.book_thumbnail);
        }
    }
}
