package com.six.cat.sixcat.utils

import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.NumberFormat

/**
 * @author liguoying
 * @date 2018/2/7.
 */
fun conversionTime(duration: Int): String {
    val minutes = duration / 60
    val seconds = duration - minutes * 60
    val m = if (sizeOf(minutes) > 1) minutes.toString() else "0" + minutes
    val s = if (sizeOf(seconds) > 1) seconds.toString() else "0" + seconds
    return m + ":" + s
}

/**
 * 判断是几位数字
 *
 * @param size
 * @return
 */
fun sizeOf(size: Int): Int {
    val sizeTable = intArrayOf(9, 99, 999, 9999, 99999, 999999, 9999999, 99999999, 999999999, Integer.MAX_VALUE)
    var i = 0
    while (true) {
        if (size <= sizeTable[i])
            return i + 1
        i++
    }
}

fun conversionPlayTime(playtime: Int): String {
    return if (sizeOf(playtime) > 4) {
        accuracy(playtime.toDouble(), 10000.0, 1) + "万"
    } else {
        playtime.toString()
    }
}

fun accuracy(num: Double, total: Double, digit: Int): String {
    val df = NumberFormat.getInstance() as DecimalFormat
    //可以设置精确几位小数
    df.maximumFractionDigits = digit
    //模式 例如四舍五入
    df.roundingMode = RoundingMode.HALF_UP
    val accuracy_num = num / total
    return df.format(accuracy_num)
}