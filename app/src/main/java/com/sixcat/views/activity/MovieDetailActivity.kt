package com.sixcat.views.activity

import android.graphics.Bitmap
import android.os.Build
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.palette.graphics.Palette
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.sixcat.R
import com.sixcat.adapter.MovieShowcaseDiscussAdapter
import com.sixcat.base.BaseActivity
import com.sixcat.model.bean.MovieShowcaseBean
import com.sixcat.presenter.MovieDetailContract
import com.sixcat.presenter.contract.MovieDetailPresenter
import com.sixcat.utils.DisplayUtil
import com.sixcat.utils.WindowUtil
import kotlinx.android.synthetic.main.activity_movie_showcase.*
import kotlinx.android.synthetic.main.movie_showcase_image.*
import kotlinx.android.synthetic.main.movie_showcase_movie_content.*
import java.util.*
import kotlin.collections.ArrayList

/**
 * @author liguoying
 * @date 2018/1/27.
 */
class MovieDetailActivity : BaseActivity(), MovieDetailContract.View {


    private var movieId: String? = null
    private var showCaseList: ArrayList<MovieShowcaseBean.PopularCommentsBean>? = null

    private val adapter by lazy { MovieShowcaseDiscussAdapter(this, showCaseList) }

    private val mPresenter by lazy { MovieDetailPresenter() }

    override fun getLayoutId() = R.layout.activity_movie_showcase

    override fun initView() {
        movieId = this.intent.getStringExtra("movieId")
        mPresenter.attachView(this)
        setLayoutParams()
        initRecyclerview()
        initScrollView()
        loadData()
    }

    private fun setLayoutParams() {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
        }
        val layoutParams: ViewGroup.LayoutParams = vw_action.layoutParams
        layoutParams.width = WindowUtil.instance.getScreenWidth(this)
        layoutParams.height = getStatusBarHeight()
        vw_action.layoutParams = layoutParams
    }

    private fun getStatusBarHeight(): Int {
        var result = 0
        val resourcesId = resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourcesId > 0) result = resources.getDimensionPixelOffset(resourcesId)
        return result
    }

    private fun initScrollView() {
        val titleHeight = DisplayUtil.dp2px(40f)
        val headHeight = DisplayUtil.dp2px(220f)
        obsvAllData.setIScrollViewLisenter { _, dy, isUp ->
            val movieDistance = (headHeight - titleHeight).toFloat()
            tvTitleMovieName.visibility = View.GONE
            if (!isUp && dy <= movieDistance) {
                titlebarAlphaChange(dy, movieDistance)
            } else if (!isUp && dy > movieDistance) {
                titlebarAlphaChange(1, 1f)
                tvTitleMovieName.visibility = View.VISIBLE
            } else if (isUp && dy > movieDistance) {

            } else if (isUp && dy <= movieDistance) {
                titlebarAlphaChange(dy, movieDistance)
                ivFinish.setImageResource(R.drawable.finish)
            }
        }
    }

    fun titlebarAlphaChange(dy: Int, mHeadHeighPx: Float) {
        val rate: Float = Math.abs(dy) / Math.abs(mHeadHeighPx)
        val alpha: Int = (rate * 255).toInt()
        tbToolbar.background.alpha = alpha
        vw_action.background.alpha = alpha
        ivFinish.background.alpha = 300 - alpha
    }

    private fun initRecyclerview() {
        ivFinish.setOnClickListener { finish() }
        showCaseList = ArrayList()
        val linearlayoutManager = LinearLayoutManager(this)
        linearlayoutManager.orientation = RecyclerView.VERTICAL
        rvDiscuss.layoutManager = linearlayoutManager
        rvDiscuss.isNestedScrollingEnabled = false
//        adapter = MovieShowcaseDiscussAdapter(this, showCaseList)
        rvDiscuss.adapter = adapter
    }


    fun loadData() {
//        onShowLoading()
        mPresenter.doLoadData(movieId)
    }

    override fun setMovieDetail(movieShowcaseBean: MovieShowcaseBean) {
        doSetData(movieShowcaseBean)
    }

    override fun showError(errorMsg: String, errorCode: Int) {
    }

    override fun showLoading() {
    }

    override fun dismissLoading() {
    }

    fun doSetData(movieShowcaseBean: MovieShowcaseBean) {
        tvMovieName.text = movieShowcaseBean.title
        tvTitleMovieName.text = movieShowcaseBean.title
        var yaear: String? = movieShowcaseBean.year
        movieShowcaseBean.genres!!.forEach {
            yaear += "/" + it
        }
        tvMovieShortShow.text = String.format(Locale.CHINA, resources.getString(R.string.movie_show_case), yaear, movieShowcaseBean.mainland_pubdate, movieShowcaseBean.durations?.get(0).toString())
        Glide.with(this).asBitmap().load(movieShowcaseBean.images?.large).into(object : SimpleTarget<Bitmap>() {
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>) {
                ivMovieImage.setImageBitmap(resource)
                Palette.from(resource).generate { palette ->
                    val color = palette!!.getDominantColor(ContextCompat.getColor(this@MovieDetailActivity, R.color.blue_light))
                    tbToolbar.setBackgroundColor(color)
                    vw_action.setBackgroundColor(color)
                    tbToolbar.background.alpha = 0
                    vw_action.background.alpha = 0
                    flBack.setBackgroundColor(color)
                }
            }
        })
        tvMovieScore.text = movieShowcaseBean.rating?.average.toString()
        tvWatherNum.text = String.format(Locale.CHINA, resources.getString(R.string.movie_show_case_count), movieShowcaseBean.ratings_count)
        rbStarNum.rating = movieShowcaseBean.rating?.average!!.toFloat() / 2
        showCaseList?.addAll(movieShowcaseBean.popular_comments!!)
        adapter.notifyDataSetChanged()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (activityStackManager.currentActivity() != null) {
            activityStackManager.finishActivity()
        }
    }

}