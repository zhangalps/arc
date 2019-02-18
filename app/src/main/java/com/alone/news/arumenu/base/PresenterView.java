package com.alone.news.arumenu.base;

import com.alone.news.arumenu.base.BaseView;

/**
 * package : com.example.zhanghegang.arumenu.base
 * anthor : 张贺岗
 * Date : 2019/2/15
 * Use : 视图层
 */
public interface PresenterView<T extends BaseView> {
    void attachView(T baseView);
    void DetachView();
}
