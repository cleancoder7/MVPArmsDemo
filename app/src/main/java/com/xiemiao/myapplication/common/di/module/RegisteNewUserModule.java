package com.xiemiao.myapplication.common.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import com.xiemiao.myapplication.common.mvp.contract.RegisteNewUserContract;
import com.xiemiao.myapplication.common.mvp.model.RegisteNewUserModel;


@Module
public class RegisteNewUserModule {
    private RegisteNewUserContract.View view;

    /**
     * 构建RegisteNewUserModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public RegisteNewUserModule(RegisteNewUserContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    RegisteNewUserContract.View provideRegisteNewUserView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    RegisteNewUserContract.Model provideRegisteNewUserModel(RegisteNewUserModel model) {
        return model;
    }
}