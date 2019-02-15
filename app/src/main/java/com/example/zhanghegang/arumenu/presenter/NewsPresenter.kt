package com.example.zhanghegang.arumenu.presenter

import android.annotation.SuppressLint
import com.example.zhanghegang.arumenu.api.AppDataKey
import com.example.zhanghegang.arumenu.base.BasePresenter
import com.example.zhanghegang.arumenu.model.NewsModel
import com.example.zhanghegang.arumenu.view.NewsView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.reactivestreams.Subscriber

/**
 * package : com.example.zhanghegang.arumenu.presenter
 * anthor : 张贺岗
 * Date : 2019/2/15
 * Use : <类的用途>
 */
class NewsPresenter(val newsModel: NewsModel) : BasePresenter<NewsView>() {

    var subscriberSimple: Disposable? = null

    override fun DetachView() {
        super.DetachView()
        if (subscriberSimple != null && !subscriberSimple!!.isDisposed){
            subscriberSimple!!.dispose()
        }
    }

    @SuppressLint("CheckResult")
    fun getToh(month: String, day: String) {
        val map = mapOf("v" to "1.0", "month" to month, "day" to day, "key" to AppDataKey.TohKey)
        subscriberSimple = newsModel.getToh(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    view.sucess(it)
                }, {

                    view.fail(it)
                })

    }

}

