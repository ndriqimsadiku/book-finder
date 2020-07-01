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
import com.bmn.bookfinder.models.Topic;
import com.bumptech.glide.Glide;

import java.util.List;

public class TopicsAdapter extends RecyclerView.Adapter<TopicsAdapter.ViewHolder> {

    private List<Topic> topics;
    private LayoutInflater mInflater;
    private ItemTopicsClickListener mClickListener;

    public TopicsAdapter(Context context, List<Topic> topics) {
        this.mInflater = LayoutInflater.from(context);
        this.topics = topics;
    }

    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.topics_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Topic currentTopic = topics.get(position);
        String thumbnailUrl = currentTopic.thumbnailUrl;

        holder.text.setText(currentTopic.getText());
        holder.constraintLayout.setOnClickListener(view -> {
            holder.checkedLayer.setVisibility(holder.checkedLayer.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);

        });

        if (thumbnailUrl != null && !thumbnailUrl.isEmpty()) {
            Glide.with(holder.text.getContext()).load(thumbnailUrl).into(holder.image);
        }
    }

    @Override
    public int getItemCount() {
        return topics.size();
    }

    void setClickListener(ItemTopicsClickListener itemTopicsClickListener) {
        this.mClickListener = itemTopicsClickListener;
    }

    public interface ItemTopicsClickListener {
        void onItemTopicsClick(View view, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView text;
        private ImageView image;
        private ConstraintLayout constraintLayout;
        private RelativeLayout checkedLayer;

        ViewHolder(View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.topics_list_text);
            image = itemView.findViewById(R.id.topics_list_image);
            constraintLayout = itemView.findViewById(R.id.item_topic_container);
            checkedLayer = itemView.findViewById(R.id.topic_checked);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null)
                mClickListener.onItemTopicsClick(view, getAdapterPosition());
        }
    }
}