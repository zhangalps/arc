
package com.alone.news.arumenu.model
import com.alone.news.arumenu.api.NewsService
import com.alone.news.arumenu.entity.NewsEntity

import io.reactivex.Observable
import io.reactivex.functions.Function

/**
 * package : com.example.zhanghegang.arumenu.model
 * anthor : 张贺岗
 * Date : 2019/2/15
 * Use :新闻网络请求
 */
class NewsModel {
    private val service: NewsService = NewsService.Creater.getNewsService()

    /**
     * 获取信息
     */
    fun getToh(info : Map<String, String>): Observable<NewsEntity>{
        return service.getToh(info).map { t -> t }
    }

}