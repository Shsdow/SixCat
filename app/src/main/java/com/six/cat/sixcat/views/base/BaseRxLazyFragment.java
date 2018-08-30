package com.six.cat.sixcat.views.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.six.cat.sixcat.presenter.IBasePresenter;
import com.six.cat.sixcat.presenter.IBaseView;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.android.FragmentEvent;
import com.trello.rxlifecycle2.components.support.RxFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author liguoying
 * @date 2017/12/11.
 */
public abstract class BaseRxLazyFragment<T extends IBasePresenter> extends RxFragment implements IBaseView<T> {
    private View parentView;
    private FragmentActivity activity;
    // 标志位 标志已经初始化完成。
    protected boolean isPrepared;
    //标志位 fragment是否可见
    protected boolean isVisible;
    private Unbinder bind;
    private static final String TAG = "BaseRxLazyFragment";

    @LayoutRes
    public abstract int getLayoutResId();

    protected T presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle state) {
        parentView = inflater.inflate(getLayoutResId(), container, false);
        bind = ButterKnife.bind(this, parentView);
        activity = getSupportActivity();
        mSetPresenter(presenter);
        return parentView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        finishCreateView(savedInstanceState);
    }

    /**
     * 初始化views
     *
     * @param state
     */
    public abstract void finishCreateView(Bundle state);

    @Override
    public void onResume() {
        super.onResume();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        bind.unbind();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = (FragmentActivity) activity;
    }


    @Override
    public void onDetach() {
        super.onDetach();
        this.activity = null;
    }


    public FragmentActivity getSupportActivity() {
        return super.getActivity();
    }


    public android.app.ActionBar getSupportActionBar() {
        return getSupportActivity().getActionBar();
    }


    public Context getApplicationContext() {
        return this.activity == null ? (getActivity() == null ?
                null : getActivity().getApplicationContext()) : this.activity.getApplicationContext();
    }


    /**
     * Fragment数据的懒加载
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }

    /**
     * fragment显示时才加载数据
     */
    protected void onVisible() {
        lazyLoad();
    }

    /**
     * fragment懒加载方法，获取页面信息，例如进行请求数据
     */
    protected void lazyLoad() {
        loadData();
    }

    /**
     * fragment隐藏
     */
    protected void onInvisible() {
    }

    /**
     * 加载数据
     */
    protected void loadData() {
    }

    @Override
    public void onShowLoading() {

    }

    @Override
    public void onHideLoading() {

    }

    /**
     * 初始化 View
     */
    protected void initView() {
        initRecyclerView();
        initRefreshLayout();
    }

    /**
     * 初始化recyclerView
     */
    protected void initRecyclerView() {
    }

    /**
     * 初始化refreshLayout
     */
    protected void initRefreshLayout() {
    }

//    /**
//     * 设置数据显示
//     */
//    protected void finishTask() {
//    }

    @Override
    public <T> LifecycleTransformer<T> bindToLife() {
        return bindUntilEvent(FragmentEvent.DESTROY);
    }

    @SuppressWarnings("unchecked")
    public <T extends View> T $(int id) {
        return (T) parentView.findViewById(id);
    }
}
