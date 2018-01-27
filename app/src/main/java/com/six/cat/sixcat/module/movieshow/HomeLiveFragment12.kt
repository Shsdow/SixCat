package com.six.cat.sixcat.module.movieshow

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

import com.six.cat.sixcat.R
import com.six.cat.sixcat.adapter.LiveJavaAdapter
import com.six.cat.sixcat.base.BaseRxLazyFragment
import com.six.cat.sixcat.bean.LiveBean
import com.six.cat.sixcat.module.movieshow.ILiveInterface.ILivePresenter
import com.six.cat.sixcat.module.movieshow.ILiveInterface.ILiveView
import com.six.cat.sixcat.utils.LogUtil
import com.six.cat.sixcat.utils.ShowToast

import java.util.ArrayList

import butterknife.BindView

/**
 * @author liguoying
 * @date 2017/12/27.
 */

class HomeLiveFragment12 : BaseRxLazyFragment<ILiveInterface.ILivePresenter>(), ILiveView/*,SwipeRefreshLayout.OnRefreshListener*/ {
    private var mLiveFragmentAdapter: LiveJavaAdapter? = null
    private val mBeanList = ArrayList<LiveBean.SubjectsBean>()
    private var isFreshing = false

    @BindView(R.id.rv_show_items)
    internal var mRecyclerView: RecyclerView? = null
    @BindView(R.id.srl_show_live_items)
    internal var mSwipeRefreshLayout: SwipeRefreshLayout? = null

    override fun getLayoutResId(): Int {
        return R.layout.fragment_live
    }

    override fun finishCreateView(state: Bundle) {
        isPrepared = true
        lazyLoad()
    }


    override fun lazyLoad() {
        if (!isPrepared || !isVisible) {
            return
        }
        initView()
        isPrepared = false
    }

    override fun initRecyclerView() {
        LogUtil.e("bubu 2")
        val linearLayoutManager = LinearLayoutManager(context)
        mRecyclerView!!.layoutManager = linearLayoutManager
        mLiveFragmentAdapter = LiveJavaAdapter(mBeanList)
        mLiveFragmentAdapter!!.openLoadAnimation()
        mLiveFragmentAdapter!!.setNotDoAnimationCount(3)
        mLiveFragmentAdapter!!.setOnItemChildClickListener { adapter, view, position -> ShowToast.shortTime(R.string.show_toast_tip) }
        mRecyclerView!!.adapter = mLiveFragmentAdapter
    }

    override fun initRefreshLayout() {
        LogUtil.e("bubu 3")
        mSwipeRefreshLayout!!.setOnRefreshListener {
            isFreshing = true
            presenter.doRefresh()
        }
        mSwipeRefreshLayout!!.setColorSchemeColors(applicationContext!!.resources.getColor(R.color.colorAccent))
        mSwipeRefreshLayout!!.post {
            mSwipeRefreshLayout!!.isRefreshing = true
            loadData()
        }
    }


    override fun loadData() {
        onShowLoading()
        presenter.doLoadData()
    }

    override fun onShowLoading() {
        isFresh(true)
    }

    override fun onHideLoading() {
        isFresh(false)
    }

    private fun isFresh(isFresh: Boolean) {
        mSwipeRefreshLayout!!.post { mSwipeRefreshLayout!!.isRefreshing = isFresh }
    }

    override fun onShowNetError() {
        ShowToast.shortTime(R.string.network_error)
    }

    override fun setPresenter(presenter: ILivePresenter?) {
        if (presenter == null) {
            this.presenter = LiveContentPresenter(this)
        }
    }

    override fun onSetAdapter(list: List<*>) {
        if (isFreshing && !mBeanList.isEmpty()) {
            mBeanList.clear()
        }
        mBeanList.addAll(list as Collection<LiveBean.SubjectsBean>)
        mLiveFragmentAdapter!!.notifyDataSetChanged()
    }

    override fun onShowNoMore() {
        activity!!.runOnUiThread { ShowToast.shortTime(R.string.have_no_data) }
    }

    companion object {

        private var instance: HomeLiveFragment? = null


        fun newInstance(): HomeLiveFragment {
            if (instance == null) {
                instance = HomeLiveFragment()
            }
            return instance
        }
    }
}
