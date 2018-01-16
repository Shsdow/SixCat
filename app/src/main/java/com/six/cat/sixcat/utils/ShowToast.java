package com.six.cat.sixcat.utils;

import android.support.annotation.IdRes;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.six.cat.sixcat.R;
import com.six.cat.sixcat.SixCatApplication;

/**
 * Toast 工具类
 *
 * @author liguoying
 * @date 2017/12/4.
 */

public class ShowToast {
    private static Toast toast;
    private static TextView tvText;

    public static void shortTime(int resId) {
        shortTime(SixCatApplication.getInstance().getString(resId));
    }

    public static void shortTime(String text) {
        if (SixCatApplication.getInstance() == null) {
            return;
        }
        if (TextUtils.isEmpty(text)) {
            return;
        }
        if (toast == null) {
            View view = View.inflate(SixCatApplication.getInstance(), R.layout.layout_toast_body, null);
            tvText = (TextView) view.findViewById(R.id.tv_toast);
            tvText.setText(String.valueOf(text));
            toast = Toast.makeText(SixCatApplication.getInstance(), String.valueOf(text),
                    Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.BOTTOM, 0, DisplayUtil.dp2px(120));
            toast.setView(view);
        } else {
            tvText.setText(String.valueOf(text));
        }
        toast.show();
    }
}
