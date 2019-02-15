package com.arc.news.utils;

import android.app.Application;
import android.content.Context;

/**
 * package : com.example.alexa.utils.imageloader
 * anthor : 张贺岗
 * Date : 2019/2/13
 * Use : <类的用途>
 */
public class CoreApp extends Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        //捕获全局异常
//        CatchExceptionUtils.getInstance(this).init();
    }

    public static Context getContext() {
        return mContext;
    }
}
