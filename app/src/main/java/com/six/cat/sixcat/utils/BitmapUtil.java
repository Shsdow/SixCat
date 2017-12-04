package com.six.cat.sixcat.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

/**
 * bitmap 操作工具类
 * @author liguoying
 * @date 2017/12/4.
 */

public class BitmapUtil {
    /**
     * 裁剪成圆角正方形的图片
     *
     * @param bitmap
     * @return
     */
    public static Bitmap framedImage(Bitmap bitmap) {
        //获得宽度和高度
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int avg = 0;
        if (width > height) {
            avg = height;
        } else {
            avg = width;
        }
        Drawable imageDrawable = new BitmapDrawable(bitmap);
        //新建一个新的输出图片
        Bitmap outputBitmap = Bitmap.createBitmap(avg, avg, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(outputBitmap);
        //新建一个矩形
        RectF outerRect = new RectF(0, 0, avg, avg);
        //产生一个红色的圆角矩形
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.RED);
        canvas.drawRoundRect(outerRect, avg / 10, avg / 10, paint);
        //将原图片绘制到这个圆角矩形上
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        imageDrawable.setBounds(0, 0, avg, avg);
        canvas.saveLayer(outerRect, paint, Canvas.ALL_SAVE_FLAG);
        imageDrawable.draw(canvas);
        canvas.restore();
        return outputBitmap;
    }

    /**
     * 图片尺寸压缩 （根据图片宽度）
     *
     * @param context
     * @param image
     * @return
     */
    public static Bitmap compress(Context context, Bitmap image) {

        int screenw = 480;

        WindowManager manager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(dm);
        screenw = dm.widthPixels;

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        if (baos.toByteArray().length / 1024 > 1024) {// 判断如果图片大于1M,进行压缩避免在生成图片（BitmapFactory
            // .decodeStream）时溢出
            baos.reset();// 重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, 50, baos);// 这里压缩50%，把压缩后的数据存放到baos中
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        // 开始读入图片，此时把options.inJustDecodeBounds 设回true了
        newOpts.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;

        // 缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        int be = 1;// be=1表示不缩放
        // 图片宽度大于屏幕，压缩
        if (w > screenw) {
            be = (int) Math.ceil(((float) w / screenw));
        }
        if (be <= 0)
            be = 1;
        newOpts.inSampleSize = be;// 设置缩放比例
        // 重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
        isBm = new ByteArrayInputStream(baos.toByteArray());
        bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
        return bitmap;
    }

    /**
     * 截取图片上部分横向充满组件
     */
    public static Bitmap cutBitmap(Context context, int h, Bitmap bitmap) {
        if (bitmap.getWidth() <= 0 || bitmap.getHeight() <= 0 || h <= 0) {

            return bitmap;
        }
        WindowManager manager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(dm);
        // 图片宽度大于屏幕宽度
        // if (bitmap.getWidth() > dm.widthPixels) {

        // 将图片缩放到屏幕同宽
        Bitmap bitmap2 = resizeImage(bitmap, dm.widthPixels, dm.widthPixels
                * ((float) bitmap.getHeight() / bitmap.getWidth()));

        // 组件高度大于图片高度，不截取
        if (h > bitmap2.getHeight()) {
            return bitmap2;
        } else {
            // 截取图片
            Bitmap b = Bitmap
                    .createBitmap(bitmap2, 0, 0, bitmap2.getWidth(), h);
            return b;
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
    public static Bitmap resizeImage(Bitmap bitmap, float w, float h) {
        Bitmap BitmapOrg = bitmap;
        int width = BitmapOrg.getWidth();
        int height = BitmapOrg.getHeight();
        float newWidth = w;
        float newHeight = h;

        float scaleWidth = (newWidth) / width;
        float scaleHeight = (newHeight) / height;

        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        // if you want to rotate the Bitmap
        // matrix.postRotate(45);
        if (width > 0 && height > 0) {
            Bitmap resizedBitmap = Bitmap.createBitmap(BitmapOrg, 0, 0, width,
                    height, matrix, true);
            return resizedBitmap;
        }

        return BitmapOrg;
    }

    //将Bitmap转换成字符串
    public static String toBase64String(Bitmap bitmap) {
        ByteArrayOutputStream bStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bStream);
        int i = 0;
        while (bStream.toByteArray().length / 1024 > 100) {
            bStream.reset();
            bitmap.compress(Bitmap.CompressFormat.JPEG, (int) (50 * Math.pow(0.5, i)), bStream);
            System.out.println(bStream.toByteArray().length / 1024 + " " + (int) (50 * Math.pow(0.5, i)));
            i++;
        }
        byte[] bytes = bStream.toByteArray();
        return Base64.encodeToString(bytes, Base64.DEFAULT);
    }

    /**
     * 缩放图片，解决bitmap 内存溢出out of memory的问题
     * 获取的图片如果太大,有可能造成程序崩溃，所以不在内存中处理这些图片。
     *
     * @param context
     * @param filePath
     * @return
     */
    public static Bitmap getScaleBitmap(Context context, String filePath) {
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inJustDecodeBounds = true;//很重要
        Bitmap bm = BitmapFactory.decodeFile(filePath, opt);
        int bmWidth = opt.outWidth;
        int bmHeight = opt.outHeight;//获取到这个原始图片的宽度和高度
        WindowManager manager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(dm);
        int screenWidth = dm.widthPixels;
        int screenHeight = dm.heightPixels;//获取到这个屏幕的宽度和高度
        opt.inSampleSize = 1;//isSampleSize是表示对图片的缩放程度，比如值为2图片的宽度和高度都变为以前的1/2
        if (bmWidth > bmHeight) {
            if (bmWidth > screenWidth) {
                opt.inSampleSize = bmWidth / screenWidth;
            }
        } else {
            if (bmHeight > screenHeight) {
                opt.inSampleSize = bmHeight / screenHeight;
            }
        }
        opt.inJustDecodeBounds = false;//这次再真正地生成一个有像素的，经过缩放了的bitmap
        bm = BitmapFactory.decodeFile(filePath, opt);
        return bm;
    }

    /**
     * 裁剪成圆形的图片
     *
     * @param bitmap
     * @return
     */
    public static Bitmap toRoundBitmap(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        float roundPx;
        float left, top, right, bottom, dst_left, dst_top, dst_right, dst_bottom;
        if (width <= height) {
            roundPx = width / 2;
            top = 0;
            bottom = width;
            left = 0;
            right = width;
            height = width;
            dst_left = 0;
            dst_top = 0;
            dst_right = width;
            dst_bottom = width;
        } else {
            roundPx = height / 2;
            float clip = (width - height) / 2;
            left = clip;
            right = width - clip;
            top = 0;
            bottom = height;
            width = height;
            dst_left = 0;
            dst_top = 0;
            dst_right = height;
            dst_bottom = height;
        }
        Bitmap output = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect src = new Rect((int) left, (int) top, (int) right,
                (int) bottom);
        final Rect dst = new Rect((int) dst_left, (int) dst_top,
                (int) dst_right, (int) dst_bottom);
        final RectF rectF = new RectF(dst);
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, src, dst, paint);
        return output;
    }

    public static String saveBitmap(Bitmap bitmap) {
        File cacheDir = FilePathUtils.getImageFile();
        try {
            FileOutputStream out = new FileOutputStream(cacheDir);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();
            return cacheDir.getPath();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
