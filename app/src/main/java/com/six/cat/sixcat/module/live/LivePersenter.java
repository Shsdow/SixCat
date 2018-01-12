package com.six.cat.sixcat.module.live;

import com.six.cat.sixcat.bean.LiveBean;
import com.six.cat.sixcat.module.live.ILiveInterface.ILivePresenter;

import java.util.List;

/**
 * @author liguoying
 * @date 2018/1/11.
 */

public class LivePersenter implements ILivePresenter {
    private ILiveInterface.ILiveView mView;

    @Override
    public void doRefresh() {

    }

    @Override
    public void doShowNetError() {

    }

    @Override
    public void doLoadData() {

    }

    @Override
    public void doLoadMoreData() {

    }

    @Override
    public void doSetAdapter(List<LiveBean.SubjectsBean> mList) {

    }

    @Override
    public void doShowNoMore() {

    }
}
