package com.six.cat.sixcat.module.movieshowcase

import android.os.Bundle
import android.util.Log
import com.six.cat.sixcat.R
import com.six.cat.sixcat.base.BaseActivity
import com.six.cat.sixcat.module.movieshowcase.IMovieShowcaseManager.IMoviewShowcasePresenter
import com.six.cat.sixcat.utils.LogUtil
import com.trello.rxlifecycle2.LifecycleTransformer
import kotlinx.android.synthetic.main.movie_showcase_image.*

/**
 * @author liguoying
 * @date 2018/1/27.
 */
class MovieShowcaseActivity : BaseActivity(), IMovieShowcaseManager.IMoviewShowcaseView {
    //    constructor() {
//        var intent: Intent = this.intent
//        var movieId = intent.getStringExtra("movieId")
//    }
    private var movieId: String? = null


    private lateinit var presenter: IMoviewShowcasePresenter

    override fun getLayoutId(): Int {
        return R.layout.activity_movie_showcase
    }

    override fun initView(savedInstanceState: Bundle?) {
        movieId = this.intent.getStringExtra("movieId")
    }

    override fun initToolBar() {
    }

    override fun loadData() {
        onShowLoading()
        presenter.doLoadData(movieId)
    }

    override fun onShowLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onHideLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onShowNetError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setPresenter(presenter: IMoviewShowcasePresenter?) {
        if (presenter == null) {
            this.presenter = MovieShowcasePresenter(this)
        }
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onSetAdapter(list: MutableList<*>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onShowNoMore() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun <T : Any?> bindToLife(): LifecycleTransformer<T> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}