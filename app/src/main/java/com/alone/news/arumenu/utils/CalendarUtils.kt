package com.alone.news.arumenu.utils
import java.util.*

/**
 * package : com.arc.news.utils.util
 * anthor : 张贺岗
 * Date : 2019/2/15
 * Use : 时间工具类
 */
 object CalendarUtils {

    var calendar: Calendar? = null
    init {
        calendar = Calendar.getInstance()
    }

    /**
     * 获取日月年
     */
    fun getDate(filed: Int): Int {
        when(filed){
            Calendar.MONTH -> return calendar!!.get(filed)+1
        }
        return calendar!!.get(filed)
    }
}