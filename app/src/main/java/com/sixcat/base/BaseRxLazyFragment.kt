package com.sixcat.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import com.sixcat.SixCatApplication
import com.sixcat.utils.ActivityManager
import com.trello.rxlifecycle2.components.support.RxFragment

/**
 * @author liguoying
 * @date 2017/12/11.
 */
abstract class BaseRxLazyFragment : RxFragment() {
    private var parentView: View? = null
    /**
     * 视图是否加载完毕
     */
    private var isViewPrepare = false
    /**
     * 数据是否加载过了
     */
    private var hasLoadData = false

    @LayoutRes
    abstract fun getLayoutResId(): Int

    /**
     * 页面初始化
     */
    abstract fun initView()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, state: Bundle?): View? {
        parentView = inflater.inflate(getLayoutResId(), container, false)
        return parentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        super.onViewCreated(view, savedInstanceState)
        isViewPrepare = true
        initView()
        lazyLoadDataIfPrepared()
    }
    /**
     * Fragment数据的懒加载
     */
    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser) {
            lazyLoadDataIfPrepared()
        }
    }

    private fun lazyLoadDataIfPrepared() {
        if (userVisibleHint && isViewPrepare && !hasLoadData) {
            lazyLoad()
            hasLoadData = true
        }
    }
    /**
     * 懒加载
     */
    abstract fun lazyLoad()


    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun onDestroy() {
        super.onDestroy()
        finishTask()
        SixCatApplication.getRefWatcher(activity as Context)?.watch(activity)
    }

    private fun finishTask() {
        ActivityManager.getInstance().finishActivity(activity)
    }

}
