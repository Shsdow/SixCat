package com.six.cat.sixcat.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Matrix
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.graphics.Rect
import android.graphics.RectF
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.Base64
import android.util.DisplayMetrics
import android.view.WindowManager

import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream

/**
 * bitmap 操作工具类
 * @author liguoying
 * @date 2017/12/4.
 */

object BitmapUtil {
    /**
     * 裁剪成圆角正方形的图片
     *
     * @param bitmap
     * @return
     */
    fun framedImage(bitmap: Bitmap): Bitmap {
        //获得宽度和高度
        val width = bitmap.width
        val height = bitmap.height
        var avg = 0
        if (width > height) {
            avg = height
        } else {
            avg = width
        }
        val imageDrawable = BitmapDrawable(bitmap)
        //新建一个新的输出图片
        val outputBitmap = Bitmap.createBitmap(avg, avg, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(outputBitmap)
        //新建一个矩形
        val outerRect = RectF(0f, 0f, avg.toFloat(), avg.toFloat())
        //产生一个红色的圆角矩形
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.color = Color.RED
        canvas.drawRoundRect(outerRect, (avg / 10).toFloat(), (avg / 10).toFloat(), paint)
        //将原图片绘制到这个圆角矩形上
        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
        imageDrawable.setBounds(0, 0, avg, avg)
        canvas.saveLayer(outerRect, paint, Canvas.ALL_SAVE_FLAG)
        imageDrawable.draw(canvas)
        canvas.restore()
        return outputBitmap
    }

    /**
     * 图片尺寸压缩 （根据图片宽度）
     *
     * @param context
     * @param image
     * @return
     */
    fun compress(context: Context, image: Bitmap): Bitmap {

        var screenw = 480

        val manager = context
                .getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val dm = DisplayMetrics()
        manager.defaultDisplay.getMetrics(dm)
        screenw = dm.widthPixels

        val baos = ByteArrayOutputStream()
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        if (baos.toByteArray().size / 1024 > 1024) {// 判断如果图片大于1M,进行压缩避免在生成图片（BitmapFactory
            // .decodeStream）时溢出
            baos.reset()// 重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, 50, baos)// 这里压缩50%，把压缩后的数据存放到baos中
        }
        var isBm = ByteArrayInputStream(baos.toByteArray())
        val newOpts = BitmapFactory.Options()
        // 开始读入图片，此时把options.inJustDecodeBounds 设回true了
        newOpts.inJustDecodeBounds = true
        var bitmap = BitmapFactory.decodeStream(isBm, null, newOpts)
        newOpts.inJustDecodeBounds = false
        val w = newOpts.outWidth

        // 缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        var be = 1// be=1表示不缩放
        // 图片宽度大于屏幕，压缩
        if (w > screenw) {
            be = Math.ceil((w.toFloat() / screenw).toDouble()).toInt()
        }
        if (be <= 0)
            be = 1
        newOpts.inSampleSize = be// 设置缩放比例
        // 重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
        isBm = ByteArrayInputStream(baos.toByteArray())
        bitmap = BitmapFactory.decodeStream(isBm, null, newOpts)
        return bitmap
    }

    /**
     * 截取图片上部分横向充满组件
     */
    fun cutBitmap(context: Context, h: Int, bitmap: Bitmap): Bitmap {
        if (bitmap.width <= 0 || bitmap.height <= 0 || h <= 0) {

            return bitmap
        }
        val manager = context
                .getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val dm = DisplayMetrics()
        manager.defaultDisplay.getMetrics(dm)
        // 图片宽度大于屏幕宽度
        // if (bitmap.getWidth() > dm.widthPixels) {

        // 将图片缩放到屏幕同宽
        val bitmap2 = resizeImage(bitmap, dm.widthPixels.toFloat(), dm.widthPixels * (bitmap.height.toFloat() / bitmap.width))

        // 组件高度大于图片高度，不截取
        return if (h > bitmap2.height) {
            bitmap2
        } else {
            // 截取图片
            Bitmap
                    .createBitmap(bitmap2, 0, 0, bitmap2.width, h)
        }

        // } else {
        // // 组件高度大于图片高度，不截取
        // if (h > bitmap.getHeight()) {
        // return bitmap;
        // } else {
        // // 截取高度
        // Bitmap b = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
        // h);
        // return b;
        // }
        // }
    }

    // 使用Bitmap加Matrix来缩放
    fun resizeImage(bitmap: Bitmap, w: Float, h: Float): Bitmap {
        val width = bitmap.width
        val height = bitmap.height

        val scaleWidth = w / width
        val scaleHeight = h / height

        val matrix = Matrix()
        matrix.postScale(scaleWidth, scaleHeight)
        // if you want to rotate the Bitmap
        // matrix.postRotate(45);
        return if (width > 0 && height > 0) {
            Bitmap.createBitmap(bitmap, 0, 0, width,
                    height, matrix, true)
        } else bitmap

    }

    //将Bitmap转换成字符串
    fun toBase64String(bitmap: Bitmap): String {
        val bStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bStream)
        var i = 0
        while (bStream.toByteArray().size / 1024 > 100) {
            bStream.reset()
            bitmap.compress(Bitmap.CompressFormat.JPEG, (50 * Math.pow(0.5, i.toDouble())).toInt(), bStream)
            println((bStream.toByteArray().size / 1024).toString() + " " + (50 * Math.pow(0.5, i.toDouble())).toInt())
            i++
        }
        val bytes = bStream.toByteArray()
        return Base64.encodeToString(bytes, Base64.DEFAULT)
    }

