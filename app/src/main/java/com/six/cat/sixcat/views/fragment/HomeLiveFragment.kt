package com.six.cat.sixcat.views.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.chad.library.adapter.base.BaseQuickAdapter
import com.six.cat.sixcat.R
import com.six.cat.sixcat.adapter.LiveJavaAdapter
import com.six.cat.sixcat.base.BaseRxLazyFragment
import com.six.cat.sixcat.model.LiveBean
import com.six.cat.sixcat.module.live.ILiveInterface
import com.six.cat.sixcat.presenter.LiveContentPresenter
import com.six.cat.sixcat.utils.LogUtil
import com.six.cat.sixcat.utils.ShowToast
import com.six.cat.sixcat.views.activity.MovieShowcaseActivity
import com.six.cat.sixcat.widgets.CustomLoadMoreView
import com.six.cat.sixcat.widgets.SnarkBarUtil
import kotlinx.android.synthetic.main.fragment_live.*
import java.util.*


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
    val TAG = "HomeLiveFragment"

    companion object {
        fun newInstance(): HomeLiveFragment {
            var instance: HomeLiveFragment? = null
            if (instance == null) {
                instance = HomeLiveFragment()
            }
            return instance
        }
    }

    override fun getLayoutResId() = R.layout.fragment_live

    override fun setPresenterView(presenter: ILiveInterface.ILivePresenter?) {
        if (presenter == null) {
            this.presenter = LiveContentPresenter(this)
        }
    }

    override fun finishCreateView(state: Bundle?) {
        LogUtil.d(TAG + " finishcreateview" + " " + TAG)
        presenter = LiveContentPresenter(this)
        isPrepared = true
        initView()
        isPrepared = false
    }


    override fun initView() {
        initRecyclerView()
    }

    private fun initRecyclerView() {
        val linearLayoutManager = LinearLayoutManager(context)
        rvMovieShortCase.layoutManager = linearLayoutManager
        mMovieShortCaseAdapter = LiveJavaAdapter(mBeanList)
        mMovieShortCaseAdapter!!.setOnLoadMoreListener(this, rvMovieShortCase)
        mMovieShortCaseAdapter!!.setLoadMoreView(CustomLoadMoreView())
        mMovieShortCaseAdapter!!.openLoadAnimation()
        mMovieShortCaseAdapter!!.setNotDoAnimationCount(3)
        mMovieShortCaseAdapter!!.setOnItemChildClickListener { _, _, position -> startActivity(Intent(context, MovieShowcaseActivity::class.java).putExtra("movieId", mBeanList[position].id)) }
        rvMovieShortCase.adapter = mMovieShortCaseAdapter
        initRefreshLayout()
        mCuvEmptyView.setOnClickListener {
            presenter.doRefresh()
        }
    }


    private fun initRefreshLayout() {
        mMovieShortCaseAdapter!!.setEnableLoadMore(false)
        srlMovieShortCaseFresh.setOnRefreshListener {
            mMovieShortCaseAdapter!!.setEnableLoadMore(true)
            isFreshing = true
            presenter.doRefresh()
        }
        srlMovieShortCaseFresh.setColorSchemeColors(applicationContext!!.resources.getColor(R.color.colorAccent, null), applicationContext!!.resources.getColor(R.color.blue_light, null), applicationContext!!.resources.getColor(R.color.yellow_light, null))
        srlMovieShortCaseFresh.post {
            srlMovieShortCaseFresh.isRefreshing = true
            presenter.doRefresh()
        }
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

    private fun isFresh(isFresh: Boolean) {
        srlMovieShortCaseFresh!!.post { srlMovieShortCaseFresh.isRefreshing = isFresh }
    }

    override fun onShowNetError() {
        srlMovieShortCaseFresh.isRefreshing = false
        mCuvEmptyView.visibility = View.VISIBLE
        rvMovieShortCase.visibility = View.GONE
        mCuvEmptyView.setEmptyImage(R.drawable.img_tips_error_load_error)
        mCuvEmptyView.setEmptyText("加载失败~(≧▽≦)~啦啦啦.")
//        Toast.makeText(activity,"jiji",Toast.LENGTH_SHORT).show()
        ShowToast.shortTime(String.format(Locale.CHINA, "这是第 %d 个 fragment ", 34))
//        SnarkBarUtil.showSnakbarMessage(rvMovieShortCase, "数据加载失败,请重新加载或者检查网络是否链接")
    }

    override fun onLoadDataSuccess(list: List<*>, totalCount: Int) {
        mCuvEmptyView.visibility = View.GONE
        rvMovieShortCase.visibility = View.VISIBLE
        mMovieShortCaseAdapter!!.setEnableLoadMore(true)
        if (list.size < PAGE_SIZE) {
            mMovieShortCaseAdapter!!.loadMoreEnd(true)
        } else {

            mMovieShortCaseAdapter!!.loadMoreComplete()
        }
        if (isFreshing && !mBeanList.isEmpty()) {
            mMovieShortCaseAdapter!!.setNewData(list as List<LiveBean.SubjectsBean>)
            isFreshing = false
        } else {
            mMovieShortCaseAdapter!!.addData(list as List<LiveBean.SubjectsBean>)
        }
    }

    override fun haveNoMore() {
        onShowNoMore()
    }

    override fun onShowLoading() {
        isFresh(true)
    }

    override fun onHideLoading() {
        isFresh(false)
    }

    override fun onShowNoMore() {
        activity!!.runOnUiThread { ShowToast.shortTime(R.string.have_no_data) }
    }
}
