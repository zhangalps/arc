package com.example.zhanghegang.arumenu.base

import android.os.Build
import android.support.annotation.RequiresApi
import com.arc.news.utils.CoreApp
import com.arc.news.utils.util.NetUtils
import com.arc.news.utils.util.ToastUtils
import io.reactivex.functions.Consumer
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * package : com.example.zhanghegang.arumenu.base
 * anthor : 张贺岗
 * Date : 2019/2/18
 * Use : <类的用途>
 */
@RequiresApi(Build.VERSION_CODES.N)
open class BaseConsumer<T> : Consumer<T> {
    override fun accept(t: T) {
        when (t) {
            is Throwable -> multiError(t)
        }
    }

    fun multiError(te: Throwable) {
        if (!NetUtils.isConnect()) {
            ToastUtils.showToast(CoreApp.getContext(), "请检查网络", false)
        }else{
            when(te){
                is SecurityException ->
                    ToastUtils.showToast(CoreApp.getContext(), "请授予权限", false)
                is SocketTimeoutException ->
                    ToastUtils.showToast(CoreApp.getContext(), "网络不给力", false)
                is UnknownHostException ->
                    ToastUtils.showToast(CoreApp.getContext(), "系统繁忙，稍后刷新重试", false)
                else ->
                    ToastUtils.showToast(CoreApp.getContext(), "系统繁忙，稍后刷新重试", false)
            }
        }
    }

}