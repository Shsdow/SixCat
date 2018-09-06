package com.six.cat.sixcat.base.refactor

/**
 * Author：Administrator
 * Data: 2018/9/6 0006 10:51
 */
interface IBasePresenter<in V : IBaseView> {
    fun attachView(mRootView: V)

    fun detachView()
}