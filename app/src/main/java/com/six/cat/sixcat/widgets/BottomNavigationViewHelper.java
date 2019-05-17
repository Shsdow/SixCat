package com.six.cat.sixcat.widgets;

import android.annotation.SuppressLint;
import android.util.Log;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;

/**
 * @author liguoying
 * @date 2017/12/11.
 */

public class BottomNavigationViewHelper {

    @SuppressLint("RestrictedApi")
    public static void disableShiftMode(BottomNavigationView view) {
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
        //            Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
//            shiftingMode.setAccessible(true);
//            shiftingMode.setBoolean(menuView, false);
//            shiftingMode.setAccessible(false);

        menuView.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);

        for (int i = 0; i < menuView.getChildCount(); i++) {
            BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
            item.setShifting(false);
            //noinspection RestrictedApi
//                item.setShiftingMode(false);
            // set once again checked value, so view will be updated
            //noinspection RestrictedApi
//                item.setChecked(item.getItemData().isChecked());
        }
    }
}