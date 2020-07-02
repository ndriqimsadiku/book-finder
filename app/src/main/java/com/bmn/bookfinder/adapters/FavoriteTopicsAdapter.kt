package com.bmn.bookfinder.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bmn.bookfinder.R
import com.bmn.bookfinder.data.room.BookEntity
import com.bmn.bookfinder.databinding.ItemFavoriteTopicBinding
import com.bmn.bookfinder.models.Topic

private val listOfColors = arrayOf(
    R.color.card_blue_color,
    R.color.card_purple_color,
    R.color.card_green_color,
    R.color.card_pink_color,
    R.color.card_blue_to_purple_color,
    R.color.card_dark_yellow_color
)

class FavoriteTopicsAdapter(val onClickListener: OnClickListener) :
    ListAdapter<BookEntity, FavoriteTopicsAdapter.FavoriteTopicViewHolder>(DiffCallback) {

    var colorPos = 0

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
        val book = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(book)
        }
        holder.bind(book, colorPos++)
        if (colorPos == listOfColors.size) {
            colorPos = 0
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<BookEntity>() {
        override fun areItemsTheSame(oldItem: BookEntity, newItem: BookEntity): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: BookEntity, newItem: BookEntity): Boolean {
            return oldItem.id == newItem.id
        }
    }

    class FavoriteTopicViewHolder(private var binding: ItemFavoriteTopicBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(book: BookEntity, pos: Int) {
            binding.topic = Topic(book.thumbnailUrl, book.topic, listOfColors[pos])
        }
    }

    interface OnClickListener {
        fun onClick(book: BookEntity)
    }
}