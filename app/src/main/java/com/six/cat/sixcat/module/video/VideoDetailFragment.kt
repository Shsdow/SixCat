package com.six.cat.sixcat.module.video

import android.os.Bundle
import com.six.cat.sixcat.base.BaseRxLazyFragment

/**
 * @author liguoying
 * @date 2018/2/6.
 */
class VideoDetailFragment: BaseRxLazyFragment<IVideoInterfaceManager.IVideoPresenter>() {
    override fun onShowLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onHideLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onShowNetError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setPresenter(presenter: IVideoInterfaceManager.IVideoPresenter?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onShowNoMore() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getLayoutResId(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun finishCreateView(state: Bundle?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object {
        fun newInstance(typeId: String): VideoDetailFragment {
            val args = Bundle()
            args.putCharSequence("typeId", typeId)
            val fragment = VideoDetailFragment()
            fragment.arguments = args
            return fragment
        }
    }
}