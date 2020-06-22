package com.bmn.bookfinder.adapters

import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
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