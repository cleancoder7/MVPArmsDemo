package com.xiemiao.myapplication.common.mvp.model;

import android.app.Application;

import com.google.gson.Gson;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;
import com.xiemiao.myapplication.common.mvp.contract.LoginContract;
import com.xiemiao.myapplication.common.mvp.model.api.service.CommonService;
import com.xiemiao.myapplication.common.mvp.model.bean.LoginResult;

import javax.inject.Inject;

import io.reactivex.Observable;


@ActivityScope
public class LoginModel extends BaseModel implements LoginContract.Model {
    private Gson mGson;
    private Application mApplication;

    @Inject
    public LoginModel(IRepositoryManager repositoryManager, Gson gson, Application application) {
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
    public Observable<LoginResult> login(String username, String password) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).login(username, password);
    }
}