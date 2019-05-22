package com.sixcat.utils

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.sixcat.jetpack.viewmodel.ViewModelFactory

/**
 * @uthor: GY.LEE
 * @date: 2019-05-21
 */

fun <T : ViewModel> Fragment.obtainViewModel(viewModelClass: Class<T>) =
        ViewModelProviders.of(this,
                ViewModelFactory.getInstance(activity!!.application)).get(viewModelClass)
