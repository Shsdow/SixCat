package com.sixcat.adapter

import android.content.Context
import android.view.View
import androidx.annotation.LayoutRes
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.sixcat.R
import com.sixcat.model.bean.VideoDetailBean
import com.sixcat.utils.conversionPlayTime
import com.sixcat.utils.conversionTime
import fm.jiecao.jcvideoplayer_lib.JCUserAction
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard

/**
 * 视频详情
 * @author liguoying
 * @date 2018/2/7.
 */
class VideoDetailAdapter(private var context: Context, @LayoutRes layoutResId: Int, data: List<VideoDetailBean.ItemBean>?)
    : BaseQuickAdapter<VideoDetailBean.ItemBean, BaseViewHolder>(layoutResId, data) {
    override fun convert(helper: BaseViewHolder, item: VideoDetailBean.ItemBean) {
        val jcVideoPlayerStandard = helper.getView<JCVideoPlayerStandard>(R.id.videoplayer)
        jcVideoPlayerStandard.setUp(item.video_url, JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, item.title)
        JCVideoPlayer.setJcUserAction { type, _, _, _ ->
            when (type) {
                JCUserAction.ON_CLICK_START_ICON -> {
                    helper.getView<View>(R.id.tv_videoduration).visibility = View.GONE
                }
            }
//            jcVideoPlayerStandard.thumbImageView.setImageURI(Uri.parse(item.thumbnail))
            Glide.with(context).load(item.thumbnail).apply(RequestOptions()
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL))
                    .transition(DrawableTransitionOptions().crossFade(800)).into(jcVideoPlayerStandard.thumbImageView)
            helper.setText(R.id.tv_videoduration, conversionTime(item.duration))
            item.playTime?.let {
                helper.setText(R.id.tv_playtime, conversionPlayTime(Integer.valueOf(it)!!))
            }
        }
    }
}