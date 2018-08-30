package com.six.cat.sixcat;

import android.app.Application;

import com.akaita.java.rxjava2debug.RxJava2Debug;
import com.squareup.leakcanary.LeakCanary;


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
//        if (LeakCanary.isInAnalyzerProcess(this)) {
//            return;
//        }
//        Fabric.with(this, new Crashlytics());

        // Enable RxJava assembly stack collection, to make RxJava crash reports clear and unique
        // Make sure this is called AFTER setting up any Crash reporting mechanism as Crashlytics
        RxJava2Debug.enableRxJava2AssemblyTracking(new String[]{"com.six.cat.sixcat", "com.six.cat.sixcat"});

        LeakCanary.install(this);

    }
}
