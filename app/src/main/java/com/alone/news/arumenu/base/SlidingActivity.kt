package com.alone.news.arumenu.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.alone.news.arumenu.base.sliding.SlidingLayout

/**
 * package : com.alone.news.arumenu.base
 * anthor : 张贺岗
 * Date : 2019/4/22
 * Use : <类的用途>
 */
open class SlidingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (enableSliding()){
            val rootView = SlidingLayout(this)
            rootView.bindActivity(this)
        }
    }
    protected fun enableSliding() : Boolean{
       return true
    }
}