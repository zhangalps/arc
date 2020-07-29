
package com.alone.news.arumenu.model

import com.alone.news.arumenu.api.NewsService
import com.alone.news.arumenu.entity.NewsEntity
import io.reactivex.Observable
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.util.*

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

    fun postMulti(): Observable<NewsEntity>{
        val files: List<File> = ArrayList()
        var parts: MutableList<MultipartBody.Part> = ArrayList()
        if(files.isNotEmpty()) {
            for (file: File in files) {
                var body: RequestBody = RequestBody.create(MediaType.parse("image/jpg"), file);
                var part: MultipartBody.Part = MultipartBody.Part.createFormData("image", file.name, body);
                parts.add(part)
            }
        }else{
            var part: MultipartBody.Part = MultipartBody.Part.createFormData("image", "");
            parts.add(part)
        }
        return service.uploadFilesWithParts(parts)
    }


}