package com.alone.news.arumenu.ui.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/**
 * package : com.alone.news.arumenu.ui.adapter
 * anthor : 张贺岗
 * Date : 2019/4/24
 * Use : <类的用途>
 */
class MainViewpagerAdapter(fm: FragmentManager?,var fragmentList:List<Fragment>) : FragmentPagerAdapter(fm) {
    override fun getItem(current: Int): Fragment {
        return fragmentList[current]
    }

    override fun getCount(): Int {
        return fragmentList.size
    }

}