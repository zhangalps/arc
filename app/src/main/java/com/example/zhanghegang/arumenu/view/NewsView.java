package com.example.zhanghegang.arumenu.view;

import com.example.zhanghegang.arumenu.base.BaseView;
import com.example.zhanghegang.arumenu.entity.NewsEntity;

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
