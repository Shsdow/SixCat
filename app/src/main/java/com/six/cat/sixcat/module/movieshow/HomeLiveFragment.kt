package com.six.cat.sixcat.module.movieshow

import android.content.Intent
import android.os.Bundle
import android.os.IInterface
import android.support.v7.widget.LinearLayoutManager

import com.chad.library.adapter.base.BaseQuickAdapter
import com.six.cat.sixcat.R
import com.six.cat.sixcat.adapter.LiveJavaAdapter
import com.six.cat.sixcat.base.BaseRxLazyFragment
import com.six.cat.sixcat.bean.LiveBean
import com.six.cat.sixcat.module.live.ILiveInterface
import com.six.cat.sixcat.module.movieshowcase.MovieShowcaseActivity
import com.six.cat.sixcat.utils.ShowToast

import java.util.ArrayList

import kotlinx.android.synthetic.main.fragment_live.*


/**
 * @author liguoying
 * @date 2017/12/27.
 */

class HomeLiveFragment : BaseRxLazyFragment<ILiveInterface.ILivePresenter>(), ILiveInterface.ILiveView, BaseQuickAdapter.RequestLoadMoreListener {
    private var mMovieShortCaseAdapter: LiveJavaAdapter? = null
    private val mBeanList = ArrayList<LiveBean.SubjectsBean>()
    private var isFreshing = false
    private val PAGE_SIZE = 10


    var mCurrentCount = 0

    companion object {
        fun newInstance(): HomeLiveFragment {
            var instance: HomeLiveFragment? = null
            if (instance == null) {
                instance = HomeLiveFragment()
            }
            return instance
        }
    }

    override fun getLayoutResId(): Int {
        return R.layout.fragment_live
    }

    override fun finishCreateView(state: Bundle?) {
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
        val linearLayoutManager = LinearLayoutManager(context)
        rvMovieShortCase.layoutManager = linearLayoutManager
        mMovieShortCaseAdapter = LiveJavaAdapter(mBeanList)
        mMovieShortCaseAdapter!!.setOnLoadMoreListener(this, rvMovieShortCase)
        mMovieShortCaseAdapter!!.openLoadAnimation()
        mMovieShortCaseAdapter!!.setNotDoAnimationCount(3)
        mMovieShortCaseAdapter!!.setOnItemChildClickListener { _, _, position -> startActivity(Intent(context, MovieShowcaseActivity::class.java).putExtra("movieId", mBeanList[position].id)) }
        rvMovieShortCase.adapter = mMovieShortCaseAdapter
    }


    override fun initRefreshLayout() {
        srlMovieShortCaseFresh.setOnRefreshListener {
            mMovieShortCaseAdapter!!.setEnableLoadMore(false)
            isFreshing = true
            presenter.doRefresh()
        }
        srlMovieShortCaseFresh.setColorSchemeColors(applicationContext!!.resources.getColor(R.color.colorAccent, null), applicationContext!!.resources.getColor(R.color.blue_light, null), applicationContext!!.resources.getColor(R.color.yellow_light, null))
        srlMovieShortCaseFresh.post {
            srlMovieShortCaseFresh.isRefreshing = true
            loadData()
        }
    }


    override fun loadData() {
        onShowLoading()
        presenter.doLoadData()
//        mCurrentCount =
    }

    override fun onLoadMoreRequested() {
        srlMovieShortCaseFresh.isEnabled = false
        if (mMovieShortCaseAdapter!!.data.size < PAGE_SIZE) {
            mMovieShortCaseAdapter!!.loadMoreEnd()
        } else {
            presenter.doLoadMoreData()
        }
        srlMovieShortCaseFresh.isEnabled = true
    }

    override fun onShowLoading() {
        isFresh(true)
    }

    override fun onHideLoading() {
        isFresh(false)
    }

    private fun isFresh(isFresh: Boolean) {
        srlMovieShortCaseFresh!!.post { srlMovieShortCaseFresh.isRefreshing = isFresh }
    }

    override fun onShowNetError() {
        ShowToast.shortTime(R.string.network_error)
    }

    override fun setPresenter(presenter: ILiveInterface.ILivePresenter?) {
        if (presenter == null) {
            this.presenter = LiveContentPresenter(this)
        }
    }

    override fun onSetAdapter(list: List<*>, totalCount: Int) {

//        mMovieShortCaseAdapter!!.setEnableLoadMore(true)
        if (/*isFreshing && */!mBeanList.isEmpty()) {
            mBeanList.clear()
        }
        if (list.size == totalCount) {
            mMovieShortCaseAdapter!!.loadMoreEnd(true)
        }
        mBeanList.addAll(list as List<LiveBean.SubjectsBean>)
        mMovieShortCaseAdapter!!.notifyDataSetChanged()
    }

    override fun onShowNoMore() {
        activity!!.runOnUiThread { ShowToast.shortTime(R.string.have_no_data) }
    }
}
