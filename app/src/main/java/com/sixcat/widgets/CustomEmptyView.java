package com.sixcat.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.sixcat.R;

/**
 * @author liguoying
 * @date 2018/3/16.
 */

public class CustomEmptyView extends FrameLayout {
    private ImageView mEmptyImg;
    private TextView mEmptyText;

    public CustomEmptyView(Context context) {
        this(context, null);
    }

    public CustomEmptyView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomEmptyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_empty, this,true);
        mEmptyImg = view.findViewById(R.id.empty_img);
        mEmptyText = view.findViewById(R.id.empty_text);
    }

    public void setEmptyImage(int imgRes) {
        mEmptyImg.setImageResource(imgRes);
    }

    public void setEmptyText(String text) {
        mEmptyText.setText(text);
    }
}
