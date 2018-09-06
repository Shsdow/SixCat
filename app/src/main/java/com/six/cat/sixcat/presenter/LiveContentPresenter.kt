package com.six.cat.sixcat.presenter

import com.hazz.kotlinmvp.net.exception.ExceptionHandle
import com.six.cat.sixcat.base.refactor.BasePresenter
import com.six.cat.sixcat.model.LiveModel
import com.six.cat.sixcat.module.live.LiveContrace
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

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
                .subscribe({ subjectsBeans ->
                    mRootView?.apply {
                        setLiveData(subjectsBeans, totalSize)
                    }
                }, { throwable ->
                    mRootView?.apply {
                        showError(ExceptionHandle.handleException(throwable), ExceptionHandle.errorCode)
                    }
                })

        addSubscription(disposable)
    }


    override fun loadMoreData() {
    }

    fun doRefresh() {
        start = 0
        count = 10
        loadData("",start,count)
    }

    fun doLoadMoreData() {
        start += 10
        count = if (start >= totalSize) totalSize % count else count
        loadData("",start,count)
    }

//    fun doShowNetError() {
//        mView!!.onHideLoading()
//        mView.onShowNetError()
//    }
}
