package com.sixcat.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.VideoView

/**
 * @author liguoying Explicit
 * @date 2017/12/8.
 */

class CustomerVideoView : VideoView {
    constructor(context: Context) : super(context.applicationContext)

    constructor(context: Context, attrs: AttributeSet) : super(context.applicationContext, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context.applicationContext, attrs, defStyleAttr)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        setMeasuredDimension(View.getDefaultSize(0, widthMeasureSpec), View.getDefaultSize(0, heightMeasureSpec))
    }
}
