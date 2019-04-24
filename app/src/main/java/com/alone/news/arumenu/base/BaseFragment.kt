package com.alone.news.arumenu.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * package : com.alone.news.arumenu.base
 * anthor : 张贺岗
 * Date : 2019/4/24
 * Use : <类的用途>
 */
abstract class BaseFragment :Fragment(){
    lateinit var childView:View
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
       childView = View.inflate(context,viewBindId(), null)
        return childView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
            viewInit(childView)
    }
    abstract fun viewBindId():Int
    abstract fun viewInit(view:View)
}