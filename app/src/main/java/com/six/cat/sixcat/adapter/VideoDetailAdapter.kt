package com.six.cat.sixcat.adapter

import android.content.Context
import android.support.annotation.LayoutRes
import android.view.View
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.six.cat.sixcat.R
import com.six.cat.sixcat.bean.VideoDetailBean
import com.six.cat.sixcat.utils.conversionPlayTime
import com.six.cat.sixcat.utils.conversionTime
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
            Glide.with(context).load(item.image).into(jcVideoPlayerStandard.thumbImageView)
            helper.setText(R.id.tv_videoduration, conversionTime(item.duration))
            item.playTime?.let {
                helper.setText(R.id.tv_playtime, conversionPlayTime(Integer.valueOf(it)!!))
            }
        }
    }
}