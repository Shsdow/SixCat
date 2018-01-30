package com.six.cat.sixcat.base;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.six.cat.sixcat.R;
import com.six.cat.sixcat.module.base.IBasePresenter;
import com.six.cat.sixcat.module.base.IBaseView;
import com.six.cat.sixcat.utils.ActivityManager;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.android.FragmentEvent;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity<T extends IBasePresenter> extends RxAppCompatActivity implements IBaseView<T> {

    public ActivityManager mManager;
    private Unbinder bind;
    protected T presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        bind = ButterKnife.bind(this);
        setPresenter(presenter);
        initView(savedInstanceState);
        initToolBar();
        mManager = ActivityManager.getInstance();
        mManager.addActivity(this);
    }

    public abstract int getLayoutId();

    protected abstract void initView(Bundle savedInstanceState);

    protected abstract void initToolBar();

    @Override
    public <T> LifecycleTransformer<T> bindToLife() {
        return bindUntilEvent(ActivityEvent.DESTROY);
    }

}
