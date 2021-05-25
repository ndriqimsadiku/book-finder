package com.bmn.bookfinder.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bmn.bookfinder.R
import com.bmn.bookfinder.data.room.BookEntity
import com.bumptech.glide.Glide
import java.util.*

/**
 * Created by Ndriçim Sadiku on 01 July 2020
 * ndricim@frakton.com
 */
class TopicBooksAdapter(private val mContext: Context) :
    RecyclerView.Adapter<TopicBooksAdapter.ViewHolder>() {
    private var books: List<BookEntity>
    private var listener: OnItemTopicBookClick? = null
    fun setBooks(topics: List<BookEntity>) {
        books = topics
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_book, parent, false),
            listener
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val book = books[position]
        holder.title.text = book.title
        holder.description.text = book.description
        Glide.with(mContext).load(book.thumbnailUrl).into(holder.thumbnail)
    }

    override fun getItemCount(): Int {
        return books.size
    }

    fun setListener(listener: OnItemTopicBookClick?) {
        this.listener = listener
    }

    interface OnItemTopicBookClick {
        fun onItemTopicBookClick(view: View, bookId: String)
    }

    inner class ViewHolder(itemView: View, listener: OnItemTopicBookClick?) :
        RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.book_title)
        val thumbnail: ImageView = itemView.findViewById(R.id.book_thumbnail)
        val description: TextView = itemView.findViewById(R.id.book_description)

        init {
            itemView.setOnClickListener {
                listener?.onItemTopicBookClick(
                    itemView,
                    books[adapterPosition].id
                )
            }
        }
    }

    init {
        books = ArrayList()
    }
}