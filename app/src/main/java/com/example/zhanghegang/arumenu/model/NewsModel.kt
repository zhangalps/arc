package com.example.zhanghegang.arumenu.model

import com.example.zhanghegang.arumenu.api.NewsService
import com.example.zhanghegang.arumenu.entity.NewsEntity
import io.reactivex.Observable
import io.reactivex.functions.Function

/**
 * package : com.example.zhanghegang.arumenu.model
 * anthor : 张贺岗
 * Date : 2019/2/15
 * Use : <类的用途>
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