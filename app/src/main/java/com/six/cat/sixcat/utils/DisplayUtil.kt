package com.six.cat.sixcat.utils

import android.graphics.Paint
import androidx.core.content.ContextCompat
import android.text.SpannableString
import android.text.Spanned
import android.text.style.AbsoluteSizeSpan
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.widget.TextView

import com.six.cat.sixcat.SixCatApplication

/**
 * 尺寸转换 和 字体操作 工具类
 * @author liguoying
 * @date 2017/12/4.
 */

object DisplayUtil {
    /**
     * 将dip或dp值转换为px值，保证尺寸大小不变
     *
     * @param dipValue
     * @return
     */
    fun dp2px(dipValue: Float): Int {
        return (dipValue * SixCatApplication.context.resources.displayMetrics.density + 0.5f).toInt()
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param spValue
     * @return
     */

    fun sp2px(spValue: Float): Int {
        return (spValue * SixCatApplication.context.resources.displayMetrics.scaledDensity + 0.5f).toInt()
    }

    /**
     * 改变字符串中个别字体大小
     *
     * @param text
     * @param textSize 要改变的字体大小（sp）
     * @param isDip    字体单位是否是dip
     * @param start    开始位置
     * @param end      结束位置 （前包后不包）
     * @return
     */
    fun changeTextSize(text: String, textSize: Int, isDip: Boolean,
                       start: Int, end: Int): SpannableString {
        val sp = SpannableString(text)
        sp.setSpan(AbsoluteSizeSpan(textSize, isDip), start, end,
                Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
        return sp
    }

    /**
     * 改变字符串中个别字体加粗
     *
     * @param text
     * @param start 开始位置
     * @param end   结束位置 （前包后不包）
     * @return
     */
    fun changeTextBold(text: String, start: Int, end: Int): SpannableString {
        val sp = SpannableString(text)
        sp.setSpan(StyleSpan(android.graphics.Typeface.BOLD), start, end,
                Spanned.SPAN_INCLUSIVE_EXCLUSIVE)

        return sp
    }

    /**
     * 改变字符串中个别字体颜色
     *
     * @param text
     * @param colorId 颜色id
     * @param start   开始位置
     * @param end     结束位置 （前包后不包）
     * @return
     */
    fun changeTextColor(text: String, colorId: Int, start: Int, end: Int): SpannableString {
        val sp = SpannableString(text)
        sp.setSpan(ForegroundColorSpan(
                ContextCompat.getColor(SixCatApplication.context, colorId)), start, end,
                Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
        return sp
    }

    /**
     * 设置TextView下划线
     */
    fun setTextViewUnderLine(textView: TextView) {
        textView.paint.flags = Paint.UNDERLINE_TEXT_FLAG
        textView.paint.isAntiAlias = true
    }

    /**
     * 设置TextView删除线
     */
    fun setTextViewDeleteLine(textView: TextView) {
        textView.paint.flags = Paint.STRIKE_THRU_TEXT_FLAG
        textView.paint.isAntiAlias = true
    }
}
