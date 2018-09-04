package com.six.cat.sixcat.presenter

import com.six.cat.sixcat.RetrofitFactory
import com.six.cat.sixcat.base.BaseObserver
import com.six.cat.sixcat.model.VideoChannelBean
import com.six.cat.sixcat.model.VideoDetailBean
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * @author liguoying
 * @date 2018/2/6.
 */
class VideoPresenter(val mView: IVideoInterfaceManager.IVideoView?) : IVideoInterfaceManager.IVideoPresenter {


    override fun getVideoChannel() {
        RetrofitFactory.getRetrofit().create(VideoApi::class.java).getVideoChannel()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : BaseObserver<List<VideoChannelBean>>() {
                    override fun onSuccess(t: List<VideoChannelBean>?) {
                        mView?.dosetVideoChanel(t)
                    }

                    override fun onFail(e: Throwable) {
                        mView?.onShowNetError()
                    }
                })
    }

    override fun getVideoDetails(page: Int, listType: String, typeId: String) {

        RetrofitFactory.getRetrofit().create(VideoApi::class.java).getVideoDetail(page, listType, typeId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : BaseObserver<List<VideoDetailBean>>() {
                    override fun onSuccess(t: List<VideoDetailBean>?) {
                        when {
                            page > 1 -> mView!!.loadMoreVideoDetails(t)
                            else -> mView!!.loadVideoDetails(t)
                        }
                    }
                    override fun onFail(e: Throwable) {
                        mView!!.onShowNetError()
                    }

                })
    }



    override fun doNotShowMore() {
    }

    override fun loadData() {
    }

    override fun doShowNetError() {
    }
}