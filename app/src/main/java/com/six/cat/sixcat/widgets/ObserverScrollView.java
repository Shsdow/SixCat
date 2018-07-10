package com.six.cat.sixcat.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * @author liguoying
 * @date 2018/1/29.
 */

public class ObserverScrollView extends ScrollView {
    private IScrollViewLisenter mIScrollViewLisenter;

    public ObserverScrollView(Context context) {
        super(context);
    }

    public ObserverScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ObserverScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (mIScrollViewLisenter != null) {
            if (oldt < t) {
                mIScrollViewLisenter.onScroll(oldt, t, false);
            } else if (oldt > t) {
                mIScrollViewLisenter.onScroll(oldt, t, true);
            }
        }
    }

    public void setIScrollViewLisenter(IScrollViewLisenter scrollViewLisenter) {
        mIScrollViewLisenter = scrollViewLisenter;
    }

    public interface IScrollViewLisenter {
        void onScroll(int oldy, int dy, boolean isUp);
    }
}
