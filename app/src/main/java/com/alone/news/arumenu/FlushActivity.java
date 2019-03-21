package com.alone.news.arumenu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.alone.news.arumenu.presenter.Test;


//import androidx.appcompat.app.AppCompatActivity;

public class FlushActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flush);
//        Test.INSTANCE.saySomething();
        Test.saySomething();
    }
}
