package com.six.cat.sixcat.utils;

import android.app.Activity;
import android.content.Context;

import java.util.Iterator;
import java.util.Stack;

/**
 * 通过 stack 管理 Activity 工具类
 * @author liguoying
 * @date 2017/12/5.
 */

public class ActivityManager {
    private static Stack<Activity> activityStack;
    private static ActivityManager instance;

    public static ActivityManager getInstance() {
        if (instance == null) {
            synchronized (ActivityManager.class) {
                if (instance == null) {
                    instance = new ActivityManager();
                }
            }
        }
        return instance;
    }

    public void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack();
        }
        activityStack.add(activity);
    }

    public Activity currentActivity() {
        return (Activity) activityStack.lastElement();
    }

    public void finishActivity() {
        finishActivity((Activity) activityStack.lastElement());
    }

    public void finishActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            activity.finish();
        }
    }

    public void finishActivity(Class<?> cls) {
        Iterator it = activityStack.iterator();
        while (it.hasNext()) {
            Activity activity = (Activity) it.next();
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
            }
        }
    }

    public void finishAllActivity() {
        int size = activityStack.size();
        for (int i = 0; i < size; i++) {
            if (activityStack.get(i) != null) {
                ((Activity) activityStack.get(i)).finish();
            }
        }
        activityStack.clear();
    }
    public void AppExit(Context context) {
        try {
            finishAllActivity();
            ((android.app.ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE)).killBackgroundProcesses(context.getPackageName());
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
