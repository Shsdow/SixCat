package com.six.cat.sixcat.view

import android.support.design.widget.Snackbar
import android.view.View

/**
 * @author liguoying
 * @date 2018/3/16.
 */
object SnarkBarUtil {
    fun showSnakbarMessage(view: View, text: String) {
        Snackbar.make(view,text,Snackbar.LENGTH_SHORT).show()
    }
}