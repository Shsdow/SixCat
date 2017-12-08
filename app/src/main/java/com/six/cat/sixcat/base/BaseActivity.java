package com.six.cat.sixcat.base;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.six.cat.sixcat.R;
import com.six.cat.sixcat.utils.ActivityManager;

public class BaseActivity extends AppCompatActivity {

    public ActivityManager mManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mManager  = ActivityManager.getInstance();
        mManager.addActivity(this);
    }
}
