package com.six.cat.sixcat.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.six.cat.sixcat.R;

/**
 * @author liguoying
 * @date 2017/12/25.
 */

public class MainTitleAdapter extends PagerAdapter implements View.OnClickListener {
    private Context context;
    private int[] images = new int[]{R.mipmap.speech_title, R.mipmap.branch_title, R.mipmap.activity_title, R.mipmap.record_title};
    private OnItemClickLitener mOnItemClickLitener;

    public interface OnItemClickLitener {
        void onLeftClick(View view, int i);

        void onRightClick(View view, int i);
    }

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    public MainTitleAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return this.images.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(this.context).inflate(R.layout.main_title_item, null);
        ((ImageView) view.findViewById(R.id.main_title_image)).setImageResource(this.images[position]);
        View left = view.findViewById(R.id.main_title_left);
        View right = view.findViewById(R.id.main_title_right);
        left.setOnClickListener(this);
        left.setTag(Integer.valueOf(position));
        right.setOnClickListener(this);
        right.setTag(Integer.valueOf(position));
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public void onClick(View v) {
        if (this.mOnItemClickLitener != null) {
            switch (v.getId()) {
                case R.id.main_title_left:
                    this.mOnItemClickLitener.onLeftClick(v, ((Integer) v.getTag()).intValue());
                    return;
                case R.id.main_title_right:
                    this.mOnItemClickLitener.onRightClick(v, ((Integer) v.getTag()).intValue());
                    return;
                default:
                    return;
            }
        }
    }
}

