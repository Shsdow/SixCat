package com.six.cat.sixcat.module.movieshowcase

import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.six.cat.sixcat.R
import com.six.cat.sixcat.base.BaseActivity
import com.six.cat.sixcat.module.movieshowcase.IMovieShowcaseManager.IMoviewShowcasePresenter
import com.six.cat.sixcat.utils.LogUtil
import kotlinx.android.synthetic.main.movie_showcase_image.*
import kotlinx.android.synthetic.main.movie_showcase_movie_content.*
import java.util.*

/**
 * @author liguoying
 * @date 2018/1/27.
 */
class MovieShowcaseActivity : BaseActivity<IMoviewShowcasePresenter>(), IMovieShowcaseManager.IMoviewShowcaseView {
    private var movieId: String? = null


    override fun getLayoutId(): Int {
        return R.layout.activity_movie_showcase
    }

    override fun initView(savedInstanceState: Bundle?) {
        movieId = this.intent.getStringExtra("movieId")
        loadData()
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
        tvMovieName.text = movieShowcaseBean.original_title
        var yaear: String? = movieShowcaseBean.year
        movieShowcaseBean.genres!!.forEach {
            yaear += "/" + it
        }
        tvMovieShortShow.text = String.format(Locale.CHINA, resources.getString(R.string.movie_show_case), yaear, movieShowcaseBean.mainland_pubdate, movieShowcaseBean.durations?.get(0).toString())
        Glide.with(this).load(movieShowcaseBean.images?.large).into(ivMovieImage)
        tvMovieScore.text = movieShowcaseBean.rating?.average.toString()
        tvWatherNum.text = String.format(Locale.CHINA, resources.getString(R.string.movie_show_case_count), movieShowcaseBean.ratings_count)
        rbStarNum.rating = movieShowcaseBean.rating?.average!!.toFloat() / 2

    }

}