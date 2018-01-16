package com.six.cat.sixcat.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.six.cat.sixcat.R;
import com.six.cat.sixcat.SixCatApplication;
import com.six.cat.sixcat.bean.LiveBean;
import com.six.cat.sixcat.utils.GlideCircleTransform;
import com.six.cat.sixcat.utils.LogUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

/**
 * @author liguoying
 * @date 2018/1/16.
 */

public class LiveFragementJavaAdapter extends BaseQuickAdapter<LiveBean.SubjectsBean, BaseViewHolder> {

    private Long currentTime;
    private SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);

    public LiveFragementJavaAdapter(@Nullable List<LiveBean.SubjectsBean> data) {
        super(R.layout.item_live, data);
        currentTime = System.currentTimeMillis();
    }

    @Override
    protected void convert(BaseViewHolder helper, LiveBean.SubjectsBean item) {
        try {
            helper.addOnClickListener(R.id.cv_item);
            Glide.with(mContext).load(item.getImages().getMedium())
                    .apply(RequestOptions.bitmapTransform(new GlideCircleTransform(SixCatApplication.getInstance(), 4))).into((ImageView) helper.getView(R.id.iv_post_card));
            helper.setText(R.id.tv_item_title, item.getTitle());
            helper.setText(R.id.tv_director,
                    String.format(Locale.CHINA, SixCatApplication.getInstance().getResources().getString(R.string.showing_movie_actors), item.getDirectors().get(0).getName()));
            String actors = "";
            List<LiveBean.SubjectsBean.CastsBean> castsBeanList = item.getCasts();
            int size = castsBeanList.size() - 1;
            for (int i = 0; i < size; i++) {
                actors += castsBeanList.get(i).getName() + (i == size ? "" : "/");
            }
            helper.setText(R.id.tv_actor, actors);
            LogUtil.e(item.getMainland_pubdate() + "9090" + mSimpleDateFormat.parse(item.getMainland_pubdate()).getTime() + " " + currentTime);
            if ((mSimpleDateFormat.parse(item.getMainland_pubdate()).getTime() > currentTime)) {
                helper.setText(R.id.tv_port_status, "预售");
            } else {
                helper.setText(R.id.tv_port_status, "购票");
            }
            ((RatingBar) (helper.getView(R.id.rb_star))).setRating((float) item.getRating().getAverage());
            ;
            helper.setText(R.id.tv_want_to_watch_number, String.format(SixCatApplication.getInstance().getString(R.string.number_want_to_watch_movie), String.valueOf(item.getCollect_count())));
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }
}
