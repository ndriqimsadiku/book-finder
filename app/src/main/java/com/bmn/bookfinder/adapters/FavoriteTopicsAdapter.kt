package com.bmn.bookfinder.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bmn.bookfinder.databinding.ItemFavoriteTopicBinding
import com.bmn.bookfinder.models.Topic

class FavoriteTopicsAdapter(val onClickListener: OnClickListener) :
    ListAdapter<Topic, FavoriteTopicsAdapter.FavoriteTopicViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteTopicViewHolder {
        return FavoriteTopicViewHolder(
            ItemFavoriteTopicBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: FavoriteTopicViewHolder, position: Int) {
        val story = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(story)
        }
        holder.bind(story)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Topic>() {
        override fun areItemsTheSame(oldItem: Topic, newItem: Topic): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Topic, newItem: Topic): Boolean {
            return oldItem.id == newItem.id
        }
    }

    class FavoriteTopicViewHolder(private var binding: ItemFavoriteTopicBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(topic: Topic) {
            binding.topic = topic
        }
    }

    interface OnClickListener {
        fun onClick(topic: Topic)
    }
}