package com.six.cat.sixcat.module.movieshowcase

import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.graphics.Palette
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.six.cat.sixcat.R
import com.six.cat.sixcat.adapter.MovieShowcaseDiscussAdapter
import com.six.cat.sixcat.base.BaseActivity
import com.six.cat.sixcat.bean.MovieShowcaseBean
import com.six.cat.sixcat.module.movieshowcase.IMovieShowcaseManager.IMoviewShowcasePresenter
import com.six.cat.sixcat.utils.DisplayUtil
import com.six.cat.sixcat.utils.WindowUtil
import kotlinx.android.synthetic.main.activity_movie_showcase.*
import kotlinx.android.synthetic.main.movie_showcase_image.*
import kotlinx.android.synthetic.main.movie_showcase_movie_content.*
import java.util.*
import kotlin.collections.ArrayList

/**
 * @author liguoying
 * @date 2018/1/27.
 */
class MovieShowcaseActivity : BaseActivity<IMoviewShowcasePresenter>(), IMovieShowcaseManager.IMoviewShowcaseView {



    private var movieId: String? = null
    private var adapter: MovieShowcaseDiscussAdapter? = null
    private var showCaseList: ArrayList<MovieShowcaseBean.PopularCommentsBean>? = null


    override fun getLayoutId(): Int {
        return R.layout.activity_movie_showcase
    }

    override fun initView(savedInstanceState: Bundle?) {
        movieId = this.intent.getStringExtra("movieId")
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
        layoutParams.width = WindowUtil.getInstance().getScreenWidth(this)
        layoutParams.height = getStatusBarHeight()
        vw_action.layoutParams = layoutParams
    }

    private fun getStatusBarHeight(): Int {
        var result: Int = 0
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
        linearlayoutManager.orientation = LinearLayoutManager.VERTICAL
        rvDiscuss.layoutManager = linearlayoutManager
        rvDiscuss.isNestedScrollingEnabled = false
        adapter = MovieShowcaseDiscussAdapter(this, showCaseList)
        rvDiscuss.adapter = adapter
    }

    override fun initToolBar() {

    }

    override fun loadData() {
        onShowLoading()
        presenter.doLoadData(movieId)
    }

    override fun onShowLoading() {

    }

    override fun onHideLoading() {

    }

    override fun onShowNetError() {

    }

    override fun setPresenter(presenter: IMoviewShowcasePresenter?) {
        if (presenter == null) {
            this.presenter = MovieShowcasePresenter(this)
        }
    }

    override fun onShowNoMore() {

    }

    override fun doSetData(movieShowcaseBean: MovieShowcaseBean) {
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
                    val color = palette.getDominantColor(ContextCompat.getColor(this@MovieShowcaseActivity, R.color.blue_light))
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
        adapter!!.notifyDataSetChanged()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (mManager.currentActivity() != null) {
            mManager.finishActivity()
        }
    }

}