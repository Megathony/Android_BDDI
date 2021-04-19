package com.anthony.neighbors.adapters;

import android.widget.ImageButton
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.anthony.neighbors.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

object NeighborBindingAdapder {
    @BindingAdapter("app:avatar")
    @JvmStatic
    fun bindImage(imageView: ImageView, url: String) {
        val context = imageView.context
        Glide.with(context)
            .load(url)
            .apply(RequestOptions.circleCropTransform())
            .placeholder(R.drawable.ic_baseline_person_24)
            .error(R.drawable.ic_baseline_delete_24)
            .skipMemoryCache(false)
            .into(imageView)
    }

    @BindingAdapter("app:favorite")
    @JvmStatic
    fun bindFavorite(imageButton: ImageButton, favorite: Boolean) {
        if (favorite) {
            imageButton.setImageResource(R.drawable.ic_baseline_favorite_24)
        } else {
            imageButton.setImageResource(R.drawable.ic_baseline_favorite_border_24)
        }
    }
}