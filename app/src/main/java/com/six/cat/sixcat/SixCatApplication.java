package com.six.cat.sixcat;

import android.app.Application;

/**
 * @author liguoying
 * @date 2017/12/4.
 *
 */

public class SixCatApplication extends Application {
    private static SixCatApplication instance;

    public static SixCatApplication getInstance() {
        SixCatApplication application;
        synchronized (SixCatApplication.class) {
            if (instance == null) {
                synchronized (SixCatApplication.class) {
                    instance = new SixCatApplication();
                }
            }
            application = instance;
        }
        return application;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
