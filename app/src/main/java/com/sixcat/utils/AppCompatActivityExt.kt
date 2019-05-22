package com.sixcat.utils

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.sixcat.jetpack.viewmodel.ViewModelFactory

/**
 * @uthor: GY.LEE
 * @date: 2019-05-21
 */

/**
 * 通过扩展，在 AppCompatActivity 中获得指定的 ViewModel
 */
fun <T : ViewModel> AppCompatActivity.obtainViewModel(viewModelClass: Class<T>) =
        ViewModelProviders.of(this, ViewModelFactory.getInstance(application)).get(viewModelClass)
