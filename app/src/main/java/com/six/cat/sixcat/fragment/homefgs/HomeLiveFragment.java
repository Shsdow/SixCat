package com.six.cat.sixcat.fragment.homefgs;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.six.cat.sixcat.R;
import com.six.cat.sixcat.base.BaseRxLazyFragment;
import com.six.cat.sixcat.module.live.ILiveInterface;
import com.six.cat.sixcat.module.live.ILiveInterface.ILivePresenter;
import com.six.cat.sixcat.module.live.ILiveInterface.ILiveView;
import com.six.cat.sixcat.module.live.LiveContentPresenter;
import com.six.cat.sixcat.utils.ShowToast;
import com.trello.rxlifecycle2.LifecycleTransformer;

import java.util.List;

import butterknife.BindView;

/**
 * @author liguoying
 * @date 2017/12/27.
 */

public class HomeLiveFragment extends BaseRxLazyFragment<ILiveInterface.ILivePresenter> implements ILiveView/*,SwipeRefreshLayout.OnRefreshListener*/ {
    private static HomeLiveFragment instance;

    @BindView(R.id.rv_show_items)
    RecyclerView mRecyclerView;

    @BindView(R.id.srl_show_live_items)
    SwipeRefreshLayout mSwipeRefreshLayout;

    public static HomeLiveFragment newInstance() {
        if (instance == null) {
            instance = new HomeLiveFragment();
        }
        return instance;
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_live;
    }

    @Override
    public void finishCreateView(Bundle state) {
        isPrepared = true;
//        layLoadFragment();
        mSwipeRefreshLayout.setOnRefreshListener(() -> presenter.doRefresh());
        isFresh(true);
    }


    @Override
    protected void lazyLoad() {
        if (!isPrepared || !isVisible) {
            return;
        }
        isFresh(true);
        isPrepared = false;
    }


    @Override
    public void onShowLoading() {
        isFresh(true);
    }

    private void isFresh(boolean isFresh) {
        mSwipeRefreshLayout.post(() -> {
            if (isFresh) {
                loadData();
            }
            mSwipeRefreshLayout.setRefreshing(true);
        });
    }

    @Override
    public void loadData() {
        onShowLoading();
        presenter.doLoadData();
    }

    @Override
    public void onHideLoading() {
        isFresh(false);
    }

    @Override
    public void onShowNetError() {
        ShowToast.shortTime(R.string.network_error);
    }

    @Override
    public void setPresenter(ILivePresenter presenter) {
        if (presenter == null) {
            this.presenter = new LiveContentPresenter(this);
        }
    }

    @Override
    public void onSetAdapter(List<?> list) {


    }

    @Override
    public void onShowNoMore() {
        getActivity().runOnUiThread(() -> {
            ShowToast.shortTime(R.string.have_no_data);
        });
    }

    @Override
    public <T> LifecycleTransformer<T> bindToLife() {
        return null;
    }

}
