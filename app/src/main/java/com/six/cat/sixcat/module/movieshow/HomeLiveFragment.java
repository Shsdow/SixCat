package com.six.cat.sixcat.module.movieshow;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.six.cat.sixcat.R;
import com.six.cat.sixcat.adapter.LiveJavaAdapter;
import com.six.cat.sixcat.base.BaseRxLazyFragment;
import com.six.cat.sixcat.bean.LiveBean;
import com.six.cat.sixcat.module.live.ILiveInterface;
import com.six.cat.sixcat.module.movieshowcase.MovieShowcaseActivity;
import com.six.cat.sixcat.utils.LogUtil;
import com.six.cat.sixcat.utils.ShowToast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import butterknife.BindView;

/**
 * @author liguoying
 * @date 2017/12/27.
 */

public class HomeLiveFragment extends BaseRxLazyFragment<ILiveInterface.ILivePresenter> implements ILiveInterface.ILiveView/*,SwipeRefreshLayout.OnRefreshListener*/ {

    private static HomeLiveFragment instance;
    private LiveJavaAdapter mLiveFragmentAdapter;
    private List<LiveBean.SubjectsBean> mBeanList = new ArrayList<>();
    private boolean isFreshing = false;

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
        lazyLoad();
    }


    @Override
    protected void lazyLoad() {
        if (!isPrepared || !isVisible) {
            return;
        }
        initView();
        isPrepared = false;
    }

    @Override
    protected void initRecyclerView() {
        LogUtil.e("bubu 2");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mLiveFragmentAdapter = new LiveJavaAdapter(mBeanList);
        mLiveFragmentAdapter.openLoadAnimation();
        mLiveFragmentAdapter.setNotDoAnimationCount(3);
        mLiveFragmentAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            startActivity(new Intent(getContext(), MovieShowcaseActivity.class).putExtra("movieId", mBeanList.get(position).getId()));
        });
        mRecyclerView.setAdapter(mLiveFragmentAdapter);
    }


    @Override
    protected void initRefreshLayout() {
        LogUtil.e("bubu 3");
        mSwipeRefreshLayout.setOnRefreshListener(() -> {
            isFreshing = true;
            presenter.doRefresh();
        });
        mSwipeRefreshLayout.setColorSchemeColors(getApplicationContext().getResources().getColor(R.color.colorAccent));
        mSwipeRefreshLayout.post(() -> {
            mSwipeRefreshLayout.setRefreshing(true);
            loadData();
        });
    }


    @Override
    public void loadData() {
        onShowLoading();
        presenter.doLoadData();
    }

    @Override
    public void onShowLoading() {
        isFresh(true);
    }

    @Override
    public void onHideLoading() {
        isFresh(false);
    }

    private void isFresh(boolean isFresh) {
        mSwipeRefreshLayout.post(() -> {
            mSwipeRefreshLayout.setRefreshing(isFresh);
        });
    }

    @Override
    public void onShowNetError() {
        ShowToast.shortTime(R.string.network_error);
    }

    @Override
    public void setPresenter(ILiveInterface.ILivePresenter presenter) {
        if (presenter == null) {
            this.presenter = new LiveContentPresenter(this);
        }
    }

    @Override
    public void onSetAdapter(List<?> list) {
        if (isFreshing && !mBeanList.isEmpty()) {
            mBeanList.clear();
        }
        mBeanList.addAll((Collection<? extends LiveBean.SubjectsBean>) list);
        mLiveFragmentAdapter.notifyDataSetChanged();
    }

    @Override
    public void onShowNoMore() {
        getActivity().runOnUiThread(() -> {
            ShowToast.shortTime(R.string.have_no_data);
        });
    }
}
