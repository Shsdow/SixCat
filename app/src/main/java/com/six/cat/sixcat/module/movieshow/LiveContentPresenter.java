package com.six.cat.sixcat.module.movieshow;

import com.six.cat.sixcat.RetrofitFactory;
import com.six.cat.sixcat.api.ILiveApi;
import com.six.cat.sixcat.bean.LiveBean;
import com.six.cat.sixcat.module.live.ILiveInterface;
import com.six.cat.sixcat.widget.ErrorAction;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author liguoying
 * @date 2018/1/12.
 */

public class LiveContentPresenter implements ILiveInterface.ILivePresenter {

    private ILiveInterface.ILiveView mView;
    //    private List<LiveBean.SubjectsBean> mLiveDataList = new ArrayList<>();
    private int count = 10;
    private int totalSize = 0;
    private int start = 0;

    public LiveContentPresenter(ILiveInterface.ILiveView mView) {
        this.mView = mView;
    }


    @Override
    public void doLoadData() {
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
                        doSetAdapter(subjectsBeans);
                    } else {
                        doShowNoMore();
                    }
                }, throwable -> doShowNetError());
    }

    @Override
    public void doRefresh() {
        start = 0;
        count = 10;
        mView.onShowLoading();
        doLoadData();
    }

    @Override
    public void doLoadMoreData() {
        start += 10;
        count = (start >= totalSize) ? totalSize % count : count;
        doLoadData();
    }

    @Override
    public void doShowNoMore() {
        mView.onHideLoading();
        mView.onShowNoMore();
    }

    @Override
    public void doShowNetError() {
        mView.onHideLoading();
        mView.onShowNetError();
    }

    @Override
    public void doSetAdapter(@NotNull List<? extends LiveBean.SubjectsBean> mList) {
        mView.onSetAdapter(mList, totalSize);
        mView.onHideLoading();
    }
}
