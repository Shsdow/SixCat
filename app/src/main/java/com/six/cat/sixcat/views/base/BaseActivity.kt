package com.six.cat.sixcat.views.base

import android.os.Bundle
import butterknife.ButterKnife
import butterknife.Unbinder
import com.six.cat.sixcat.presenter.IBasePresenter
import com.six.cat.sixcat.presenter.IBaseView
import com.six.cat.sixcat.utils.ActivityManager
import com.trello.rxlifecycle2.LifecycleTransformer
import com.trello.rxlifecycle2.android.ActivityEvent
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity

abstract class BaseActivity<T : IBasePresenter> : RxAppCompatActivity(), IBaseView<T> {

    var mManager: ActivityManager? = null
    private var bind: Unbinder? = null
    protected var mSetPresenter: T? = null

    abstract fun getLayoutId(): Int


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        bind = ButterKnife.bind(this)
        mSetPresenter(mSetPresenter)
        initView(savedInstanceState)
        initToolBar()
        mManager = ActivityManager.getInstance()
        mManager!!.addActivity(this)
    }

    protected abstract fun initView(savedInstanceState: Bundle?)

    protected abstract fun initToolBar()

    override fun <T> bindToLife(): LifecycleTransformer<T> {
        return bindUntilEvent(ActivityEvent.DESTROY)
    }

}
