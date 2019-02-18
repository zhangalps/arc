package com.alone.news.arumenu.view;

import com.alone.news.arumenu.base.BaseView;
import com.alone.news.arumenu.entity.NewsEntity;

/**
 * package : com.example.zhanghegang.arumenu.view
 * anthor : 张贺岗
 * Date : 2019/2/15
 * Use : <类的用途>
 */
public interface NewsView extends BaseView {
    void sucess(NewsEntity data);
    void fail(Throwable error);
}
