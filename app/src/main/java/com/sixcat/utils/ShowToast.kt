package com.sixcat.utils

import android.text.TextUtils
import android.view.Gravity
import android.view.View
import android.widget.TextView
import android.widget.Toast

import com.sixcat.R
import com.sixcat.SixCatApplication

/**
 * Toast 工具类
 *
 * @author liguoying
 * @date 2017/12/4.
 */

object ShowToast {
    private var toast: Toast? = null
    private var tvText: TextView? = null

    fun shortTime(resId: Int) {
        if (SixCatApplication.context == null) {
            return
        }
        shortTime(SixCatApplication.context.getString(resId))
    }

    fun shortTime(text: String) {
        if (SixCatApplication.context == null) {
            return
        }
        if (TextUtils.isEmpty(text)) {
            return
        }
        if (toast == null) {
            val view = View.inflate(SixCatApplication.context, R.layout.layout_toast_body, null)
            tvText = view.findViewById<View>(R.id.tv_toast) as TextView
            tvText!!.text = text
            toast = Toast.makeText(SixCatApplication.context, text,
                    Toast.LENGTH_SHORT)
            toast!!.setGravity(Gravity.BOTTOM, 0, DisplayUtil.dp2px(120f))
            toast!!.view = view
        } else {
            tvText!!.text = text
        }
        toast!!.show()
    }
}
