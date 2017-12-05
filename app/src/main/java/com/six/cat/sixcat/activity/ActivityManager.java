package com.six.cat.sixcat.activity;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * activity 管理类
 *
 * @author liguoying
 * @date 2017/12/5.
 */

public class ActivityManager {
    private static List<Activity> mActivityArrayList = new ArrayList<>();

    public static void addActivity(Activity activity) {
        mActivityArrayList.add(activity);
    }

    public static Activity getCurrentActivity() {
        return mActivityArrayList.isEmpty() ? null : mActivityArrayList.get(mActivityArrayList.size() - 1);
    }

    public static Activity findActivity(Class clazz){
        Activity activity = null ;
        for(Activity aty : mActivityArrayList){
            if(aty.getClass() ==clazz ){
                activity = aty;
            }
        }
        return activity;
    }

    private static void finishAcitivity(Activity activity){
        if(activity!= null){
            mActivityArrayList.remove(activity);
            activity.finish();
        }
    }






}
