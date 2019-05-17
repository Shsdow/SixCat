package com.sixcat.base

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.FragmentActivity
import com.sixcat.utils.ActivityManager
import com.trello.rxlifecycle2.components.RxFragment

/**
 * Author：Administrator
 * Data: 2018/9/4 0004 15:14
 */
abstract class BaseContainFragment : RxFragment() {
    var containActivity: FragmentActivity? = null
    private var parentView: View? = null
    @LayoutRes
    abstract fun getLayoutResId(): Int


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, state: Bundle?): View? {
        parentView = inflater.inflate(getLayoutResId(), container, false)
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
    }


    protected fun finishTask() {
        ActivityManager.getInstance().finishActivity(activity)
    }

    fun <T : View> `$`(id: Int): T {
        return parentView!!.findViewById<View>(id) as T
    }
}