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

class TopPicksAdapter(private val context: Context, topPicks: List<BookEntity?>) :
    RecyclerView.Adapter<TopPicksAdapter.ViewHolder>() {
    private val topPicks: List<BookEntity?> = topPicks
    private val mInflater: LayoutInflater = LayoutInflater.from(context)
    private var mClickListener: ItemClickListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = mInflater.inflate(R.layout.top_picks_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.text.text = topPicks[position]?.title
        Glide.with(context)
            .load(topPicks[position]?.thumbnailUrl)
            .into(holder.image)
    }

    override fun getItemCount(): Int {
        return topPicks.size
    }

    fun setClickListener(itemClickListener: ItemClickListener?) {
        mClickListener = itemClickListener
    }

    // parent activity will implement this method to respond to click events
    interface ItemClickListener {
        fun onItemClick(view: View?, id: String?)
    }

    // stores and recycles views as they are scrolled off screen
    inner class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        var text: TextView = itemView.findViewById(R.id.top_picks_text)
        var image: ImageView = itemView.findViewById(R.id.top_picks_image)
        override fun onClick(view: View) {
            if (mClickListener != null) mClickListener!!.onItemClick(
                view,
                topPicks[adapterPosition]?.id
            )
        }

        init {
            itemView.setOnClickListener(this)
        }
    }

}