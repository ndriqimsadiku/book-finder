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
import com.bmn.bookfinder.models.Topic
import com.bumptech.glide.Glide
import java.util.*

class TopicsAdapter(context: Context?, topics: List<Topic>?) :
    RecyclerView.Adapter<TopicsAdapter.ViewHolder>() {
    private val topics: List<Topic>?
    private val checkedTopics: MutableList<Topic>
    private val mInflater: LayoutInflater
    private var mListener: OnTopicsSelectChange? = null
    fun getCheckedTopics(): List<Topic> {
        return checkedTopics
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = mInflater.inflate(R.layout.topics_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentTopic = topics!![position]
        val thumbnailUrl = currentTopic.thumbnailUrl
        holder.checkedLayer.visibility =
            if (checkedTopics.contains(currentTopic)) View.VISIBLE else View.GONE
        holder.text.text = currentTopic.text
        holder.constraintLayout.setOnClickListener { view: View? ->
            holder.checkedLayer.visibility =
                if (holder.checkedLayer.visibility == View.VISIBLE) View.GONE else View.VISIBLE
            if (mListener != null) {
                if (holder.checkedLayer.visibility == View.VISIBLE) {
                    if (topics.contains(currentTopic)) {
                        checkedTopics.add(currentTopic)
                    }
                } else {
                    checkedTopics.remove(currentTopic)
                }
                mListener!!.onUpdate()
            }
        }
        if (thumbnailUrl != null && !thumbnailUrl.isEmpty()) {
            Glide.with(holder.text.context).load(thumbnailUrl).into(holder.image)
        }
    }

    override fun getItemCount(): Int {
        return topics?.size ?: 0
    }

    fun setClickListener(onTopicsSelectChange: OnTopicsSelectChange?) {
        mListener = onTopicsSelectChange
    }

    interface OnTopicsSelectChange {
        fun onUpdate()
    }

    class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val text: TextView
        val image: ImageView
        val constraintLayout: ConstraintLayout
        val checkedLayer: RelativeLayout

        init {
            text = itemView.findViewById(R.id.topics_list_text)
            image = itemView.findViewById(R.id.topics_list_image)
            constraintLayout = itemView.findViewById(R.id.item_topic_container)
            checkedLayer = itemView.findViewById(R.id.topic_checked)
        }
    }

    init {
        mInflater = LayoutInflater.from(context)
        this.topics = topics
        checkedTopics = ArrayList()
    }
}