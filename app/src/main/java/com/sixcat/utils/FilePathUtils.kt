package com.sixcat.utils

import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.util.Log

import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * 通过uri 获取文件的路径，因为版本不同获取的方式也不一样
 * @author liguoying
 * @date 2017/12/4.
 */

internal object FilePathUtils {
    private var myDir: File? = null//文件目录
    private var myFile: File? = null//文件绝对路径
    private var myName: String? = null//文件自己起的名字

    val imageFile: File?
        get() {
            myDir = File(Environment.getExternalStorageDirectory(), "smart_seal")
            if (!myDir!!.exists()) {
                myDir!!.mkdirs()
            }
            myName = SimpleDateFormat("yyyyMMddHHmmss", Locale.CHINA).format(Date()) + ".jpg"
            myFile = File(myDir, myName!!)
            return myFile
        }

    /**
     * 获取文件的路径
     *
     * @param context
     * @param uri
     * @return
     */
    fun getFilePath(context: Context, uri: Uri): String? {
        /**
         * 判断版本是否大于4.4
         * 如果版本真的大于4.4那么……
         */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && DocumentsContract.isDocumentUri(context, uri)) {
            /**
             * 如果是媒体类型的数据
             */
            if (isMediaDocument(uri)) {
                val docId = DocumentsContract.getDocumentId(uri)
                val split = docId.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                val type = split[0]

                var contentUri: Uri? = null
                if ("image" == type) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                } else if ("video" == type) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI
                } else if ("audio" == type) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
                }
                val selection = "_id=?"
                val selectionArgs = arrayOf(split[1])
                return getDataColumn(context, contentUri, selection, selectionArgs)
            }
        } else if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            //外界程序访问ContentProvider所提供的数据，可以通过ContentResolver接口
            val column = MediaStore.MediaColumns.DATA
            val projection = arrayOf(column)
            val cursor = context.contentResolver.query(uri, projection, null, null, null)
            val index: Int
            //判断请求的类型，获取的索引值和类型相一致。
            index = cursor!!.getColumnIndexOrThrow(column)
            cursor.moveToFirst()
            val path = cursor.getString(index)
            Log.e("main.....", path)
            cursor.close()
            return path
        }
        /**
         * 如果版本小于4.4
         */
        return null
    }

    /**
     * 获取路径
     *
     * @param context
     * @param uri
     * @param selection
     * @param selectionArgs
     * @return
     */
    fun getDataColumn(context: Context, uri: Uri?, selection: String,
                      selectionArgs: Array<String>): String? {

        var cursor: Cursor? = null
        val column = "_data"
        val projection = arrayOf(column)

        try {
            cursor = context.contentResolver.query(uri!!, projection, selection, selectionArgs, null)
            if (cursor != null && cursor.moveToFirst()) {
                val index = cursor.getColumnIndexOrThrow(column)
                return cursor.getString(index)
            }
        } finally {
            cursor?.close()
        }
        return null
    }

    fun isMediaDocument(uri: Uri): Boolean {
        return "com.android.providers.media.documents" == uri.authority
    }

}
