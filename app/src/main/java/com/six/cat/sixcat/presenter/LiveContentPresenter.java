package com.six.cat.sixcat.presenter;

import com.six.cat.sixcat.RetrofitFactory;
import com.six.cat.sixcat.model.LiveBean;
import com.six.cat.sixcat.module.live.ILiveInterface;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author liguoying
 * @date 2018/1/12.
 */

public class LiveContentPresenter implements ILiveInterface.ILivePresenter {

    private ILiveInterface.ILiveView mView;
    private int count = 10;
    private int totalSize = 0;
    private int start = 0;

    public LiveContentPresenter(ILiveInterface.ILiveView mView) {
        this.mView = mView;
    }

    @Override
    public void loadData() {
        mView.onShowLoading();
        RetrofitFactory.getRetrofit().create(ILiveApi.class).getLiveContent("北京", start, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(liveBean -> {
                    totalSize = liveBean.getTotal();
                    return liveBean.getSubjects();
                })
                .compose(mView.<List<LiveBean.SubjectsBean>>bindToLife())
                .subscribe(subjectsBeans -> {
                    if (subjectsBeans.size() > 0) {
                        mView.onLoadDataSuccess(subjectsBeans, totalSize);
                    } else {
                        mView.haveNoMore();
                    }
                }, throwable -> doShowNetError());
    }

    @Override
    public void doRefresh() {
        start = 0;
        count = 10;
        mView.onShowLoading();
        loadData();
    }

    @Override
    public void doLoadMoreData() {
        start += 10;
        count = (start >= totalSize) ? totalSize % count : count;
        loadData();
    }

    @Override
    public void doShowNetError() {
        mView.onHideLoading();
        mView.onShowNetError();
    }
}
