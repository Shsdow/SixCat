package com.six.cat.sixcat.utils;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 通过uri 获取文件的路径，因为版本不同获取的方式也不一样
 * @author liguoying
 * @date 2017/12/4.
 */

class FilePathUtils {
    private static File myDir;//文件目录
    private static File myFile;//文件绝对路径
    private static String myName;//文件自己起的名字

    public static File getImageFile() {
        myDir = new File(Environment.getExternalStorageDirectory(), "smart_seal");
        if (!myDir.exists()) {
            myDir.mkdirs();
        }
        myName = new SimpleDateFormat("yyyyMMddHHmmss", Locale.CHINA).format(new Date()) + ".jpg";
        myFile = new File(myDir, myName);
        return myFile;
    }

    /**
     * 获取文件的路径
     *
     * @param context
     * @param uri
     * @return
     */
    public static String getFilePath(Context context, Uri uri) {
        /**
         * 判断版本是否大于4.4
         * 如果版本真的大于4.4那么……
         */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && DocumentsContract.isDocumentUri(context, uri)) {
            /**
             * 如果是媒体类型的数据
             */
            if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }
                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{
                        split[1]
                };
                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        }
        /**
         * 如果版本小于4.4
         */
        else if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            //外界程序访问ContentProvider所提供的数据，可以通过ContentResolver接口
            final String column = MediaStore.MediaColumns.DATA;
            final String[] projection = {column};
            Cursor cursor = context.getContentResolver().query(uri, projection, null, null, null);
            int index;
            //判断请求的类型，获取的索引值和类型相一致。
            index = cursor.getColumnIndexOrThrow(column);
            cursor.moveToFirst();
            String path = cursor.getString(index);
            Log.e("main.....", path);
            cursor.close();
            return path;
        }
        return null;
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
    public static String getDataColumn(Context context, Uri uri, String selection,
                                       String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {
                column
        };

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }

    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

}
