package com.xiemiao.myapplication.net.function;

import com.xiemiao.myapplication.net.BaseResponse;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * Title:过滤返回结果的function <br />
 * Description: <br />
 * author:xiemiao <br />
 * date: 2017-10-16  <br />
 * version 1.0 <br />
 */
public class HttpResponseFunction<T> implements Function<T, T> {

    @Override
    public T apply(@NonNull T t) throws Exception {
        if (t instanceof BaseResponse) {
            BaseResponse baseResponse = (BaseResponse) t;
            if (baseResponse.isOK()) {//请求成功
                return t;
            } else {//请求失败
                throw new IllegalStateException(baseResponse.getError());
            }
        } else {
            throw new IllegalStateException("JaveBean请继承BaseResponse");
        }
    }
}
