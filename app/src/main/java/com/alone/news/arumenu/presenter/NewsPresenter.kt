package com.alone.news.arumenu.presenter

import android.annotation.SuppressLint
import android.os.Build
import android.support.annotation.RequiresApi
import com.alone.news.arumenu.api.AppDataKey
import com.alone.news.arumenu.base.BaseConsumer
import com.alone.news.arumenu.base.BasePresenter
import com.alone.news.arumenu.entity.NewsEntity
import com.alone.news.arumenu.model.NewsModel
import com.alone.news.arumenu.view.NewsView

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.jetbrains.annotations.NotNull

/**
 * package : com.example.zhanghegang.arumenu.presenter
 * anthor : 张贺岗
 * Date : 2019/2/15
 * Use : <类的用途>
 */

object Test{
    @JvmStatic
    fun saySomething() {

    }
}

class NewsPresenter(@NotNull val newsModel: NewsModel) : BasePresenter<NewsView>() {

    var subscriberSimple: Disposable? = null

    override fun DetachView() {
        super.DetachView()
        if (subscriberSimple != null && !subscriberSimple!!.isDisposed) {
            subscriberSimple!!.dispose()
        }
    }

    @SuppressLint("CheckResult")
    fun getToh(month: String, day: String) {
        val map = mapOf("v" to "1.0", "month" to month, "day" to day, "key" to AppDataKey.TohKey)
//        subscriberSimple = newsModel.postMulti()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(object : BaseConsumer<NewsEntity>() {
//                    @RequiresApi(Build.VERSION_CODES.N)
//                    override fun accept(t: NewsEntity) {
//                        super.accept(t)
//                        view.sucess(t)
//                    }
//                }, object : BaseConsumer<Throwable>() {
//                    @RequiresApi(Build.VERSION_CODES.N)
//                    override fun accept(t: Throwable) {
//                        super.accept(t)
//                        view.fail(t)
//                    }
//                })
        subscriberSimple = newsModel.getToh(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : BaseConsumer<NewsEntity>() {
                    @RequiresApi(Build.VERSION_CODES.N)
                    override fun accept(t: NewsEntity) {
                        super.accept(t)
                        view.sucess(t)
                    }
                }, object : BaseConsumer<Throwable>() {
                    @RequiresApi(Build.VERSION_CODES.N)
                    override fun accept(t: Throwable) {
                        super.accept(t)
                        view.fail(t)
                    }
                })


    }

}

