package com.example.zhanghegang.arumenu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.zhanghegang.arumenu.autoview.ArcMenu;
import com.example.zhanghegang.arumenu.autoview.AruMenuView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "ARCMENU";
    private AruMenuView aruMenuView;
    private ArcMenu arcMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initMvp();

    }

    private void initMvp() {

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
}
