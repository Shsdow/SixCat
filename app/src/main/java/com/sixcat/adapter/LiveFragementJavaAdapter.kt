package com.sixcat.adapter

import android.view.View
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.sixcat.R
import com.sixcat.SixCatApplication
import com.sixcat.model.bean.MovieBean
import com.sixcat.widgets.GlideCircleTransform
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * @author liguoying
 * @date 2018/1/16.
 * 对应的 kotlin 代码 [com.six.cat.sixcat.adapter.LiveFragementJavaAdapter]
 */

class LiveFragementJavaAdapter(data: List<MovieBean.SubjectsBean>?, private val currentTime: Long = System.currentTimeMillis()) : BaseQuickAdapter<MovieBean.SubjectsBean, BaseViewHolder>(R.layout.item_live, data) {


    private val mSimpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.CHINA)


    override fun convert(helper: BaseViewHolder, item: MovieBean.SubjectsBean) {
        try {
            helper.addOnClickListener(R.id.cv_item)
            Glide.with(mContext).load(item.images!!.medium)
                    .apply(RequestOptions.bitmapTransform(GlideCircleTransform.getInstance(SixCatApplication.context, 4))).into(helper.getView<View>(R.id.iv_post_card) as ImageView)
            helper.setText(R.id.tv_item_title, item.title)
            helper.setText(R.id.tv_director,
                    String.format(Locale.CHINA, SixCatApplication.context.resources.getString(R.string.showing_movie_director), item.directors!![0].name))
            val actors = StringBuilder()
            val castsBeanList = item.casts
            val size = castsBeanList!!.size - 1
            for (i in 0..size) {
                actors.append(castsBeanList[i].name).append(if (i == size) "" else "/")
            }
            helper.setText(R.id.tv_actor, String.format(Locale.CHINA, SixCatApplication.context.resources.getString(R.string.showing_movie_actors), actors.toString()))
            if (mSimpleDateFormat.parse(item.mainland_pubdate).time > currentTime) {
                helper.setText(R.id.tv_port_status, "预售")
                (helper.getView<View>(R.id.tv_not_on_show) as TextView).visibility = View.VISIBLE
                (helper.getView<View>(R.id.ll_star_show) as TextView).visibility = View.GONE

            } else {
                (helper.getView<View>(R.id.tv_not_on_show) as TextView).visibility = View.GONE
                (helper.getView<View>(R.id.ll_star_show) as TextView).visibility = View.VISIBLE
                helper.setText(R.id.tv_port_status, "购票")
                helper.setText(R.id.tv_star_num, item.rating!!.average.toString())
                (helper.getView<View>(R.id.rb_star) as RatingBar).rating = item.rating!!.average.toFloat() / 2.0f
            }

            helper.setText(R.id.tv_want_to_watch_number, String.format(SixCatApplication.context.getString(R.string.number_want_to_watch_movie), item.collect_count.toString()))
        } catch (e: ParseException) {
            e.printStackTrace()
        }

    }
}
