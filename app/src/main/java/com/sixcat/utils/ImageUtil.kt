package com.sixcat.utils

import android.text.TextUtils
import android.widget.ImageView

import com.bumptech.glide.request.RequestOptions.placeholderOf
import com.bumptech.glide.request.RequestOptions.skipMemoryCacheOf

import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.sixcat.R

/**
 * glide v3->v4 api的改变需要注意
 *
 * @author liguoying
 * @date 2017/12/4.
 */
object ImageUtil {
    fun loadImage(url: String, imageView: ImageView) {
        if (TextUtils.isEmpty(url)) {
            imageView.setImageResource(R.drawable.iv_default)
            return
        }
        Glide.with(imageView.context).load(url).into(imageView)
    }

    fun loadImageWithoutCache(url: String, imageView: ImageView) {
        if (TextUtils.isEmpty(url)) {
            imageView.setImageResource(R.drawable.iv_default)
            return
        }
        Glide.with(imageView.context).load(url).apply(skipMemoryCacheOf(true))
                .into(imageView)
    }

    fun loadImage(url: String, imageView: ImageView, holderResId: Int) {
        if (TextUtils.isEmpty(url)) {
            imageView.setImageResource(R.drawable.iv_default)
            return
        }
        Glide.with(imageView.context).load(url).apply(placeholderOf(holderResId)).into(imageView)
    }

    fun loadImageWithBitmap(url: String, imageView: ImageView, holderResId: Int) {
        if (TextUtils.isEmpty(url)) {
            imageView.setImageResource(R.drawable.iv_default)
            return
        }
        Glide.with(imageView.context).load(url).apply(placeholderOf(holderResId)).into(imageView)
    }

    fun loadImageWithoutCache(url: String, imageView: ImageView, holderResId: Int) {
        if (TextUtils.isEmpty(url)) {
            imageView.setImageResource(R.drawable.iv_default)
            return
        }
        Glide.with(imageView.context).load(url).apply(skipMemoryCacheOf(true)).apply(placeholderOf(holderResId))
                .into(imageView)
    }

    fun loadThumbnail(url: String, imageView: ImageView) {
        if (TextUtils.isEmpty(url)) {
            imageView.setImageResource(R.drawable.iv_default)
            return
        }
        val transitionOptions = DrawableTransitionOptions().dontTransition()
        Glide.with(imageView.context).load(url).transition(transitionOptions).thumbnail(0.3f).into(
                imageView)
    }

    fun loadThumbnail(url: String, imageView: ImageView, placeHolder: Int) {
        if (TextUtils.isEmpty(url)) {
            imageView.setImageResource(R.drawable.iv_default)
            return

        }
        Glide.with(imageView.context).load(url).apply(placeholderOf(placeHolder)).into(imageView)
    }

    fun loadAvatarImage(url: String, imageView: ImageView, holderResId: Int) {
        if (TextUtils.isEmpty(url)) {
            imageView.setImageResource(R.drawable.iv_avatar_default)
            return
        }
        val transitionOptions = DrawableTransitionOptions().dontTransition()
        Glide.with(imageView.context).load(url).transition(transitionOptions).apply(placeholderOf(holderResId)).into(imageView)
    }
}
