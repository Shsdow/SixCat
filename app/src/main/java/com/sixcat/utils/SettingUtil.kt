package com.sixcat.utils

import android.graphics.Color

import com.sixcat.R
import com.sixcat.SixCatApplication

/**
 * @author liguoying
 * @date 2017/12/11.
 */

class SettingUtil {

    /**
     * 获取是否开启无图模式
     */
    val isNoPhotoMode: Boolean
        get() = SPUtil.getBoolean("switch_noPhotoMode", false) && NetWorkUtil.isMobileConnected(SixCatApplication.context)

    /**
     * 获取主题颜色
     */
    /**
     * 设置主题颜色
     */
    var color: Int
        get() {
            val defaultColor = SixCatApplication.context.resources.getColor(R.color.colorPrimary, null)
            val color = SPUtil.getInt("color", defaultColor)
            return if (color != 0 && Color.alpha(color) != 255) {
                defaultColor
            } else color
        }
        set(color) = SPUtil.setInt("color", color)

    /**
     * 获取是否开启夜间模式
     */
    /**
     * 设置夜间模式
     */
    var isNightMode: Boolean
        get() = SPUtil.getBoolean("switch_nightMode", false)
        set(flag) = SPUtil.setBoolean("switch_nightMode", flag)

    /**
     * 获取是否开启自动切换夜间模式
     */
    /**
     * 获取是否开启自动切换夜间模式
     */
    var isAutoNightMode: Boolean
        get() = SPUtil.getBoolean("auto_nightMode", false)
        set(flag) = SPUtil.setBoolean("auto_nightMode", flag)

    var nightStartHour: String
        get() = SPUtil.getString("night_startHour", "22")
        set(nightStartHour) = SPUtil.setString("night_startHour", nightStartHour)

    var nightStartMinute: String
        get() = SPUtil.getString("night_startMinute", "00")
        set(nightStartMinute) = SPUtil.setString("night_startMinute", nightStartMinute)

    var dayStartHour: String
        get() = SPUtil.getString("day_startHour", "06")
        set(dayStartHour) = SPUtil.setString("day_startHour", dayStartHour)

    var dayStartMinute: String
        get() = SPUtil.getString("day_startMinute", "00")
        set(dayStartMinute) = SPUtil.setString("day_startMinute", dayStartMinute)

    /**
     * 获取是否开启导航栏上色
     */
    val navBar: Boolean
        get() = SPUtil.getBoolean("nav_bar", false)

    /**
     * 获取是否开启视频强制横屏
     */
    val isVideoForceLandscape: Boolean
        get() = SPUtil.getBoolean("video_force_landscape", false)

    /**
     * 获取图标值
     */
    val customIconValue: Int
        get() {
            val s = SPUtil.getString("custom_icon", "0")
            return Integer.parseInt(s)
        }

    /**
     * 获取滑动返回值
     */
    val slidable: Int
        get() {
            val s = SPUtil.getString("slidable", "1")
            return Integer.parseInt(s)
        }

    /**
     * 获取是否开启视频自动播放
     */
    val isVideoAutoPlay: Boolean
        get() = SPUtil.getBoolean("video_auto_play", false) && NetWorkUtil.isWifiConnected(SixCatApplication.context)

    /**
     * 获取字体大小
     */
    /**
     * 设置字体大小
     */
    var textSize: Int
        get() = SPUtil.getInt("textsize", 16)
        set(textSize) = SPUtil.setInt("textsize", textSize)

    var isFirstTime: Boolean
        get() = SPUtil.getBoolean("first_time", true)
        set(flag) = SPUtil.setBoolean("first_time", flag)

    private object SettingsUtilInstance {
        val instance = SettingUtil()
    }

    companion object {
        val instance: SettingUtil
            get() = SettingsUtilInstance.instance
    }

}

