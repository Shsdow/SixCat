package com.six.cat.sixcat.widgets;

import android.support.annotation.NonNull;

import com.six.cat.sixcat.BuildConfig;

import io.reactivex.functions.Consumer;

/**
 * @author liguoying
 * @date 2018/1/12.
 */

public class ErrorAction {
    @NonNull
    public static Consumer<Throwable> error() {
        return throwable -> {
            if (BuildConfig.DEBUG) {
                throwable.printStackTrace();
            }
        };
    }

    public static void print(@NonNull Throwable throwable) {
        if (BuildConfig.DEBUG) {
            throwable.printStackTrace();
        }
    }
}
