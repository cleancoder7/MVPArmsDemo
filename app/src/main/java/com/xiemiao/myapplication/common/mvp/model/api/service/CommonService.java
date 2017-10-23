package com.xiemiao.myapplication.common.mvp.model.api.service;

import com.xiemiao.myapplication.app.Constants;
import com.xiemiao.myapplication.common.mvp.model.bean.ConfigInfoResult;
import com.xiemiao.myapplication.common.mvp.model.bean.LoginResult;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Title:存放一些常规的API <br />
 * Description: <br />
 * author:xiemiao <br />
 * date: 2017-10-15  <br />
 * version 1.0 <br />
 */
public interface CommonService {
    /**
     * 请求获取全局配置信息
     */
    @POST(Constants.API.GET_CONFIG_INFO)
    Observable<ConfigInfoResult> getConfigInfo();

    @FormUrlEncoded
    @POST(Constants.API.LOGIN)
    Observable<LoginResult> login(@Field("userName") String userName, @Field("passWord") String passWord);
}
