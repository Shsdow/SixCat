package com.sixcat.views.activity

import com.afollestad.materialdialogs.color.ColorChooserDialog
import com.sixcat.R
import com.sixcat.base.BaseActivity

class SettingActivity : BaseActivity(), ColorChooserDialog.ColorCallback {
    override fun initView() {

    }

    override fun getLayoutId() = R.layout.activity_setting


    override fun onColorSelection(dialog: ColorChooserDialog, selectedColor: Int) {

    }

    override fun onColorChooserDismissed(dialog: ColorChooserDialog) {

    }


}
