package com.six.cat.sixcat.adapter

import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RatingBar
import android.widget.TextView

import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.six.cat.sixcat.R
import com.six.cat.sixcat.SixCatApplication
import com.six.cat.sixcat.model.bean.LiveBean
import com.six.cat.sixcat.widgets.GlideCircleTransform

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.ArrayList
import java.util.Locale

/**
 * @author liguoying
 * @date 2018/1/17.
 * 对应的 Java 代码 {@link com.six.cat.sixcat.adapter.LiveFragementJavaAdapter }
 */

class LiveJavaAdapter(data: ArrayList<LiveBean.SubjectsBean>?) : BaseQuickAdapter<LiveBean.SubjectsBean, BaseViewHolder>(R.layout.item_live, data) {

    private val currentTime: Long = System.currentTimeMillis()
    private val mSimpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.CHINA)

    override fun convert(helper: BaseViewHolder, item: LiveBean.SubjectsBean) {
        try {
            helper.addOnClickListener(R.id.cv_item)
            Glide.with(mContext).load(item.images?.medium)
                    .apply(RequestOptions.bitmapTransform(GlideCircleTransform(SixCatApplication.context, 4))).into(helper.getView<ImageView>(R.id.iv_post_card))
            helper.setText(R.id.tv_item_title, item.title)
            helper.setText(R.id.tv_director,
                    String.format(Locale.CHINA, SixCatApplication.context.resources.getString(R.string.showing_movie_director), item.directors?.get(0)?.name))
            val actors = StringBuilder()
            val castsBeanList = item.casts
            val size = castsBeanList?.size?.minus(1)
            for (i in 0..size!!) {
                actors.append(castsBeanList[i].name).append(if (i == size) "" else "/")
            }
            helper.setText(R.id.tv_actor, String.format(Locale.CHINA, SixCatApplication.context.resources.getString(R.string.showing_movie_actors), actors.toString()))
            helper.setText(R.id.tv_want_to_watch_number, String.format(SixCatApplication.context.getString(R.string.number_want_to_watch_movie), item.collect_count.toString()))
            when (mSimpleDateFormat.parse(item.mainland_pubdate).time > this.currentTime) {
                true -> {
                    helper.setText(R.id.tv_port_status, "预售")
                    (helper.getView<View>(R.id.tv_not_on_show) as TextView).visibility = View.VISIBLE// as 转型
                    (helper.getView<LinearLayout>(R.id.ll_star_show)).visibility = View.GONE
                }
                false -> {
                    (helper.getView<TextView>(R.id.tv_not_on_show)).visibility = View.GONE
                    (helper.getView<LinearLayout>(R.id.ll_star_show)).visibility = View.VISIBLE
                    helper.setText(R.id.tv_port_status, "购票")
                    helper.setText(R.id.tv_star_num, item.rating?.average.toString())
                    (helper.getView<RatingBar>(R.id.rb_star)).rating = item.rating?.average?.toFloat()!! / 2.0f
                }
            }
        } catch (e: ParseException) {
            e.printStackTrace()
        }
    }
}

