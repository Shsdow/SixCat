package com.sixcat.base

import com.trello.rxlifecycle2.LifecycleTransformer

/**
 * @author liguoying
 * @date 2017/12/27.
 */

interface IBaseView<T> {

    /**
     * 设置 presenter
     */
    fun setPresenterView(presenter: T?)

    /**
     * 显示加载动画
     */
    fun onShowLoading()

    /**
     * 隐藏加载
     */
    fun onHideLoading()

    /**
     * 显示网络错误
     */
    fun onShowNetError()

    /**
     * 加载完毕
     */
    fun onShowNoMore()

    /**
     * 绑定生命周期
     */
    fun <T> bindToLife(): LifecycleTransformer<T>
}
