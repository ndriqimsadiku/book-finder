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
import com.bmn.bookfinder.models.BestShareModel;

import java.util.List;

public class BestShareListAdapter extends RecyclerView.Adapter<BestShareListAdapter.ViewHolder> {

    private List<BestShareModel> bestShareModels;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    public BestShareListAdapter(Context context, List<BestShareModel> bestShareModels) {
        this.mInflater = LayoutInflater.from(context);
        this.bestShareModels = bestShareModels;
    }

    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_best_share, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(bestShareModels.get(position).getTitle());
        holder.author.setText(bestShareModels.get(position).getAuthor());
        holder.image.setImageResource(bestShareModels.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return bestShareModels.size();
    }

    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
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
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }
}