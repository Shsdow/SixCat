package com.six.cat.sixcat.module.movieshow;

import com.six.cat.sixcat.RetrofitFactory;
import com.six.cat.sixcat.api.ILiveApi;
import com.six.cat.sixcat.bean.LiveBean;
import com.six.cat.sixcat.module.live.ILiveInterface;
import com.six.cat.sixcat.widget.ErrorAction;

import org.jetbrains.annotations.NotNull;

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

    public LiveContentPresenter(ILiveInterface.ILiveView mView) {
        this.mView = mView;
    }


    @Override
    public void doLoadData() {
        RetrofitFactory.getRetrofit().create(ILiveApi.class).getLiveContent("北京", 0, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(liveBean -> {
                    return liveBean.getSubjects();
                })
                .compose(mView.<List<LiveBean.SubjectsBean>>bindToLife())
                .subscribe(subjectsBeans -> {
                    if (subjectsBeans.size() > 0) {
                        doSetAdapter(subjectsBeans);
                    } else {
                        doShowNoMore();
                    }
                }, ErrorAction.error());
    }

    @Override
    public void doRefresh() {
//        if (mLiveDataList.size() > 0) {
//            mLiveDataList.clear();
//            count = 1;
//        }
        count = 10;
        mView.onShowLoading();
        doLoadData();
    }

    @Override
    public void doLoadMoreData() {
        count += 10;
        doLoadData();
    }

//    @Override
//    public void doSetAdapter(List<LiveBean.SubjectsBean> mList) {
////        mLiveDataList.addAll(mList);
//
//    }


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
        mView.onSetAdapter(mList);
        mView.onHideLoading();
    }
}
