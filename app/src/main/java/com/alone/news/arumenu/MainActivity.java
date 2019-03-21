package com.alone.news.arumenu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.alone.news.arumenu.autoview.ArcMenu;
import com.alone.news.arumenu.autoview.AruMenuView;
import com.alone.news.arumenu.entity.NewsEntity;
import com.alone.news.arumenu.model.NewsModel;
import com.alone.news.arumenu.presenter.NewsPresenter;
import com.alone.news.arumenu.utils.CalendarUtils;
import com.alone.news.arumenu.view.NewsView;
import com.arc.news.utils.util.LogUtils;
import com.google.gson.Gson;

import java.util.Calendar;

//import androidx.appcompat.app.AppCompatActivity;

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
//        DUILiteSDK.openLog();
//        DDS.getInstance().setDebugMode(2);

    }

    public static void main(String[] args) {
//        CalendarUtils.test("1111");
//        ToastUtils.Companion.test("1111");

    }
    private void initMvp() {
        newsPresenter = new NewsPresenter(new NewsModel());
        newsPresenter.attachView(this);
        getDate();

    }

    private void getDate() {
//        long time = System.currentTimeMillis();
        String month = CalendarUtils.INSTANCE.getDate(Calendar.MONTH);
        String day = CalendarUtils.INSTANCE.getDate(Calendar.DATE);
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
