package com.demo.androiddemo.base

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.demo.androiddemo.App
import com.demo.androiddemo.R


class AppBinding {

    companion object {

        @JvmStatic
        @BindingAdapter("app:loadGlideImage")
        fun loadGlideImage(iv: ImageView, url: String?) {
            if (!url.isNullOrEmpty() && App.instance?.applicationContext != null) {
                Glide.with(App.instance!!.applicationContext)
                    .load(url)
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .placeholder(R.drawable.ic_user_placeholder)
                    .error(R.drawable.ic_user_placeholder)
                    .into(iv)
                iv.scaleType = ImageView.ScaleType.CENTER_CROP
            } else {
                iv.scaleType = ImageView.ScaleType.CENTER_CROP
                iv.setImageResource(R.drawable.ic_user_placeholder)
            }
        }
    }
}

