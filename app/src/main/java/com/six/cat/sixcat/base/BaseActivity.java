package com.six.cat.sixcat.base;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.six.cat.sixcat.R;
import com.six.cat.sixcat.utils.ActivityManager;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity extends RxAppCompatActivity {

    public ActivityManager mManager;
    private Unbinder bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        bind = ButterKnife.bind(this);
        initView(savedInstanceState);
        initToolBar();
        mManager = ActivityManager.getInstance();
        mManager.addActivity(this);
    }

    public abstract int getLayoutId();

    protected abstract void initView(Bundle savedInstanceState);

    protected abstract void initToolBar();
}
