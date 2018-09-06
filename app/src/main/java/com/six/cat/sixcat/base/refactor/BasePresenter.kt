package com.six.cat.sixcat.base.refactor

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Author：Administrator
 * Data: 2018/9/4 0004 14:52
 */
open class BasePresenter<T : IBaseView> : IBasePresenter<T> {

    var mRootView: T? = null
        private set

    private var compositeDisposable = CompositeDisposable()

    override fun attachView(mRootView: T) {
        this.mRootView = mRootView
    }

    override fun detachView() {
        mRootView = null
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.clear()
        }
    }

    private val isViewAttach: Boolean
        get() = mRootView != null


    fun checkViewAttached() {
        if (!isViewAttach) throw ViewNotAttachException()
    }

    fun addSubscription(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    private class ViewNotAttachException internal constructor() : RuntimeException("Please call IPresenter.attachView(IBaseView) before\" + \" requesting data to the IPresenter")
}
