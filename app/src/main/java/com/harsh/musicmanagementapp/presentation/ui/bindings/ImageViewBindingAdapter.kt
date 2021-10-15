package com.harsh.musicmanagementapp.presentation.ui.bindings

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("srcCompat")
fun setupSrcCompat(imageView: ImageView, imageResourceId: Int) {
    imageView.setImageResource(imageResourceId)
}

@BindingAdapter("srcCompat")
fun setupSrcCompat(imageView: ImageView, drawable: Drawable) {
    imageView.setImageDrawable(drawable)
}

@BindingAdapter("srcCompat")
fun setupSrcCompat(imageView: ImageView, url: String?) {
    Glide.with(imageView.context).load(url).into(imageView)
}
