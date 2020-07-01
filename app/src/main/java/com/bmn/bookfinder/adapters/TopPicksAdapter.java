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
import com.bmn.bookfinder.models.TopPick;
import com.bumptech.glide.Glide;

import java.util.List;

public class TopPicksAdapter extends RecyclerView.Adapter<TopPicksAdapter.ViewHolder> {

    private List<TopPick> topPicks;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private Context context;

    public TopPicksAdapter(Context context, List<TopPick> topPicks) {
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        this.topPicks = topPicks;
    }

    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.top_picks_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.text.setText(topPicks.get(position).getTitle());
        Glide.with(context)
                .load(topPicks.get(position).getImageUrl())
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return topPicks.size();
    }

   public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView text;
        ImageView image;

        ViewHolder(View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.top_picks_text);
            image = itemView.findViewById(R.id.top_picks_image);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }
}