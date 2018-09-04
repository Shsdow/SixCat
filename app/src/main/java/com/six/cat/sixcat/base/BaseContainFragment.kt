package com.six.cat.sixcat.base

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.FragmentActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife
import butterknife.Unbinder
import com.six.cat.sixcat.utils.ActivityManager
import com.trello.rxlifecycle2.components.RxFragment

/**
 * Author：Administrator
 * Data: 2018/9/4 0004 15:14
 */
abstract class BaseContainFragment : RxFragment() {
    var containActivity: FragmentActivity? = null
    private var parentView: View? = null
    private var bind: Unbinder? = null
    @LayoutRes
    abstract fun getLayoutResId(): Int


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, state: Bundle?): View? {
        parentView = inflater.inflate(getLayoutResId(), container, false)
        bind = ButterKnife.bind(this, parentView!!)
        containActivity = getSupportActivity()
        return parentView
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        finishCreateView(savedInstanceState)
    }

    abstract fun finishCreateView(state: Bundle?)

    fun getSupportActivity(): FragmentActivity? {
        return super.getActivity() as FragmentActivity
    }

    fun getApplicationContext(): Context? {
        if (containActivity == null) {
            if (activity == null)
                return null
            else
                return activity.applicationContext
        } else {
            return containActivity!!.applicationContext
        }
    }

    fun getSupportActionBar(): android.app.ActionBar? {
        return getSupportActivity()!!.actionBar
    }

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        this.containActivity = activity as FragmentActivity
    }

    override fun onDetach() {
        super.onDetach()
        containActivity = null
    }

    override fun onDestroy() {
        super.onDestroy()
        finishTask()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        bind!!.unbind()
    }


    protected fun finishTask() {
        ActivityManager.getInstance().finishActivity(activity)
    }

    fun <T : View> `$`(id: Int): T {
        return parentView!!.findViewById<View>(id) as T
    }
}