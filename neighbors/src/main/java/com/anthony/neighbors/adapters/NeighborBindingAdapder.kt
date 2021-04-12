package com.anthony.neighbors.adapters;

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.anthony.neighbors.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

object NeighborBindingAdapder {
    @BindingAdapter( "app:avatar")
    @JvmStatic
    fun bindImage(imageView: ImageView, url: String) {
        Glide.with(imageView.context)
            .load(url)
            .apply(RequestOptions.circleCropTransform())
            .placeholder(R.drawable.ic_baseline_person_outline_24)
            .error(R.drawable.ic_baseline_person_outline_24)
            .skipMemoryCache(false)
            .into(imageView)
    }
}
