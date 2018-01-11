package com.six.cat.sixcat.fragment.homefgs;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.six.cat.sixcat.R;
import com.six.cat.sixcat.base.BaseRxLazyFragment;
import com.six.cat.sixcat.module.base.IBaseView;
import com.six.cat.sixcat.module.live.ILiveInterface;
import com.six.cat.sixcat.module.live.ILiveInterface.ILiveView;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.android.FragmentEvent;

import butterknife.BindView;

/**
 * @author liguoying
 * @date 2017/12/27.
 */

public class HomeLiveFragment extends BaseRxLazyFragment<ILiveInterface.ILivePresenter> implements ILiveView {
    @BindView(R.id.rv_show_items)
    RecyclerView mRecyclerView;
    @BindView(R.id.srl_show_live_items)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private static HomeLiveFragment instance;

    public static HomeLiveFragment newInstance() {
        if (instance == null) {
            instance = new HomeLiveFragment();
        }
        return instance;
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_ex;
    }

    @Override
    public void finishCreateView(Bundle state) {
        isPrepared = true;
        layLoadFragment();
    }

    private void layLoadFragment() {
        if (!isPrepared || !isVisible) {
            return;
        }
        mSwipeRefreshLayout.setOnRefreshListener(this::loadData);
        mSwipeRefreshLayout.post(() -> {
            mSwipeRefreshLayout.setRefreshing(true);
            loadData();
        });
    }

    @Override
    public void loadData() {
    }

    @Override
    public void onShowLoading() {

    }

    @Override
    public void onHideLoading() {

    }

    @Override
    public void onShowNetError() {

    }

    @Override
    public void setPresenter(ILiveInterface.ILivePresenter presenter) {

    }

    @Override
    public <T> LifecycleTransformer<T> bindToLife() {
        return null;
    }


}
