package com.sixcat.presenter.contract

import com.hazz.kotlinmvp.net.exception.ExceptionHandle
import com.sixcat.base.refactor.BasePresenter
import com.sixcat.model.VideoModel
import com.sixcat.presenter.VideoContract

/**
 * @author liguoying
 * @date 2018/2/6.
 */
class VideoPresenter : BasePresenter<VideoContract.View>(), VideoContract.Presenter {

    private val videoModel by lazy { VideoModel() }

    override fun getVideoChannel(mPage: Int) {
        val disposable = videoModel.getVideoChannel(mPage)
                .subscribe({ list ->
                    mRootView?.apply {
                        setVideoChanel(list)
                    }
                }, { throwable ->
                    mRootView?.apply {
                        showError(ExceptionHandle.handleException(throwable), ExceptionHandle.errorCode)
                    }
                })

        addSubscription(disposable)
    }

    override fun getVideoDetails(page: Int, listType: String, typeId: String) {
        videoModel.getVideoDetails(page, listType, typeId)
                .subscribe({ list ->
                    mRootView?.apply {
                        loadVideoDetails(list)
                    }
                }, { throwable ->
                    mRootView?.apply {
                        showError(ExceptionHandle.handleException(throwable), ExceptionHandle.errorCode)
                    }

                })
//                .subscribe(object : BaseObserver<List<VideoDetailBean>>() {
//                    override fun onSuccess(t: List<VideoDetailBean>?) {
//                        when {
//                            page > 1 -> mView!!.loadMoreVideoDetails(t)
//                            else -> mView!!.loadVideoDetails(t)
//                        }
//                    }
//
//                    override fun onFail(e: Throwable) {
//                        mView!!.onShowNetError()
//                    }
//
//                })
    }


    override fun doNotShowMore() {
    }


}