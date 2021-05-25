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

class BestShareListAdapter(val context: Context, val bestShareModels: List<BookEntity?>) :
    RecyclerView.Adapter<BestShareListAdapter.ViewHolder>() {
    private val mInflater: LayoutInflater = LayoutInflater.from(context)
    private var mClickListener: ItemCheckedListClickListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = mInflater.inflate(R.layout.item_best_share, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val bookEntity = bestShareModels[position]
        bookEntity ?: return
        holder.title.text = bookEntity.title
        holder.author.text = bookEntity.authors[0]
        Glide.with(context)
            .load(bookEntity.thumbnailUrl)
            .into(holder.image)
    }

    override fun getItemCount(): Int {
        return bestShareModels.size
    }

    fun setClickListener(itemClickListener: ItemCheckedListClickListener?) {
        mClickListener = itemClickListener
    }

    interface ItemCheckedListClickListener {
        fun onItemClick(view: View?, bookId: String?)
    }

    inner class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        var title: TextView = itemView.findViewById(R.id.title)
        var author: TextView = itemView.findViewById(R.id.author)
        var image: ImageView = itemView.findViewById(R.id.book_cover)
        override fun onClick(view: View) {
            if (mClickListener != null) {
                mClickListener!!.onItemClick(view, bestShareModels[adapterPosition]?.id)
            }
        }

        init {
            itemView.setOnClickListener(this)
        }
    }
}