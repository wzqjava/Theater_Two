package com.bw.movie.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.NonNull;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

import java.security.MessageDigest;

/**
 * date:2019/1/7
 * author:赵豪轩
 * function:
 */
public class GlideRoundedCornersUtil{
//extends BitmapTransformation {
   /* private static float radius = 0f;
    public GlideRoundedCornersUtil(Context context) {
        this(context,10);
    }

    public GlideRoundedCornersUtil(Context context,int size) {
        super(context);
        radius = Resources.getSystem().getDisplayMetrics().density * size;
    }

    @Override
    protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {

        return roundCrop(pool, toTransform);
    }

    private static Bitmap roundCrop(BitmapPool pool, Bitmap source) {
        if (source == null) {
            return null;
        }

        Bitmap result = pool.get(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
        if (result == null) {
            result = Bitmap.createBitmap(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(result);
        Paint paint = new Paint();
        paint.setShader(new BitmapShader(source, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
        paint.setAntiAlias(true);
        RectF rectF = new RectF(0f, 0f, source.getWidth(), source.getHeight());
        canvas.drawRoundRect(rectF, radius, radius, paint);
        return result;
    }

    //@Override
    public String getId() {
        return getClass().getName() + Math.round(radius);
    }

    *//**
     * Adds all uniquely identifying information to the given digest.
     *
     * <p> Note - Using {@link MessageDigest#reset()} inside of this method will result
     * in undefined behavior. </p>
     *
     * @param messageDigest
     *//*
    @Override
    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {

    }*/
}
