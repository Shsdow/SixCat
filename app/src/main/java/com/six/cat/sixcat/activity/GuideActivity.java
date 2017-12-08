package com.six.cat.sixcat.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.TextView;

import com.six.cat.sixcat.MainActivity;
import com.six.cat.sixcat.R;
import com.six.cat.sixcat.base.BaseActivity;
import com.six.cat.sixcat.constants.Constants;
import com.six.cat.sixcat.utils.SpShareUtil;
import com.six.cat.sixcat.view.CustomerVideoView;

public class GuideActivity extends BaseActivity {
    private CustomerVideoView mVideoView;
    private TextView mGuideExit;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        initView();
        playVideo();
    }

    private void initView() {
        mVideoView = findViewById(R.id.cvv_guide_video);
        mGuideExit = findViewById(R.id.tv_guide_exit);
        mGuideExit.setOnClickListener(v -> {
                    startActivity(new Intent(this, MainActivity.class));
                    SpShareUtil.setString("firstGuide", "ok");
                    mManager.finishActivity();
                    mVideoView.destroyDrawingCache();
                }
        );
    }

    private void playVideo() {
        mVideoView.setVideoURI(Uri.parse("android.resource://" + this.getPackageName() + Constants.PATHS_SEPARATOR + R.raw.video));
        mVideoView.start();
        mVideoView.setOnCompletionListener(mediaPlayer -> {
            startActivity(new Intent(this,MainActivity.class));
            SpShareUtil.setString("firstGuide", "ok");
            mManager.finishActivity();
            mVideoView.destroyDrawingCache();
        });
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
