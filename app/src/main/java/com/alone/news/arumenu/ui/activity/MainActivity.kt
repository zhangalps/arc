package com.alone.news.arumenu.ui.activity

import android.annotation.SuppressLint
import android.support.design.internal.BottomNavigationItemView
import android.support.design.internal.BottomNavigationMenuView
import android.support.design.widget.BottomNavigationView
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.MenuItem
import butterknife.BindView
import com.alone.news.arumenu.R
import com.alone.news.arumenu.R.id.*
import com.alone.news.arumenu.base.BaseActivity
import com.alone.news.arumenu.ui.adapter.MainViewpagerAdapter
import com.alone.news.arumenu.ui.fragment.LeftFragment
import com.alone.news.arumenu.ui.fragment.MiddleFragment
import com.alone.news.arumenu.ui.fragment.RightFragment
import com.arc.news.utils.util.LogUtils


/**
 * package : com.alone.news.arumenu.ui
 * anthor : 张贺岗
 * Date : 2019/4/23
 * Use : app主页
 */
class MainActivity : BaseActivity(), BottomNavigationView.OnNavigationItemSelectedListener, ViewPager.OnPageChangeListener {
    override fun onPageScrollStateChanged(p0: Int) {
    }

    override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {

    }

    override fun onPageSelected(current: Int) {
        when(current){
            0-> bn_main.selectedItemId = R.id.action_left
            1-> bn_main.selectedItemId = R.id.action_middle
            2-> bn_main.selectedItemId = R.id.action_right
        }
    }

    @BindView(R.id.vp_main)
    lateinit var vp_main: ViewPager
    @BindView(R.id.bn_main)
    lateinit var bn_main: BottomNavigationView

    override fun viewBingLayout(): Int {
        return R.layout.activity_middle_main
    }

    override fun viewInit(layout: LayoutInflater) {
//        setStatusBar(true)
        disableShiftMode(bn_main)
        bn_main.setOnNavigationItemSelectedListener(this)
        bn_main.selectedItemId = R.id.action_left
        vpInit()

    }

    /**
     * viewPager初始化
     */
    private fun vpInit() {
        vp_main.addOnPageChangeListener(this)
        val leftFragment = LeftFragment()
        val middleFragment = MiddleFragment()
        val rightFragment = RightFragment()
        val fragmentList = arrayListOf(leftFragment, middleFragment, rightFragment)
        val vpAdapter = MainViewpagerAdapter(supportFragmentManager, fragmentList)
        vp_main.offscreenPageLimit = 3
        vp_main.adapter = vpAdapter
        vp_main.currentItem = 0

    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val itemId = item.itemId
        LogUtils.d(LogUtils.ALL_TAG, "onNavigationItemSelected:$itemId")
        when (itemId) {
            action_left -> {
                vp_main.currentItem = 0
            }
            action_middle -> {
                vp_main.currentItem = 1
            }
            action_right -> {
                vp_main.currentItem = 2
            }
        }
        return true
    }

    @SuppressLint("RestrictedApi")
    fun disableShiftMode(navigationView: BottomNavigationView) {

        val menuView = navigationView.getChildAt(0) as BottomNavigationMenuView
        try {
            val shiftingMode = menuView.javaClass.getDeclaredField("mShiftingMode")
            shiftingMode.isAccessible = true
            shiftingMode.setBoolean(menuView, false)
            shiftingMode.isAccessible = false

            for (i in 0 until menuView.childCount) {
                val itemView = menuView.getChildAt(i) as BottomNavigationItemView
                itemView.setShiftingMode(false)
                itemView.setChecked(itemView.itemData.isChecked)
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
}