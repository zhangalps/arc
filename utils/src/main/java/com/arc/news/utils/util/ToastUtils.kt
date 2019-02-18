package com.arc.news.utils.util

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.widget.Toast

/**
 * package : com.arc.news.utils.util
 * anthor : 张贺岗
 * Date : 2019/2/18
 * Use : <类的用途>
 */
class ToastUtils {
    companion object {
        //吐司展示
        fun showToast(context: Context, text: CharSequence, longToast: Boolean) {
            if (false)return
            if (Looper.getMainLooper().thread.id != Thread.currentThread().id) {
                object : Handler(Looper.getMainLooper()) {
                    override fun handleMessage(msg: Message) {
                        Toast.makeText(context, text, if (longToast) Toast.LENGTH_LONG else Toast.LENGTH_SHORT).show()
                    }
                }
            }else{
                Toast.makeText(context, text, if (longToast) Toast.LENGTH_LONG else Toast.LENGTH_SHORT).show()
            }
        }
    }
}