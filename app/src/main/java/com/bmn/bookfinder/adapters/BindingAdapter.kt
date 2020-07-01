package com.bmn.bookfinder.adapters

import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.google.android.material.card.MaterialCardView

@BindingAdapter("cardBackground")
fun cardBackground(
    card: MaterialCardView,
    color: Int
) {
    card.setCardBackgroundColor(ContextCompat.getColorStateList(card.context, color))
}

@BindingAdapter("imageResource")
fun imageResource(
    imageView: ImageView,
    resource: Int
) {
    imageView.setImageResource(resource)
}

@BindingAdapter("glideUrl")
fun imageResource(
    imageView: ImageView,
    url:String
) {
    Glide.with(imageView.context).load(url).into(imageView)
}