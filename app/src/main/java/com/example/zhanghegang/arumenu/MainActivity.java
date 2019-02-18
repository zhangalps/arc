package com.example.zhanghegang.arumenu;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.zhanghegang.arumenu.api.CalendarUtils;
import com.arc.news.utils.util.LogUtils;
import com.example.zhanghegang.arumenu.autoview.ArcMenu;
import com.example.zhanghegang.arumenu.autoview.AruMenuView;
import com.example.zhanghegang.arumenu.entity.NewsEntity;
import com.example.zhanghegang.arumenu.model.NewsModel;
import com.example.zhanghegang.arumenu.presenter.NewsPresenter;
import com.example.zhanghegang.arumenu.view.NewsView;
import com.google.gson.Gson;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements NewsView {

    private static final String TAG = "ARCMENU";
    private AruMenuView aruMenuView;
    private ArcMenu arcMenu;
    private NewsPresenter newsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initMvp();

    }

    private void initMvp() {
        newsPresenter = new NewsPresenter(new NewsModel());
        newsPresenter.attachView(this);
        getDate();

    }

    private void getDate() {
//        long time = System.currentTimeMillis();
        int month = CalendarUtils.INSTANCE.getDate(Calendar.MONTH);
        int day = CalendarUtils.INSTANCE.getDate(Calendar.DATE);
        LogUtils.i(TAG,"month=="+month+"==day=="+day);
        newsPresenter.getToh(month+"",day+"");

    }

    private void initView() {
        aruMenuView = findViewById(R.id.aruMv);
        arcMenu = findViewById(R.id.arcMv);
        ImageView iv = findViewById(R.id.iv_test);
//        Glide.with(this).load("http://127.0.0.1:8080/for_hg/icon/composer_button.png").listener(new RequestListener<String, GlideDrawable>() {
//            @Override
//            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
//                Log.e(TAG,"onException====="+e.getMessage());
//                return false;
//            }
//
//            @Override
//            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
//                Log.e(TAG,"onResourceReady====="+model);
//                return false;
//            }
//        }).into(iv);
    }

    @Override
    public void sucess(NewsEntity data) {
        Gson gson = new Gson();
        String s = gson.toJson(data);
        LogUtils.i(TAG, "======sucess====="+s);
    }

    @Override
    public void fail(Throwable error) {

        LogUtils.e(TAG, "error==="+error.getMessage());
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
