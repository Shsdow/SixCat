package com.six.cat.sixcat.module.live;

import com.six.cat.sixcat.RetrofitFactory;
import com.six.cat.sixcat.api.ILiveApi;
import com.six.cat.sixcat.bean.LiveBean;
import com.six.cat.sixcat.utils.LogUtil;
import com.six.cat.sixcat.widget.ErrorAction;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * @author liguoying
 * @date 2018/1/12.
 */

public class LiveContentPresenter implements ILiveInterface.ILivePresenter {

    private ILiveInterface.ILiveView mView;
    private List<LiveBean.SubjectsBean> mLiveDataList = new ArrayList<>();
    private int count;

    public LiveContentPresenter(ILiveInterface.ILiveView mView) {
        this.mView = mView;
    }


    @Override
    public void doLoadData() {

        RetrofitFactory.getRetrofit().create(ILiveApi.class).getLiveContent("北京", 0, count)
                .subscribeOn(Schedulers.io())
                .map(liveBean -> {
                    LogUtil.e("abc" + liveBean.toString());
                    return liveBean.getSubjects();
                })
//                .compose(mView.<List<LiveBean.SubjectsBean>>bindToLife())
                .observeOn(AndroidSchedulers.mainThread())
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
        if (mLiveDataList.size() > 0) {
            mLiveDataList.clear();
            count = 0;
        }
        mView.onShowLoading();
        doLoadData();
    }

    @Override
    public void doLoadMoreData() {
        count += 10;
        doLoadData();
    }

    @Override
    public void doSetAdapter(List<LiveBean.SubjectsBean> mList) {
        mLiveDataList.addAll(mList);
        mView.onSetAdapter(mLiveDataList);
        mView.onHideLoading();
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
}
