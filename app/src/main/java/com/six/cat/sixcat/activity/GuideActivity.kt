package com.six.cat.sixcat.activity

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.KeyEvent
import android.widget.TextView

import com.jaeger.library.StatusBarUtil
import com.six.cat.sixcat.R
import com.six.cat.sixcat.base.BaseActivity
import com.six.cat.sixcat.constants.Constants
import com.six.cat.sixcat.module.base.IBasePresenter
import com.six.cat.sixcat.utils.SPUtil
import com.six.cat.sixcat.view.CustomerVideoView

import butterknife.BindView
import kotlinx.android.synthetic.main.activity_guide.*

/**
 * 首次进入app的展示：1.视频展示 2.多图片滑动
 */
class GuideActivity : BaseActivity<IBasePresenter>() {

    private var position: Int = 0

    override fun getLayoutId(): Int {
        return R.layout.activity_guide
    }

    override fun initView(savedInstanceState: Bundle ?) {
        StatusBarUtil.setTranslucent(this, 0)
        SPUtil.setBoolean("isFirst", true)
        playVideo()
        initViewClick()
    }

    override fun initToolBar() {

    }

    private fun initViewClick() {
        tvGuideExit.setOnClickListener { v -> finishVideo() }
    }

    private fun playVideo() {
        cvvGuideVideo.setVideoURI(Uri.parse("android.resource://" + this.packageName + Constants.PATHS_SEPARATOR + R.raw.video))
        cvvGuideVideo.start()
        cvvGuideVideo.setOnCompletionListener { mediaPlayer -> finishVideo() }
    }

    private fun finishVideo() {
        mManager!!.finishActivity()
        cvvGuideVideo.destroyDrawingCache()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode != 4) {
            return super.onKeyDown(keyCode, event)
        }
        mManager!!.finishActivity(this as Activity)
        cvvGuideVideo.destroyDrawingCache()
        return true

    }

    override fun onBackPressed() {
        super.onBackPressed()

    }

    override fun onResume() {
        super.onResume()
        cvvGuideVideo.seekTo(position)
        cvvGuideVideo.start()
    }

    override fun onPause() {
        super.onPause()
        cvvGuideVideo.pause()
        position = cvvGuideVideo.currentPosition
    }

    override fun onDestroy() {
        super.onDestroy()
        cvvGuideVideo.destroyDrawingCache()
        cvvGuideVideo
        mManager!!.finishActivity()
    }

    override fun onShowLoading() {

    }

    override fun onHideLoading() {

    }

    override fun onShowNetError() {

    }

    override fun mSetPresenter(presenter: IBasePresenter?) {
    }

    override fun onShowNoMore() {

    }
}
