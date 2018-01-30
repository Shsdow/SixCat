package com.six.cat.sixcat.module.movieshowcase

import android.os.Bundle
import android.util.Log
import com.six.cat.sixcat.R
import com.six.cat.sixcat.base.BaseActivity
import com.six.cat.sixcat.module.movieshowcase.IMovieShowcaseManager.IMoviewShowcasePresenter
import kotlinx.android.synthetic.main.movie_showcase_image.*

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

    }

}