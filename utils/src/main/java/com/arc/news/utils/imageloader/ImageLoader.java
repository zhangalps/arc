package com.arc.news.utils.imageloader;

import android.text.TextUtils;
import android.widget.ImageView;

import com.arc.news.utils.CoreApp;
import com.bumptech.glide.BitmapTypeRequest;
import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.io.File;



/**
 * Created by zhanghegang on 18-4-17.
 * <p>
 * descript:创建图片加载工具类，使用Ｇlide加载图片进行二次封装
 */
public class ImageLoader {
    /**
     * 使用单例模式，减少类的重复创建
     */
    private static ImageLoader imageLoader;

    private ImageLoader() {
    }

    /**
     * 双重锁校验工具类是否为空
     *
     * @return
     */
    public static ImageLoader getInstace() {
        if (imageLoader == null) {
            synchronized (ImageLoader.class) {
                if (imageLoader == null) {
                    imageLoader = new ImageLoader();
                }
            }
        }
        return imageLoader;
    }

    /**
     * 加载网络图片
     *
     * @param url
     * @param mImageView
     */
    public void displayImage(String url, ImageView mImageView) {
        if (TextUtils.isEmpty(url) || mImageView == null) return;
        Glide.with(CoreApp.getContext()).load(url).into(mImageView);
    }

    /**
     * 加载网络图片,不进行缓存的方法
     *
     * @param url
     * @param mImageView
     */
    public void displayImageNoCache(String url, ImageView mImageView) {
        if (TextUtils.isEmpty(url) || mImageView == null) return;
        Glide.with(CoreApp.getContext()).load(url).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(mImageView);
    }

    /**
     * 加载本地图片
     *
     * @param id
     * @param mImageView
     */
    public void displayImage(int id, ImageView mImageView) {
        if (mImageView == null) return;
        Glide.with(CoreApp.getContext()).load(id).into(mImageView);
    }

    /**
     * 加载相册图片
     * @param file
     * @param mImageView
     */
    public void displayImage(File file, ImageView mImageView) {
        Glide.with(CoreApp.getContext()).load(file).into(mImageView);
    }

    /**
     * 加载网络图片有默认图
     *
     * @param url
     * @param mImageView
     * @param deftImage  默认图
     */
    public void displayImage(String url, ImageView mImageView, int deftImage) {
        if (TextUtils.isEmpty(url) || mImageView == null) return;
        Glide.with(CoreApp.getContext()).load(url)
                .placeholder(deftImage)
                .error(deftImage)
                .into(mImageView);
    }

    /**
     * 加载圆角图片（注意：只能加载在线图片）
     *
     * @param url
     * @param mImageView
     * @param calculation 圆角弧度
     */
    public void displayImageRound(String url, ImageView mImageView, int calculation) {
        if (TextUtils.isEmpty(url) || mImageView == null) return;
        Glide.with(CoreApp.getContext()).load(url)
                .transform(new GlideRoundTransform(CoreApp.getContext(), calculation))
                .into(mImageView);
    }

    /**
     * 加载在线ＧIF动图
     *
     * @param url
     * @param mImageView
     * @param firstFrame 　是否只加载第一帧
     */
    public void displayGIFImage(String url, ImageView mImageView, boolean firstFrame) {
        if (TextUtils.isEmpty(url) || mImageView == null) return;
        DrawableTypeRequest<String> load = Glide.with(CoreApp.getContext()).load(url);
        BitmapTypeRequest<String> bitmapTYpeRequest = null;
        if (firstFrame) {
            bitmapTYpeRequest = load.asBitmap();
            bitmapTYpeRequest.diskCacheStrategy(DiskCacheStrategy.SOURCE).into(mImageView);
        }
        if (bitmapTYpeRequest == null) {
            load.diskCacheStrategy(DiskCacheStrategy.SOURCE).into(mImageView);
        }
    }

    /**
     * 加载本地ＧIF动图
     *
     * @param id
     * @param mImageView
     * @param firstFrame 　是否只加载第一帧
     */
    public void displayGIFImage(int id, ImageView mImageView, boolean firstFrame) {
        if (mImageView == null) return;
        DrawableTypeRequest<Integer> load = Glide.with(CoreApp.getContext()).load(id);
        BitmapTypeRequest<Integer> bitmapTYpeRequest = null;
        if (firstFrame) {
            bitmapTYpeRequest = load.asBitmap();
            bitmapTYpeRequest.diskCacheStrategy(DiskCacheStrategy.SOURCE).into(mImageView);
        }
        if (bitmapTYpeRequest == null) {
            load.diskCacheStrategy(DiskCacheStrategy.SOURCE).into(mImageView);
        }
    }

}
