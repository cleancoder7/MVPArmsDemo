package com.xiemiao.myapplication.common.mvp.model;

import android.app.Application;

import com.google.gson.Gson;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;
import com.xiemiao.myapplication.common.mvp.contract.RegisteNewUserContract;
import com.xiemiao.myapplication.common.mvp.model.api.service.CommonService;
import com.xiemiao.myapplication.common.mvp.model.bean.LoginResult;
import com.xiemiao.myapplication.net.BaseResponse;
import com.xiemiao.myapplication.net.RxUtils;

import javax.inject.Inject;

import io.reactivex.Observable;


@ActivityScope
public class RegisteNewUserModel extends BaseModel implements RegisteNewUserContract.Model {
    private Gson mGson;
    private Application mApplication;

    @Inject
    public RegisteNewUserModel(IRepositoryManager repositoryManager, Gson gson, Application application) {
        super(repositoryManager);
        this.mGson = gson;
        this.mApplication = application;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }

    @Override
    public Observable<BaseResponse> getSmsCode(String userName) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class)
                .getSmsCode(userName)
                .compose(RxUtils.<BaseResponse>schedulersTransformer())
                .compose(RxUtils.<BaseResponse>httpTransformer());
    }

    @Override
    public Observable<BaseResponse> checkSmsCode(String userName, String smsCode) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class)
                .checkSmsCode(userName, smsCode)
                .compose(RxUtils.<BaseResponse>schedulersTransformer())
                .compose(RxUtils.<BaseResponse>httpTransformer());
    }

    @Override
    public Observable<LoginResult> registe(String userName, String smsCode, String passWord) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class)
                .registe(userName, smsCode, passWord)
                .compose(RxUtils.<LoginResult>schedulersTransformer())
                .compose(RxUtils.<LoginResult>httpTransformer());
    }
}