package com.xiemiao.myapplication.net;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


/**
 * 一般封装Observer
 *
 * @param <T> the type parameter
 * @User: xiemiao
 * @Time: 2017 -10-17
 * @Date: 00 :58:08
 */
public abstract class CommonObserver<T> implements Observer<T> {

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
    }

    @Override
    public void onComplete() {
    }

    public abstract void onError(ExceptionHandle.ResponeThrowable e);

}
