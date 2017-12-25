package com.six.cat.sixcat.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.afollestad.materialdialogs.color.ColorChooserDialog;
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

    @Override
    public void onColorSelection(@NonNull ColorChooserDialog dialog, int selectedColor) {

    }

    @Override
    public void onColorChooserDismissed(@NonNull ColorChooserDialog dialog) {

    }
}
