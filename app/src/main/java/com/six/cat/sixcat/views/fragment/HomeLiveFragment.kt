package com.six.cat.sixcat.views.fragment

import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.six.cat.sixcat.R
import com.six.cat.sixcat.SixCatApplication
import com.six.cat.sixcat.adapter.LiveJavaAdapter
import com.six.cat.sixcat.base.BaseRxLazyFragment
import com.six.cat.sixcat.model.bean.LiveBean
import com.six.cat.sixcat.module.live.LiveContrace
import com.six.cat.sixcat.presenter.contract.LiveContentPresenter
import com.six.cat.sixcat.utils.ShowToast
import com.six.cat.sixcat.views.activity.MovieDetailActivity
import com.six.cat.sixcat.widgets.CustomLoadMoreView
import kotlinx.android.synthetic.main.fragment_live.*
import java.util.*


/**
 * @author liguoying
 * @date 2017/12/27.
 */

class HomeLiveFragment : BaseRxLazyFragment(), LiveContrace.View, BaseQuickAdapter.RequestLoadMoreListener, BaseQuickAdapter.OnItemChildClickListener {

    private val mBeanList = ArrayList<LiveBean.SubjectsBean>()
    private val PAGE_SIZE = 10
    private val mMovieShortCaseAdapter by lazy { LiveJavaAdapter(mBeanList) }
    private val presenter by lazy { LiveContentPresenter() }

    companion object {
        fun newInstance(): HomeLiveFragment = HomeLiveFragment()
    }

    override fun getLayoutResId() = R.layout.fragment_live

    override fun initView() {
        initRecyclerView()
        presenter.attachView(this)
    }

    private fun initRecyclerView() {
        val linearLayoutManager = LinearLayoutManager(context)
        rvMovieShortCase.layoutManager = linearLayoutManager
        mMovieShortCaseAdapter.setOnLoadMoreListener(this, rvMovieShortCase)
        mMovieShortCaseAdapter.setLoadMoreView(CustomLoadMoreView())
        mMovieShortCaseAdapter.openLoadAnimation()
        mMovieShortCaseAdapter.setNotDoAnimationCount(3)
        mMovieShortCaseAdapter.onItemChildClickListener = this//
        rvMovieShortCase.adapter = mMovieShortCaseAdapter
        mMovieShortCaseAdapter.setEnableLoadMore(false)
        srlMovieShortCaseFresh.setColorSchemeColors(SixCatApplication.context.resources.getColor(R.color.colorAccent, null),
                SixCatApplication.context.resources.getColor(R.color.blue_light, null),
                SixCatApplication.context.resources.getColor(R.color.yellow_light, null))
        mCuvEmptyView.setOnClickListener {
            presenter.loadData("", 0, 10)
        }
    }

    override fun lazyLoad() {
        srlMovieShortCaseFresh.isRefreshing = true
        presenter.loadData("", 0, PAGE_SIZE)
        srlMovieShortCaseFresh.setOnRefreshListener {
            srlMovieShortCaseFresh.isRefreshing = true
            mMovieShortCaseAdapter.setEnableLoadMore(true)
            presenter.loadData("", 0, PAGE_SIZE)
        }
    }

    override fun onLoadMoreRequested() {
        srlMovieShortCaseFresh.isEnabled = false
        presenter.loadMoreData()
        srlMovieShortCaseFresh.isEnabled = true
    }

    override fun setLiveData(list: List<LiveBean.SubjectsBean>?, startCount: Int, totalCount: Int) {
        srlMovieShortCaseFresh.isRefreshing = false
        mCuvEmptyView.visibility = View.GONE
        rvMovieShortCase.visibility = View.VISIBLE
        mMovieShortCaseAdapter.setEnableLoadMore(true)
        if (startCount == 0) {
            mMovieShortCaseAdapter.setNewData(list)
        } else {
            mMovieShortCaseAdapter.addData(list!!)
        }
        if (mMovieShortCaseAdapter.data.size == totalCount) {
            mMovieShortCaseAdapter.loadMoreEnd(true)
        } else {
            mMovieShortCaseAdapter.loadMoreComplete()
        }
        mBeanList.addAll(mMovieShortCaseAdapter.data)
    }

    override fun onItemChildClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {
        val mBean = adapter?.data?.get(position) as LiveBean.SubjectsBean
        startActivity(Intent(context, MovieDetailActivity::class.java)
                .putExtra("movieId", mBean.id))
    }

    override fun showError(errorMsg: String, errorCode: Int) {
    }

    override fun showLoading() {
    }

    override fun dismissLoading() {
    }


    private fun onShowNoMore() {
        activity!!.runOnUiThread { ShowToast.shortTime(R.string.have_no_data) }
        mCuvEmptyView.visibility = View.VISIBLE
        rvMovieShortCase.visibility = View.GONE
    }


}
