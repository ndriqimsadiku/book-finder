package com.bmn.bookfinder.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bmn.bookfinder.R;
import com.bmn.bookfinder.helpers.SharedPrefUtils;
import com.bmn.bookfinder.models.Topic;
import com.bumptech.glide.Glide;

import java.util.List;

public class ChooseTopicsLayoutAdapter extends RecyclerView.Adapter<ChooseTopicsLayoutAdapter.ViewHolder> {

    private List<Topic> topics;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    public ChooseTopicsLayoutAdapter(Context context, List<Topic> topics) {
        this.mInflater = LayoutInflater.from(context);
        this.topics = topics;
    }

    // inflates the cell layout from xml when needed
    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.topics_grid_item, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each cell
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Topic currentTopic = topics.get(position);
        Context context = holder.itemView.getContext();

        String thumbnailUrl = currentTopic.thumbnailUrl;
        holder.text.setText(currentTopic.getText());
        holder.checkedLayer.setVisibility(SharedPrefUtils.getFavoriteIds(context).contains(currentTopic) ? View.VISIBLE : View.GONE);

        holder.constraintLayout.setOnClickListener(view -> {
            if (holder.checkedLayer.getVisibility() == View.VISIBLE) {
                SharedPrefUtils.removeFavoriteId(context, currentTopic.id);
            } else {
                SharedPrefUtils.addFavoriteId(context, currentTopic.id);
            }
            holder.checkedLayer.setVisibility(holder.checkedLayer.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);

        });

        if (thumbnailUrl != null && !thumbnailUrl.isEmpty()) {
            Glide.with(context).load(thumbnailUrl).into(holder.image);
        }
    }

    // total number of cells
    @Override
    public int getItemCount() {
        return topics.size();
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView text;
        private ImageView image;
        private ConstraintLayout constraintLayout;
        private RelativeLayout checkedLayer;

        ViewHolder(View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.topics_item_text);
            image = itemView.findViewById(R.id.topics_item_image);
            constraintLayout = itemView.findViewById(R.id.item_topic_container);
            checkedLayer = itemView.findViewById(R.id.topic_checked);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }
}