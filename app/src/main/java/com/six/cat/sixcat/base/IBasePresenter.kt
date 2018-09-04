package com.six.cat.sixcat.base

/**
 * @author liguoying
 * @date 2017/12/27.
 */

interface IBasePresenter {

    /**
     * 请求数据
     */
    fun loadData()

    /**
     * 显示网络错误
     */
    fun doShowNetError()
}
