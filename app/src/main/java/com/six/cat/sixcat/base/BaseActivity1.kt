package com.six.cat.sixcat.base

import android.os.Bundle
import butterknife.ButterKnife
import butterknife.Unbinder
import com.six.cat.sixcat.module.base.IBasePresenter
import com.six.cat.sixcat.module.base.IBaseView
import com.six.cat.sixcat.utils.ActivityManager
import com.trello.rxlifecycle2.LifecycleTransformer
import com.trello.rxlifecycle2.android.ActivityEvent
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity

/**
 * Creater: liguoying
 * Time: 2018/6/15 0015 14:11
 */
abstract class BaseActivity1<T : IBasePresenter> : RxAppCompatActivity(), IBaseView<T> {
    public var mManager: ActivityManager? = null
    private var mBind: Unbinder? = null
    abstract fun layoutId(): Int
    protected val present: T ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId())
        mBind = ButterKnife.bind(this)
        mSetPresenter(present)
        initView(savedInstanceState)
        initToolBar()
        mManager = ActivityManager.getInstance()
        mManager!!.addActivity(this)
    }

    abstract fun initView(savedInstanceState: Bundle?)
    abstract fun initToolBar() : Unit

    override fun <T> bindToLife(): LifecycleTransformer<T> {
        return bindUntilEvent(ActivityEvent.DESTROY)
    }
}
