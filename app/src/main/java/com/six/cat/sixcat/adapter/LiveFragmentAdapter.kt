package com.six.cat.sixcat.adapter

import android.content.Context
import android.widget.RatingBar
import android.widget.TextView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.six.cat.sixcat.R
import com.six.cat.sixcat.bean.LiveBean
import com.six.cat.sixcat.utils.LogUtil
import java.text.SimpleDateFormat
import java.util.*

/**
 * @author liguoying
 * @date 2018/1/12.
 */

class LiveFragmentAdapter(val context: Context, data: List<LiveBean.SubjectsBean>?) : BaseQuickAdapter<LiveBean.SubjectsBean, BaseViewHolder>(R.layout.item_live, data) {
    private var currentTime: Long = System.currentTimeMillis()

    override fun convert(helper: BaseViewHolder, item: LiveBean.SubjectsBean) {

        LogUtil.e("3634634636")
        helper.addOnClickListener(R.id.cv_item)
        Glide.with(context).load(item.images.medium).into(helper.getView(R.id.iv_post_card))
        helper.getView<TextView>(R.id.tv_item_title).text = item.title
        helper.getView<TextView>(R.id.tv_director).text = String.format(context.resources.getString(R.string.showing_movie_actors), item.directors)
        var actors = ""
        for (i in 0..item.casts.size - 1) {
            actors += item.casts[i].name + if (i == item.casts.size - 1) "" else "/"
        }
        val showTime: Long = SimpleDateFormat("yyyy-MM-dd", Locale.CHINA).parse(item.mainland_pubdate).time
        val status = when (showTime > this.currentTime) {
            false -> "购票"
            true -> "预售"
        }
        val number = when (item.collect_count.toDouble() > 10000.toDouble()) {
            true -> String.format("%.1f", item.collect_count.toDouble() / 10000)
            false -> item.collect_count.toString()
        }
        helper.getView<TextView>(R.id.tv_want_to_watch_number).text = String.format(context.getString(R.string.number_want_to_watch_movie), number)
        helper.getView<TextView>(R.id.tv_port_status).text = status
        helper.getView<TextView>(R.id.tv_star_num).text = item.rating.average.toString()
        helper.getView<TextView>(R.id.tv_actor).text = actors
        helper.getView<RatingBar>(R.id.rb_star).rating = item.rating.average.toFloat()
    }
}
