package com.six.cat.sixcat.utils;

import android.text.TextUtils;
import android.widget.ImageView;

import static com.bumptech.glide.request.RequestOptions.placeholderOf;
import static com.bumptech.glide.request.RequestOptions.skipMemoryCacheOf;

import com.bumptech.glide.Glide;
import com.bumptech.glide.TransitionOptions;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.six.cat.sixcat.R;

/**
 * @author liguoying
 * @date 2017/12/4.
 */
public class ImageUtil {
    public static void loadImage(String url, ImageView imageView) {
        if (TextUtils.isEmpty(url)) {
            imageView.setImageResource(R.drawable.iv_default);
            return;
        }
        Glide.with(imageView.getContext()).load(url).into(imageView);
    }

    public static void loadImageWithoutCache(String url, ImageView imageView) {
        if (TextUtils.isEmpty(url)) {
            imageView.setImageResource(R.drawable.iv_default);
            return;
        }
        Glide.with(imageView.getContext()).load(url)/*
                .skipMemoryCache(true).into(imageView)*/;
    }

    public static void loadImage(String url, ImageView imageView, int holderResId) {
        if (TextUtils.isEmpty(url)) {
            imageView.setImageResource(R.drawable.iv_default);
            return;
        }
        Glide.with(imageView.getContext()).load(url)/*.placeholder(holderResId)*/.into(imageView);
    }

    public static void loadImageWithBitmap(String url, ImageView imageView, int holderResId) {
        if (TextUtils.isEmpty(url)) {
            imageView.setImageResource(R.drawable.iv_default);
            return;
        }
        Glide.with(imageView.getContext()).load(url).apply(placeholderOf(holderResId)).into(imageView);
    }

    public static void loadImageWithoutCache(String url, ImageView imageView, int holderResId) {
        if (TextUtils.isEmpty(url)) {
            imageView.setImageResource(R.drawable.iv_default);
            return;
        }
        Glide.with(imageView.getContext()).load(url).apply(skipMemoryCacheOf(true)).apply(placeholderOf(holderResId))
                .into(imageView);
    }

    public static void loadThumbnail(String url, ImageView imageView) {
        if (TextUtils.isEmpty(url)) {
            imageView.setImageResource(R.drawable.iv_default);
            return;
        }
        TransitionOptions transitionOptions = new DrawableTransitionOptions().dontTransition();
        Glide.with(imageView.getContext()).load(url).transition(transitionOptions).thumbnail(0.3f).into(
                imageView);
    }

    public static void loadThumbnail(String url, ImageView imageView, int placeHolder) {
        if (TextUtils.isEmpty(url)) {
            imageView.setImageResource(R.drawable.iv_default);
            return;

        }
        Glide.with(imageView.getContext()).load(url).apply(placeholderOf(placeHolder)).into(
                imageView);
    }

    public static void loadAvatarImage(String url, ImageView imageView, int holderResId) {
        if (TextUtils.isEmpty(url)) {
            imageView.setImageResource(R.drawable.iv_avatar_default);
            return;
        }
        TransitionOptions transitionOptions = new DrawableTransitionOptions().dontTransition();
        Glide.with(imageView.getContext()).load(url).transition(transitionOptions).apply(placeholderOf(holderResId)).into(
                imageView);
    }
}
