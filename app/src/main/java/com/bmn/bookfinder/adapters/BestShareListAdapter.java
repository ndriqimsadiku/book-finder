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

import java.util.List;

public class BestShareListAdapter extends RecyclerView.Adapter<BestShareListAdapter.ViewHolder> {

    private List<BookEntity> bestShareModels;
    private LayoutInflater mInflater;
    private ItemCheckedListClickListener mClickListener;
    private Context context;

    public BestShareListAdapter(Context context, List<BookEntity> bestShareModels) {
        this.mInflater = LayoutInflater.from(context);
        this.bestShareModels = bestShareModels;
        this.context = context;
    }

    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_best_share, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BookEntity bookEntity = bestShareModels.get(position);
        if (bookEntity != null) {
            holder.title.setText(bookEntity.getTitle());
            holder.author.setText(bookEntity.getAuthors().get(0));
            Glide.with(context)
                    .load(bestShareModels.get(position).getThumbnailUrl())
                    .into(holder.image);
        }

    }

    @Override
    public int getItemCount() {
        return bestShareModels.size();
    }

    public void setClickListener(ItemCheckedListClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public interface ItemCheckedListClickListener {
        void onItemClick(View view, String bookId);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title, author;
        ImageView image;

        ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            image = itemView.findViewById(R.id.book_cover);
            author = itemView.findViewById(R.id.author);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) {
                mClickListener.onItemClick(view, bestShareModels.get(getAdapterPosition()).getId());
            }
        }
    }
}