    /**
     * 缩放图片，解决bitmap 内存溢出out of memory的问题
     * 获取的图片如果太大,有可能造成程序崩溃，所以不在内存中处理这些图片。
     *
     * @param context
     * @param filePath
     * @return
     */
    fun getScaleBitmap(context: Context, filePath: String): Bitmap {
        val opt = BitmapFactory.Options()
        opt.inJustDecodeBounds = true//很重要
        var bm = BitmapFactory.decodeFile(filePath, opt)
        val bmWidth = opt.outWidth
        val bmHeight = opt.outHeight//获取到这个原始图片的宽度和高度
        val manager = context
                .getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val dm = DisplayMetrics()
        manager.defaultDisplay.getMetrics(dm)
        val screenWidth = dm.widthPixels
        val screenHeight = dm.heightPixels//获取到这个屏幕的宽度和高度
        opt.inSampleSize = 1//isSampleSize是表示对图片的缩放程度，比如值为2图片的宽度和高度都变为以前的1/2
        if (bmWidth > bmHeight) {
            if (bmWidth > screenWidth) {
                opt.inSampleSize = bmWidth / screenWidth
            }
        } else {
            if (bmHeight > screenHeight) {
                opt.inSampleSize = bmHeight / screenHeight
            }
        }
        opt.inJustDecodeBounds = false//这次再真正地生成一个有像素的，经过缩放了的bitmap
        bm = BitmapFactory.decodeFile(filePath, opt)
        return bm
    }

    /**
     * 裁剪成圆形的图片
     *
     * @param bitmap
     * @return
     */
    fun toRoundBitmap(bitmap: Bitmap): Bitmap {
        var width = bitmap.width
        var height = bitmap.height
        val roundPx: Float
        val left: Float
        val top: Float
        val right: Float
        val bottom: Float
        val dst_left: Float
        val dst_top: Float
        val dst_right: Float
        val dst_bottom: Float
        if (width <= height) {
            roundPx = (width / 2).toFloat()
            top = 0f
            bottom = width.toFloat()
            left = 0f
            right = width.toFloat()
            height = width
            dst_left = 0f
            dst_top = 0f
            dst_right = width.toFloat()
            dst_bottom = width.toFloat()
        } else {
            roundPx = (height / 2).toFloat()
            val clip = ((width - height) / 2).toFloat()
            left = clip
            right = width - clip
            top = 0f
            bottom = height.toFloat()
            width = height
            dst_left = 0f
            dst_top = 0f
            dst_right = height.toFloat()
            dst_bottom = height.toFloat()
        }
        val output = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(output)
        val color = -0xbdbdbe
        val paint = Paint()
        val src = Rect(left.toInt(), top.toInt(), right.toInt(),
                bottom.toInt())
        val dst = Rect(dst_left.toInt(), dst_top.toInt(),
                dst_right.toInt(), dst_bottom.toInt())
        val rectF = RectF(dst)
        paint.isAntiAlias = true
        canvas.drawARGB(0, 0, 0, 0)
        paint.color = color
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint)
        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
        canvas.drawBitmap(bitmap, src, dst, paint)
        return output
    }

    fun saveBitmap(bitmap: Bitmap): String? {
        val cacheDir = FilePathUtils.getImageFile()
        try {
            val out = FileOutputStream(cacheDir)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out)
            out.flush()
            out.close()
            return cacheDir.path
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }

    }

}
