package com.sixcat.presenter.contract

import com.hazz.kotlinmvp.net.exception.ExceptionHandle
import com.sixcat.base.refactor.BasePresenter
import com.sixcat.model.LiveModel
import com.sixcat.module.live.LiveContrace

/**
 * @author liguoying
 * @date 2018/1/12.
 */

class LiveContentPresenter : BasePresenter<LiveContrace.View>(), LiveContrace.Presenter {

    private var count = 10
    private var totalSize = 0
    private var start = 0
    private val liveModel by lazy { LiveModel() }

    override fun loadData(city: String, start: Int, count: Int) {
        checkViewAttached()
        val disposable = liveModel.getLiveData(city, start, count)
                .map { liveBean ->
                    mRootView?.dismissLoading()
                    totalSize = liveBean.total
                    liveBean.subjects
                }
                .filter { it.isNotEmpty() }
                .subscribe({ subjectsBeans ->
                    mRootView?.apply {
                        setLiveData(subjectsBeans, start, totalSize)
                    }
                }, { throwable ->
                    mRootView?.apply {
                        showError(ExceptionHandle.handleException(throwable), ExceptionHandle.errorCode)
                    }
                })
        //添加到订阅中
        addSubscription(disposable)
    }

    override fun loadMoreData() {
        start += 10
        count = if (start >= totalSize) totalSize % count else count
        loadData("北京", start, count)
    }
}
