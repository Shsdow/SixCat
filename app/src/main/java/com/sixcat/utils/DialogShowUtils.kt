package com.sixcat.utils

import android.app.Activity
import android.content.Context
import android.text.TextUtils
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.sixcat.R


/**
 * @uthor: GY.LEE
 * @date: 2019-05-20
 */


fun showEditDialog(context: Context, title: String, message: String,
                   listener: View.OnClickListener) {
    showEditDialog(context, title, message, arrayOf("输入 Title", "输入 Content"), listener,false)
}
 fun showEditDialog(context: Context, title: String, message: String,
                           hint: Array<String>, listener: View.OnClickListener,isEdit:Boolean) {
    val alertDialog = AlertDialog.Builder(context, R.style.ProcessDialog)
            .setCancelable(false).create()
    if ((context as Activity).isFinishing) {
        return
    }
    alertDialog.show()
    alertDialog.window.clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM)
    alertDialog.setContentView(R.layout.dialog_edit_reason)
    val tvTitle = alertDialog.window.findViewById(
            R.id.tvTitle) as TextView
    tvTitle.text = title
    val tvMsg = alertDialog.window.findViewById<TextView>(R.id.tvMessage)
    tvMsg.text = message

    val user = alertDialog.findViewById<EditText>(R.id.etInputUser)
    val password = alertDialog.findViewById<EditText>(R.id.etInputPass)
    user!!.hint = hint[0]
    password!!.hint = hint[1]

    if (TextUtils.isEmpty(message)) {
        tvMsg.visibility = View.GONE
    }
    alertDialog.window.findViewById<Button>(R.id.btnDeleteOrderCancel).setOnClickListener { alertDialog.dismiss() }
    alertDialog.window.findViewById<Button>(R.id.btnDeleteOrder).setOnClickListener {
        val sb = StringBuilder()
        val title = user?.text.toString().trim()
        val content = password?.text.toString().trim()
        if (TextUtils.isEmpty(title) && !isEdit) {
            ShowToast.shortTime("Task's title can't be empty.")
            return@setOnClickListener
        }
        sb.append("$title&")
        sb.append(content)
        it.tag = sb
        listener.onClick(it)
        alertDialog.dismiss()
    }
}

fun showDelete(context: Context, listener: View.OnClickListener) {
    val alertDialog = AlertDialog.Builder(context, R.style.ProcessDialog)
            .setCancelable(false).create()
    if ((context as Activity).isFinishing) {
        return
    }
    alertDialog.show()
    alertDialog.window.clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM)
    alertDialog.setContentView(R.layout.delete_item)

    alertDialog.window.findViewById<Button>(R.id.delete_cancel).setOnClickListener { alertDialog.dismiss() }
    alertDialog.window.findViewById<Button>(R.id.delete_agree).setOnClickListener {
        listener.onClick(it)
        alertDialog.dismiss()
    }
}