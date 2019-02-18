package com.alone.news.arumenu.api
import com.alone.news.arumenu.entity.NewsEntity
import com.arc.news.utils.retrofit.RetrofitUtils
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap
import java.util.*

/**
 * package : com.arc.news.utils.retrofit
 * anthor : 张贺岗
 * Date : 2019/2/14
 * Use : <类的用途>
 */
interface NewsService {
    @GET("japi/toh")
    fun getToh(@QueryMap info: Map<String, String>): Observable<NewsEntity>


    object Creater {
        fun getNewsService(): NewsService {
            val retrofitForLog = RetrofitUtils.getInstance().getRetrofitForLog(ApiConfig.BASE_URL)
            return retrofitForLog.create(NewsService::class.java)
        }
    }
}