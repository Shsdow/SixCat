package com.six.cat.sixcat.views.fragment

import `in`.srain.cube.views.ptr.PtrDefaultHandler
import `in`.srain.cube.views.ptr.PtrFrameLayout
import `in`.srain.cube.views.ptr.PtrHandler
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.six.cat.sixcat.R
import com.six.cat.sixcat.SixCatApplication
import com.six.cat.sixcat.adapter.VideoDetailAdapter
import com.six.cat.sixcat.base.BaseRxLazyFragment
import com.six.cat.sixcat.model.bean.VideoChannelBean
import com.six.cat.sixcat.model.bean.VideoDetailBean
import com.six.cat.sixcat.presenter.IVideoInterfaceManager
import com.six.cat.sixcat.presenter.VideoPresenter
import com.six.cat.sixcat.utils.LogUtil
import com.six.cat.sixcat.widgets.CustomLoadMoreView
import kotlinx.android.synthetic.main.fragment_video_detail.*

/**
 * @author liguoying
 * @date 2018/2/6.
 */
class VideoDetailFragment : BaseRxLazyFragment<IVideoInterfaceManager.IVideoPresenter>(), IVideoInterfaceManager.IVideoView {

    private var typeId: String? = null
    private var pageNum = 1
    private var videoDetailBean: VideoDetailBean? = null
    private lateinit var videoDetailAdapter: VideoDetailAdapter
    private val TAG = "VideoDetailFragment";

    companion object {
        fun newInstance(typeId: String): VideoDetailFragment {
            val args = Bundle()
            args.putCharSequence("typeId", typeId)
            val fragment = VideoDetailFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun getLayoutResId(): Int = R.layout.fragment_video_detail

    override fun finishCreateView(state: Bundle?) {
        LogUtil.d(TAG  + " finishcreateview")
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

    override fun loadData() {
        when (arguments) {
            null -> return
            else -> {
                typeId = arguments?.getString("typeId")
                presenter.getVideoDetails(pageNum, "list", typeId!!)
            }
        }
    }


    override fun initView() {
        initSimpleMultiStateView()
        loadData()
    }

    private fun initSimpleMultiStateView() {
        mSimpleMultiStateView?.setEmptyResource(R.layout.view_empty)
                ?.setRetryResource(R.layout.view_retry)
                ?.setLoadingResource(R.layout.view_loading)
                ?.setNoNetResource(R.layout.view_nonet)
                ?.build()
                ?.setOnReLoadListener { onRetry() }
        mPtrFrameLayout.disableWhenHorizontalMove(true)
        mPtrFrameLayout.setPtrHandler(object : PtrHandler {
            override fun checkCanDoRefresh(frame: PtrFrameLayout?, content: View?, header: View?): Boolean {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, mRecyclerView, header)
            }

            override fun onRefreshBegin(frame: PtrFrameLayout?) {
                pageNum = 1
                presenter.getVideoDetails(pageNum, "list", typeId!!)
            }
        })
        videoDetailBean = VideoDetailBean()
        mRecyclerView.layoutManager = LinearLayoutManager(activity)
        videoDetailAdapter = VideoDetailAdapter(SixCatApplication.getInstance(), R.layout.item_video_content, videoDetailBean?.item)
        mRecyclerView.adapter = videoDetailAdapter
        videoDetailAdapter.setLoadMoreView(CustomLoadMoreView())
        videoDetailAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN)
        videoDetailAdapter.setOnLoadMoreListener({ presenter.getVideoDetails(pageNum, "list", typeId!!) }, mRecyclerView)
        mRecyclerView.addOnChildAttachStateChangeListener(object : RecyclerView.OnChildAttachStateChangeListener{
            override fun onChildViewDetachedFromWindow(view: View?) {
                val jzvd = view?.findViewById<View>(R.id.videoplayer)
//                jzvd.on
//                if (jzvd != null && JCUtils.dataSourceObjectsContainsUri(jzvd., JCMediaManager.getCurrentDataSource())) {
//                    JCVideoPlayer.releaseAllVideos()
//                }
            }

            override fun onChildViewAttachedToWindow(view: View?) {
            }
        })
    }

    private fun onRetry() {

    }

    override fun dosetVideoChanel(videobeanList: List<VideoChannelBean>?) {
    }

    override fun loadVideoDetails(detailBean: List<VideoDetailBean>?) {
        when (detailBean) {
            null -> {
                onShowNetError()
                return
            }
            else -> {
                pageNum++
                videoDetailAdapter.setNewData(detailBean[0].item)
                mPtrFrameLayout.refreshComplete()
                success()
            }
        }
    }


    fun success() {
        mSimpleMultiStateView?.showContent()
    }

    override fun loadMoreVideoDetails(detailBean: List<VideoDetailBean>?) {
    }


    override fun onShowLoading() {
        mSimpleMultiStateView.showLoadingView()
    }

    override fun onHideLoading() {
        mSimpleMultiStateView?.showContent()
    }

    override fun onShowNetError() {
        mSimpleMultiStateView?.showNoNetView()
    }

    override fun setPresenterView(presenter: IVideoInterfaceManager.IVideoPresenter?) {
        if (presenter == null) {
            this.presenter = VideoPresenter(this)
        }
    }

    override fun onShowNoMore() {
    }


}