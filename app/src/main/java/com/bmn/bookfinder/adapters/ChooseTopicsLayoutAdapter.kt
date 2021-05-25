package com.bmn.bookfinder.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bmn.bookfinder.R
import com.bmn.bookfinder.helpers.SharedPrefUtils
import com.bmn.bookfinder.models.Topic
import com.bumptech.glide.Glide

class ChooseTopicsLayoutAdapter(val context: Context?,val topics: List<Topic>) :
    RecyclerView.Adapter<ChooseTopicsLayoutAdapter.ViewHolder>() {
    private val mInflater: LayoutInflater = LayoutInflater.from(context)
    private var mClickListener: ItemClickListener? = null

    // inflates the cell layout from xml when needed
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = mInflater.inflate(R.layout.topics_grid_item, parent, false)
        return ViewHolder(view)
    }

    // binds the data to the TextView in each cell
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentTopic = topics[position]
        val context = holder.itemView.context
        val thumbnailUrl = currentTopic.thumbnailUrl
        holder.text.text = currentTopic.text
        holder.checkedLayer.visibility = if (SharedPrefUtils.getFavoriteIds(context)
                .contains(currentTopic)
        ) View.VISIBLE else View.GONE
        holder.constraintLayout.setOnClickListener { view: View? ->
            if (holder.checkedLayer.visibility == View.VISIBLE) {
                SharedPrefUtils.removeFavoriteId(context, currentTopic.id)
            } else {
                SharedPrefUtils.addFavoriteId(context, currentTopic.id)
            }
            holder.checkedLayer.visibility =
                if (holder.checkedLayer.visibility == View.VISIBLE) View.GONE else View.VISIBLE
        }
        if (thumbnailUrl != null && !thumbnailUrl.isEmpty()) {
            Glide.with(context).load(thumbnailUrl).into(holder.image)
        }
    }

    // total number of cells
    override fun getItemCount(): Int {
        return topics.size
    }

    // allows clicks events to be caught
    fun setClickListener(itemClickListener: ItemClickListener?) {
        mClickListener = itemClickListener
    }

    // parent activity will implement this method to respond to click events
    interface ItemClickListener {
        fun onItemClick(view: View?, position: Int)
    }

    // stores and recycles views as they are scrolled off screen
    inner class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        val text: TextView
        val image: ImageView
        val constraintLayout: ConstraintLayout
        val checkedLayer: RelativeLayout
        override fun onClick(view: View) {
            if (mClickListener != null) mClickListener!!.onItemClick(view, adapterPosition)
        }

        init {
            text = itemView.findViewById(R.id.topics_item_text)
            image = itemView.findViewById(R.id.topics_item_image)
            constraintLayout = itemView.findViewById(R.id.item_topic_container)
            checkedLayer = itemView.findViewById(R.id.topic_checked)
            itemView.setOnClickListener(this)
        }
    }
}