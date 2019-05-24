package com.sixcat.widgets;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.sixcat.BuildConfig;

import java.security.MessageDigest;


/**
 * @author liguoying
 * @date 2018/1/16.
 */

public class GlideCircleTransform extends BitmapTransformation {
    private float radius = 0f;
    private static int useDp = 0;
    private static final int VERSION = 1;
    private final String ID = BuildConfig.APPLICATION_ID + ".GlideRoundRect." + VERSION;
    private final byte[] ID_BYTES = ID.getBytes(CHARSET);

//
//    public GlideCircleTransform(Context context, int dp) {
//        super(context);
//        radius = Resources.getSystem().getDisplayMetrics().density * dp;
//    }

    private GlideCircleTransform(Context context) {
        super(context);

    }

    private static GlideCircleTransform instance;

    public static GlideCircleTransform getInstance(Context context, int dp) {
        useDp = dp;
        if (instance == null) {
            synchronized (GlideCircleTransform.class) {
                if (instance == null) {
                    instance = new GlideCircleTransform(context);
                }
            }

        }

        return instance;
    }

    @Override
    protected Bitmap transform(BitmapPool pool, Bitmap toTransform,
                               int outWidth, int outHeight) {
        radius = Resources.getSystem().getDisplayMetrics().density * useDp;
        return roundCrop(pool, toTransform);
    }


    private Bitmap roundCrop(BitmapPool pool, Bitmap source) {
        if (source == null)
            return null;

        Bitmap result = pool.get(source.getWidth(), source.getHeight(),
                Bitmap.Config.ARGB_8888);
        if (result == null) {
            result = Bitmap.createBitmap(source.getWidth(), source.getHeight(),
                    Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(result);
        Paint paint = new Paint();
        paint.setShader(new BitmapShader(source, BitmapShader.TileMode.CLAMP,
                BitmapShader.TileMode.CLAMP));
        paint.setAntiAlias(true);
        RectF rectF = new RectF(0f, 0f, source.getWidth(), source.getHeight());
        canvas.drawRoundRect(rectF, radius, radius, paint);
        return result;
    }

    @Override
    public void updateDiskCacheKey(MessageDigest messageDigest) {
        messageDigest.update(ID_BYTES);
    }
}

