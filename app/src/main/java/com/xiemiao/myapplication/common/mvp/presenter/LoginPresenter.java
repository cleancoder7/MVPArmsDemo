package com.xiemiao.myapplication.common.mvp.presenter;

import android.app.Application;
import android.content.Intent;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import com.jess.arms.mvp.BasePresenter;
import com.xiemiao.myapplication.common.mvp.contract.LoginContract;
import com.xiemiao.myapplication.common.mvp.model.bean.LoginResult;
import com.xiemiao.myapplication.common.mvp.ui.activity.MainActivity;
import com.xiemiao.myapplication.net.CommonObserver;
import com.xiemiao.myapplication.net.ExceptionHandle;
import com.xiemiao.myapplication.net.RxUtils;
import com.xiemiao.myapplication.utils.UIUtils;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;


@ActivityScope
public class LoginPresenter extends BasePresenter<LoginContract.Model, LoginContract.View> {
    private RxErrorHandler mErrorHandler;
    private Application mApplication;
    private ImageLoader mImageLoader;
    private AppManager mAppManager;

    @Inject
    public LoginPresenter(LoginContract.Model model, LoginContract.View rootView
            , RxErrorHandler handler, Application application
            , ImageLoader imageLoader, AppManager appManager) {
        super(model, rootView);
        this.mErrorHandler = handler;
        this.mApplication = application;
        this.mImageLoader = imageLoader;
        this.mAppManager = appManager;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mAppManager = null;
        this.mImageLoader = null;
        this.mApplication = null;
    }

    /**
     * 登录
     *
     * @param username the username
     * @param password the password
     * @User: xiemiao
     * @Time: 2017 -10-23
     * @Date: 21 :33:28
     */
    public void login(String username, String password) {
        mModel.login(username, password)
                .compose(RxUtils.<LoginResult>schedulersTransformer())
                .compose(RxUtils.<LoginResult>httpTransformer())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mRootView.showLoading();
                    }
                })
                .subscribe(new CommonObserver<LoginResult>() {
                    @Override
                    public void onError(ExceptionHandle.ResponeThrowable e) {
                        mRootView.hideLoading();
                        mRootView.showMessage(e.message);
                    }

                    @Override
                    public void onNext(@NonNull LoginResult loginResult) {
                        mRootView.hideLoading();
                        mRootView.launchActivity(new Intent(UIUtils.getContext(), MainActivity.class));
                        //将登陆结果保存本地
                        UIUtils.putLoginResult(loginResult);
                        mRootView.killMyself();
                    }
                });
    }

}
