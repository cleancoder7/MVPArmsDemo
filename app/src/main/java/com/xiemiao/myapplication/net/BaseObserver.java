package com.xiemiao.myapplication.net;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


/**
 * 简单封装Observer
 *
 * @param <T> the type parameter
 * @User: xiemiao
 * @Time: 2017 -10-17
 * @Date: 00 :58:08
 */
public abstract class BaseObserver<T> implements Observer<T> {

    @Override
    public void onError(Throwable e) {
        if (e instanceof ExceptionHandle.ResponeThrowable) {
            onError((ExceptionHandle.ResponeThrowable) e);
        } else {
            onError(new ExceptionHandle.ResponeThrowable(e, ExceptionHandle.ERROR.UNKNOWN));
        }
    }

    @Override
    public void onSubscribe(Disposable d) {
        //展示进度条
        showProgress();
    }

    @Override
    public void onComplete() {
        //隐藏进度条
        hintProgress();
    }

    protected abstract void hintProgress();

    protected abstract void showProgress();


    public abstract void onError(ExceptionHandle.ResponeThrowable e);

}
