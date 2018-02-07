package com.six.cat.sixcat.module.base

/**
 * @author liguoying
 * @date 2017/12/27.
 */

interface IBasePresenter {

    /**
     * 刷新数据
     */
    fun doRefresh()

    /**
     * 显示网络错误
     */
    fun doShowNetError()
}
