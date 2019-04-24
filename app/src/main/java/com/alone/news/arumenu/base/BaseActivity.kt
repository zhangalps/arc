package com.alone.news.arumenu.base

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import butterknife.ButterKnife

/**
 * package : com.alone.news.arumenu.base
 * anthor : 张贺岗
 * Date : 2019/4/22
 * Use : <类的用途>
 */
abstract class BaseActivity : SlidingActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewId = viewBingLayout()
        if (viewId != 0){
            setView(viewId)
        }
        viewInit(LayoutInflater.from(this))
    }

    private fun setView(viewId: Int) {
        setContentView(viewId)
        ButterKnife.bind(this)

    }

    override fun onDestroy() {
        super.onDestroy()
        ButterKnife.bind(this).unbind()
    }

    abstract fun viewBingLayout():Int
    abstract fun viewInit(layout:LayoutInflater)

    /**
     * 设置状态栏颜色
     */
    fun setStatusBar(isCustomStatusBar: Boolean){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0及以上
            val decorView = window.decorView
            val option = decorView.systemUiVisibility or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            decorView.systemUiVisibility = option
            //根据上面设置是否对状态栏单独设置颜色
            if (isCustomStatusBar) {
                window.statusBarColor = Color.parseColor("#ff6160")
            } else {
                window.statusBarColor = Color.parseColor("#000000")
            }
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//4.4到5.0
            val localLayoutParams = window.attributes
            localLayoutParams.flags = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS or localLayoutParams.flags
        }
    }

    /**
     * 显示加载中
     */
    fun showLoading(){

    }

    /**
     * 隐藏加载中
     */
    fun hideLoading(){

    }

}