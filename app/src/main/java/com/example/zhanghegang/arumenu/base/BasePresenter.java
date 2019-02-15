package com.example.zhanghegang.arumenu.base;

/**
 * package : com.example.zhanghegang.arumenu.base
 * anthor : 张贺岗
 * Date : 2019/2/15
 * Use : presenter基类
 */
public class BasePresenter<T extends BaseView> implements PresenterView<T>{


    private T view;

    @Override
    public void attachView(T baseView) {
        this.view = baseView;
    }

    @Override
    public void DetachView() {
        view = null;

    }
    public T getView(){
        return view;
    }
    public boolean isAttachView(){
        return view != null;
    }
    public void checkoutAttachView(){
        if (!isAttachView()){
            throw new MvpException();
        }
    }
    public static class MvpException extends RuntimeException{
        public MvpException() {
            super("------------>please invoke attchView<--------------");
        }
    }
}
