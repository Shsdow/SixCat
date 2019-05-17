package com.sixcat.utils

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.sixcat.SixCatApplication
import java.util.*

/**
 * 持久化存储工具类
 *
 * @author liguoying
 * @date 2017/12/4.
 */

object SPUtil {

    private var mSharedPreferences: SharedPreferences? = null

    private fun init() {
        if (mSharedPreferences == null) {
            mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(
                    SixCatApplication.context)
        }
    }

    fun setInt(key: String, value: Int) {
        if (mSharedPreferences == null) {
            init()
        }
        mSharedPreferences!!.edit().putInt(key, value).apply()
    }

    fun getInt(key: String): Int {
        if (mSharedPreferences == null) {
            init()
        }
        return getInt(key, -1)
    }

    fun getInt(key: String, defaultValue: Int): Int {
        if (mSharedPreferences == null) {
            init()
        }
        return mSharedPreferences!!.getInt(key, defaultValue)
    }

    fun setFloat(key: String, value: Float) {
        if (mSharedPreferences == null) {
            init()
        }
        mSharedPreferences!!.edit().putFloat(key, value).apply()
    }

    fun getFloat(key: String): Float {
        if (mSharedPreferences == null) {
            init()
        }
        return getFloat(key, -1f)
    }

    fun getFloat(key: String, defaultValue: Float): Float {
        if (mSharedPreferences == null) {
            init()
        }
        return mSharedPreferences!!.getFloat(key, defaultValue)
    }

    fun setBoolean(key: String, value: Boolean) {
        if (mSharedPreferences == null) {
            init()
        }
        mSharedPreferences!!.edit().putBoolean(key, value).apply()
    }

    fun getBoolean(key: String): Boolean {
        if (mSharedPreferences == null) {
            init()
        }
        return getBoolean(key, false)
    }

    fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        if (mSharedPreferences == null) {
            init()
        }
        return mSharedPreferences!!.getBoolean(key, defaultValue)
    }

    fun setString(key: String, value: String) {
        if (mSharedPreferences == null) {
            init()
        }
        mSharedPreferences!!.edit().putString(key, value).apply()
    }

    fun getString(key: String): String? {
        if (mSharedPreferences == null) {
            init()
        }
        return getString(key, "")
    }

    fun getString(key: String, defaultValue: String?): String {
        if (mSharedPreferences == null) {
            init()
        }
        return mSharedPreferences!!.getString(key, defaultValue)
    }

    fun remove(key: String) {
        if (mSharedPreferences == null) {
            init()
        }
        mSharedPreferences!!.edit().remove(key).apply()
    }

    fun clearAll() {
        if (mSharedPreferences == null) {
            init()
        }
        mSharedPreferences!!.edit().clear().apply()
    }

    fun getInt(context: Context, key: String,
               defaultValue: Int): Int {
        if (mSharedPreferences == null) {
            init()
        }
        return getInt(key, defaultValue)
    }

    fun setSharedStringData(context: Context, key: String,
                            value: String) {
        if (mSharedPreferences == null) {
            init()
        }
        setString(key, value)
    }

    fun remove(context: Context, key: String) {
        if (mSharedPreferences == null) {
            init()
        }
        remove(key)
    }

    fun clearAll(context: Context) {
        if (mSharedPreferences == null) {
            init()
        }
        clearAll()
    }

    fun putStrListValue(context: Context, key: String,
                        strList: List<String>?) {
        if (null == strList) {
            return
        }
        // 保存之前先清理已经存在的数据，保证数据的唯一性
        removeStrList(context, key)
        val size = strList.size
        setInt(key + "size", size)
        for (i in 0 until size) {
            setString(key + i, strList[i])
        }
    }

    fun removeStrList(context: Context, key: String) {
        val size = getInt(context, key + "size", 0)
        if (0 == size) {
            return
        }
        remove(context, key + "size")
        for (i in 0 until size) {
            remove(context, key + i)
        }
    }

    fun getStrListValue(context: Context, key: String): List<String> {
        val strList = ArrayList<String>()
        val size = getInt(context, key + "size", 0)
        //Log.d("sp", "" + size);
        for (i in 0 until size) {
            getString(key + i, null)?.let { strList.add(it) }
        }
        return strList
    }
}
