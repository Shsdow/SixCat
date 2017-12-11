package com.six.cat.sixcat.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.TextView;

import com.six.cat.sixcat.R;
import com.six.cat.sixcat.base.BaseActivity;
import com.six.cat.sixcat.constants.Constants;
import com.six.cat.sixcat.utils.SpShareUtil;
import com.six.cat.sixcat.view.CustomerVideoView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 首次进入app的展示：1.视频展示 2.多图片滑动
 */
public class GuideActivity extends BaseActivity {
    @BindView(R.id.cvv_guide_video)
    CustomerVideoView mVideoView;
    @BindView(R.id.tv_guide_exit)
    TextView mGuideExit;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        playVideo();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_guide;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        initViewClick();
    }

    @Override
    protected void initToolBar() {

    }

    private void initViewClick() {
        mGuideExit.setOnClickListener(v -> {
                    finishVideo();
                }
        );
    }

    private void playVideo() {
        mVideoView.setVideoURI(Uri.parse("android.resource://" + this.getPackageName() + Constants.PATHS_SEPARATOR + R.raw.video));
        mVideoView.start();
        mVideoView.setOnCompletionListener(mediaPlayer -> {
            finishVideo();
        });
    }

    private void finishVideo() {
        startActivity(new Intent(this, MainActivity.class));
        SpShareUtil.setString("firstGuide", "ok");
        mManager.finishActivity();
        mVideoView.destroyDrawingCache();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode != 4) {
            return super.onKeyDown(keyCode, event);
        }
        mManager.finishActivity((Activity) this);
        mVideoView.destroyDrawingCache();
        return true;

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }

    @Override
    protected void onResume() {
        super.onResume();
        mVideoView.seekTo(position);
        mVideoView.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mVideoView.pause();
        position = mVideoView.getCurrentPosition();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mVideoView.destroyDrawingCache();
    }
}
