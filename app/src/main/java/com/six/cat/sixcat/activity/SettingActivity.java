package com.six.cat.sixcat.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.six.cat.sixcat.R;
import com.six.cat.sixcat.base.BaseActivity;

public class SettingActivity extends BaseActivity implements ColorChooserDialog.ColorCallback{

    @Override
    public int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected void initToolBar() {

    }
}
