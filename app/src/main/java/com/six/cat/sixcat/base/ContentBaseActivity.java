package com.six.cat.sixcat.base;

import android.os.Bundle;

import com.six.cat.sixcat.module.base.IBasePresenter;
import com.six.cat.sixcat.module.base.IBaseView;
import com.trello.rxlifecycle2.LifecycleTransformer;

import java.util.List;

/**
 * @author liguoying
 * @date 2018/1/29.
 */

public class ContentBaseActivity<T extends IBasePresenter>  extends BaseActivity implements IBaseView<T> {
    public T presenter;

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
    public void setPresenter(T presenter) {

    }

    @Override
    public void onSetAdapter(List<?> list) {

    }

    @Override
    public void onShowNoMore() {

    }

    @Override
    public <T1> LifecycleTransformer<T1> bindToLife() {
        return null;
    }

    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected void initToolBar() {

    }
}
