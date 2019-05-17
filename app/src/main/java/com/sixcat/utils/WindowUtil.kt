package com.sixcat.utils

import android.app.Activity
import android.content.Context
import android.graphics.Rect
import android.util.DisplayMetrics
import android.view.View

/**
 * 窗体操作工具
 * 界面背景颜色的修改，
 * 控件x,y坐标的获取，
 * 屏幕宽和高的获取
 * @author liguoying
 * @date 2017/12/4.
 */

class WindowUtil private constructor() {
    /**
     * 屏幕宽度
     */
    private var screenWidth: Int = 0
    /**
     * 屏幕宽度
     */
    private var screenHeight: Int = 0

    /**
     * 获取屏幕的宽
     *
     * @param context Context
     * @return 屏幕的宽
     */
    fun getScreenWidth(context: Activity?): Int {
        if (context == null) {
            return 0
        }
        if (screenWidth != 0) {
            return screenWidth
        }
        val dm = DisplayMetrics()
        context.windowManager.defaultDisplay.getMetrics(dm)
        screenWidth = dm.widthPixels
        return screenWidth
    }

    /**
     * 获取屏幕的高
     *
     * @param context Context
     * @return 屏幕的高
     */
    fun getScreenHeight(context: Activity?): Int {
        if (context == null) {
            return 0
        }
        if (screenHeight != 0) {
            return screenHeight
        }
        val dm = DisplayMetrics()
        context.windowManager.defaultDisplay.getMetrics(dm)
        screenHeight = dm.heightPixels
        return screenHeight
    }

    /**
     * 获取控件的位置
     *
     * @param view 控件View
     * @return int[] x,y
     */
    fun getViewLocation(view: View): IntArray {
        val location = IntArray(2) //获取筛选按钮的x坐标
        view.getLocationOnScreen(location)
        return location
    }

    fun getStateBarHeight(context: Context): Int {
        val rect = Rect()
        val activity = context as Activity
        activity.window.decorView.getWindowVisibleDisplayFrame(rect)
        return rect.top
    }

    private object WindowUtilInstance {
        val instance = WindowUtil()
    }

    companion object {

        /**
         * 获得单例对象
         *
         * @return
         */
        val instance: WindowUtil
            get() = WindowUtilInstance.instance
    }
}
