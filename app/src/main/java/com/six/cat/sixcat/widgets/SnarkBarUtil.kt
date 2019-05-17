package com.six.cat.sixcat.widgets

import android.view.View
import com.google.android.material.snackbar.Snackbar

/**
 * @author liguoying
 * @date 2018/3/16.
 */
object SnarkBarUtil {
    fun showSnakbarMessage(view: View, text: String) {
        Snackbar.make(view,text,Snackbar.LENGTH_SHORT).show()
    }
}