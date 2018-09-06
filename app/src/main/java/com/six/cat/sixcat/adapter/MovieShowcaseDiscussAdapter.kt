package com.six.cat.sixcat.adapter

import android.content.Context
import android.graphics.drawable.BitmapDrawable
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.six.cat.sixcat.R
import com.six.cat.sixcat.SixCatApplication
import com.six.cat.sixcat.model.bean.MovieShowcaseBean

/**
 * @author liguoying
 * @date 2018/2/1.
 */
class MovieShowcaseDiscussAdapter(mContext: Context, data: List<MovieShowcaseBean.PopularCommentsBean>?) : BaseQuickAdapter<MovieShowcaseBean.PopularCommentsBean, BaseViewHolder>(R.layout.item_movie_showcase_comments, data) {
    private var drawable: BitmapDrawable? = null
    private var mContext1: Context? = null

    init {
        this.mContext1 = mContext
        drawable = mContext.getResources().getDrawable(R.drawable.ic_like, null) as BitmapDrawable
        val bitmap = drawable!!.bitmap
        drawable!!.setBounds(0, 0, bitmap.width / 2, bitmap.height / 2)
    }

    override fun convert(helper: BaseViewHolder, item: MovieShowcaseBean.PopularCommentsBean) {
        Glide.with(SixCatApplication.getInstance()).load(item.author?.avatar).into(helper.getView<ImageView>(R.id.civCommentImg))
//        helper.setText(R.id.rbStarNum, item.rating?.value.toString())
        helper.getView<RatingBar>(R.id.rbStarNum).rating = item.rating!!.value.toFloat()/2
        helper.setText(R.id.tvCommentName, item.author?.name)
        helper.setText(R.id.tvCommentWords, item.content)
        helper.setText(R.id.tvCommentSupportNum, item.useful_count.toString())
        helper.setText(R.id.tvComment_date, item.created_at)
        helper.getView<TextView>(R.id.tvCommentSupportNum).setCompoundDrawables(drawable, null, null, null)
    }

}