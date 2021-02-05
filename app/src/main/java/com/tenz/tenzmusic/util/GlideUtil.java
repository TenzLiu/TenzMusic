package com.tenz.tenzmusic.util;

import android.content.Context;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.ImageViewTarget;
import com.tenz.tenzmusic.R;


/**
 * Author: TenzLiu
 * Date: 2018-02-01 09:54
 * Description: GlideUtil
 */

public class GlideUtil {

    /**
     * 默认RequestOptions
     */
    private static RequestOptions mRequestOptions = new RequestOptions()
            .placeholder(R.drawable.default_music_icon)
            .error(R.drawable.default_music_icon)
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .centerCrop();
    public static RequestOptions mLogoRequestOptions = new RequestOptions()
            .placeholder(R.drawable.logo)
            .error(R.drawable.logo)
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .centerCrop();
    private static DrawableTransitionOptions mDrawableTransitionOptions = new DrawableTransitionOptions()
            .crossFade(800);

    /**
     * 默认加载图片
     * @param context
     * @param url
     * @param imageView
     */
    public static void loadImage(Context context, Object url, ImageView imageView){
        Glide.with(context)
                .load(url)
                .apply(mRequestOptions)
                .into(imageView);
    }

    /**
     * 默认加载图片
     * @param fragment
     * @param url
     * @param imageView
     */
    public static void loadImage(Fragment fragment, Object url, ImageView imageView){
        Glide.with(fragment)
                .load(url)
                .apply(mRequestOptions)
                .into(imageView);
    }

    /**
     * 自定义默认图片
     * @param context
     * @param url
     * @param imageView
     * @param resId
     */
    public static void loadImage(Context context, Object url, int resId, ImageView imageView){
        Glide.with(context)
                .load(url)
                .apply(new RequestOptions()
                        .centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .placeholder(resId))
                .into(imageView);
    }

    /**
     * 自定义默认图片
     * @param fragment
     * @param url
     * @param imageView
     * @param resId
     */
    public static void loadImage(Fragment fragment, Object url, int resId, ImageView imageView){
        Glide.with(fragment)
                .load(url)
                .apply(new RequestOptions()
                        .centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .placeholder(resId))
                .into(imageView);
    }

    /**
     * 自定义RequestOptions
     * @param context
     * @param url
     * @param imageView
     */
    public static void loadImage(Context context, Object url, RequestOptions requestOptions, ImageView imageView){
        Glide.with(context)
                .asBitmap()
                .load(url)
                .apply(requestOptions)
                .into(imageView);
    }

    /**
     * 自定义RequestOptions
     * @param fragment
     * @param url
     * @param imageView
     */
    public static void loadImage(Fragment fragment, Object url, RequestOptions requestOptions, ImageView imageView){
        Glide.with(fragment)
                .load(url)
                .apply(requestOptions)
                .into(imageView);
    }

    /**
     * 加载图片回调
     * @param context
     * @param url
     * @param imageViewTarget
     */
    public static void loadImage(Context context, Object url, ImageViewTarget imageViewTarget){
        Glide.with(context)
                .load(url)
                .apply(mRequestOptions)
                .into(imageViewTarget);
    }

    /**
     * 加载图片回调
     * @param fragment
     * @param url
     * @param imageViewTarget
     */
    public static void loadImage(Fragment fragment, Object url, ImageViewTarget imageViewTarget){
        Glide.with(fragment)
                .load(url)
                .apply(mRequestOptions)
                .into(imageViewTarget);
    }

    /**
     * 加载图片回调
     * @param context
     * @param url
     * @param requestListener
     * @param imageView
     */
    public static void loadImage(Context context, Object url, RequestListener requestListener, ImageView imageView){
        Glide.with(context)
                .load(url)
                .apply(mRequestOptions)
                .listener(requestListener)
                .into(imageView);
    }

    /**
     * 加载图片回调
     * @param fragment
     * @param url
     * @param requestListener
     * @param imageView
     */
    public static void loadImage(Fragment fragment, Object url, RequestListener requestListener, ImageView imageView){
        Glide.with(fragment)
                .load(url)
                .apply(mRequestOptions)
                .listener(requestListener)
                .into(imageView);
    }

}
