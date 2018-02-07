package com.six.cat.sixcat.module.video

import com.six.cat.sixcat.RetrofitFactory
import com.six.cat.sixcat.api.ApiContants
import com.six.cat.sixcat.api.VideoApi
import com.six.cat.sixcat.base.BaseObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * @author liguoying
 * @date 2018/2/6.
 */
class ViewPresenter(val mView: IVideoInterfaceManager.IVideoView?) : IVideoInterfaceManager.IVideoPresenter {
    override fun doLoadData() {
        RetrofitFactory.getRetrofit().create(VideoApi::class.java).getVideoChannel()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : BaseObserver<List<VideoChannelBean>>() {
                    override fun onSuccess(t: List<VideoChannelBean>?) {
                        mView?.doSetVideoData(t)
                    }
                    override fun onFail(e: Throwable) {
                        mView?.onShowNetError()
                    }
                })
    }

    override fun doNotShowMore() {
    }

    override fun doRefresh() {
    }

    override fun doShowNetError() {
    }
}