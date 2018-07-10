package com.six.cat.sixcat.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.VideoView;

/**
 * @author liguoying Explicit
 * @date 2017/12/8.
 */

public class CustomerVideoView extends VideoView {
    public CustomerVideoView(Context context) {
        super(context.getApplicationContext());
    }

    public CustomerVideoView(Context context, AttributeSet attrs) {
        super(context.getApplicationContext(), attrs);
    }

    public CustomerVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context.getApplicationContext(), attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(getDefaultSize(0, widthMeasureSpec), getDefaultSize(0, heightMeasureSpec));
    }
}
