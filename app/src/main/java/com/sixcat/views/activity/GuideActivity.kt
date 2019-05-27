package com.sixcat.views.activity

import android.net.Uri
import android.view.KeyEvent
import com.orhanobut.logger.Logger
import com.sixcat.R
import com.sixcat.base.BaseActivity
import com.sixcat.utils.PATHS_SEPARATOR
import com.sixcat.utils.SPUtil
import com.sixcat.utils.StatusBarUtil
import kotlinx.android.synthetic.main.activity_guide.*

/**
 * 首次进入app的展示：1.视频展示 2.多图片滑动
 */
class GuideActivity : BaseActivity() {

    private var position: Int = 0

    override fun getLayoutId() = R.layout.activity_guide

    override fun initView() {
        StatusBarUtil.setTranslucent(this, 0)
        SPUtil.setBoolean("isFirst", true)
        playVideo()
        initViewClick()
    }

    private fun initViewClick() {
        tvGuideExit.setOnClickListener {
            Logger.e("GuideActivity %s", "initViewClick")

            finishVideo()
        }
    }

    private fun playVideo() {
        cvvGuideVideo.setVideoURI(Uri.parse("android.resource://" + this.packageName + PATHS_SEPARATOR + R.raw.video))
        cvvGuideVideo.start()
        cvvGuideVideo.setOnCompletionListener {
            Logger.e("GuideActivity %s", "setOnCompletionListener")
            finishVideo()
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode != 4) {
            return super.onKeyDown(keyCode, event)
        }
        Logger.d("GuideActivity %s", "onKeyDown")
        finishVideo()
        return true
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
        Logger.d("GuideActivity %s", "onDestroy")
        finishVideo()
    }

    private fun finishVideo() {
        Logger.d("GuideActivity %s", "finishVideo")
        cvvGuideVideo.destroyDrawingCache()
        activityStackManager.finishActivity(GuideActivity::class.java)
    }
}
