package com.six.cat.sixcat.module.movieshowcase

import android.os.Bundle
import com.six.cat.sixcat.R
import com.six.cat.sixcat.base.BaseRxLazyFragment

/**
 * @author liguoying
 * @date 2018/1/27.
 */
class MovieShowcaseFragment : BaseRxLazyFragment<IMovieShowcaseManager.IMoviewShowcasePresenter>(),IMovieShowcaseManager.IMoviewShowcaseView{
    override fun onShowLoading() {

    }

    override fun onHideLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onShowNetError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setPresenter(presenter: IMovieShowcaseManager.IMoviewShowcasePresenter?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun onShowNoMore() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getLayoutResId(): Int {
        return R.layout.fragment_movie_showcase
    }

    override fun finishCreateView(state: Bundle?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadData() {
    }

}