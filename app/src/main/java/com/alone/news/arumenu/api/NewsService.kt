package com.alone.news.arumenu.api

import ApiConfig
import com.alone.news.arumenu.entity.NewsEntity
import com.arc.news.utils.retrofit.RetrofitUtils
import io.reactivex.Observable
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*


/**
 * package : com.arc.news.utils.retrofit
 * anthor : 张贺岗
 * Date : 2019/2/14
 * Use : <类的用途>
 */
interface NewsService {
    @GET("japi/toh")
    fun getToh(@QueryMap info: Map<String, String>): Observable<NewsEntity>

    @Multipart
    @POST("users/image")
    fun uploadFilesWithParts(@Part parts: List<MultipartBody.Part>):Observable<NewsEntity>


    object Creater {
        fun getNewsService(): NewsService {
            val retrofitForLog = RetrofitUtils.getInstance().getRetrofitForLog(ApiConfig.BASE_URL)
            return retrofitForLog.create(NewsService::class.java)
        }
    }
}