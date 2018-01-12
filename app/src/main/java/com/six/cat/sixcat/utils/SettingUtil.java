package com.six.cat.sixcat.utils;

import android.graphics.Color;

import com.six.cat.sixcat.R;
import com.six.cat.sixcat.SixCatApplication;

/**
 * @author liguoying
 * @date 2017/12/11.
 */

public class SettingUtil {

    private static final class SettingsUtilInstance {
        private static final SettingUtil instance = new SettingUtil();
    }

    public static SettingUtil getInstance() {
        return SettingsUtilInstance.instance;
    }

    /**
     * 获取是否开启无图模式
     */
    public boolean getIsNoPhotoMode() {
        return SPUtil.getBoolean("switch_noPhotoMode", false) && NetWorkUtil.isMobileConnected(SixCatApplication.getInstance());
    }

    /**
     * 获取主题颜色
     */
    public int getColor() {
        int defaultColor = SixCatApplication.getInstance().getResources().getColor(R.color.colorPrimary, null);
        int color = SPUtil.getInt("color", defaultColor);
        if ((color != 0) && Color.alpha(color) != 255) {
            return defaultColor;
        }
        return color;
    }

    /**
     * 设置主题颜色
     */
    public void setColor(int color) {
        SPUtil.setInt("color", color);
    }

    /**
     * 获取是否开启夜间模式
     */
    public boolean getIsNightMode() {
        return SPUtil.getBoolean("switch_nightMode", false);
    }

    /**
     * 设置夜间模式
     */
    public void setIsNightMode(boolean flag) {
        SPUtil.setBoolean("switch_nightMode", flag);
    }

    /**
     * 获取是否开启自动切换夜间模式
     */
    public boolean getIsAutoNightMode() {
        return SPUtil.getBoolean("auto_nightMode", false);
    }

    /**
     * 获取是否开启自动切换夜间模式
     */
    public void setIsAutoNightMode(boolean flag) {
        SPUtil.setBoolean("auto_nightMode", flag);
    }

    public String getNightStartHour() {
        return SPUtil.getString("night_startHour", "22");
    }

    public void setNightStartHour(String nightStartHour) {
        SPUtil.setString("night_startHour", nightStartHour);
    }

    public String getNightStartMinute() {
        return SPUtil.getString("night_startMinute", "00");
    }

    public void setNightStartMinute(String nightStartMinute) {
        SPUtil.setString("night_startMinute", nightStartMinute);
    }

    public String getDayStartHour() {
        return SPUtil.getString("day_startHour", "06");
    }

    public void setDayStartHour(String dayStartHour) {
        SPUtil.setString("day_startHour", dayStartHour);
    }

    public String getDayStartMinute() {
        return SPUtil.getString("day_startMinute", "00");
    }

    public void setDayStartMinute(String dayStartMinute) {
        SPUtil.setString("day_startMinute", dayStartMinute);
    }

    /**
     * 获取是否开启导航栏上色
     */
    public boolean getNavBar() {
        return SPUtil.getBoolean("nav_bar", false);
    }

    /**
     * 获取是否开启视频强制横屏
     */
    public boolean getIsVideoForceLandscape() {
        return SPUtil.getBoolean("video_force_landscape", false);
    }

    /**
     * 获取图标值
     */
    public int getCustomIconValue() {
        String s = SPUtil.getString("custom_icon", "0");
        return Integer.parseInt(s);
    }

    /**
     * 获取滑动返回值
     */
    public int getSlidable() {
        String s = SPUtil.getString("slidable", "1");
        return Integer.parseInt(s);
    }

    /**
     * 获取是否开启视频自动播放
     */
    public boolean getIsVideoAutoPlay() {
        return SPUtil.getBoolean("video_auto_play", false) && NetWorkUtil.isWifiConnected(SixCatApplication.getInstance());
    }

    /**
     * 获取字体大小
     */
    public int getTextSize() {
        return SPUtil.getInt("textsize", 16);
    }

    /**
     * 设置字体大小
     */
    public void setTextSize(int textSize) {
        SPUtil.setInt("textsize", textSize);
    }

    public boolean getIsFirstTime() {
        return SPUtil.getBoolean("first_time", true);
    }

    public void setIsFirstTime(boolean flag) {
        SPUtil.setBoolean("first_time", flag);
    }

}

