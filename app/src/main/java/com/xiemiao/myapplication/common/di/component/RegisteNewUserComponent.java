package com.xiemiao.myapplication.common.di.component;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.xiemiao.myapplication.common.di.module.RegisteNewUserModule;

import com.xiemiao.myapplication.common.mvp.ui.activity.RegisteNewUserActivity;

@ActivityScope
@Component(modules = RegisteNewUserModule.class, dependencies = AppComponent.class)
public interface RegisteNewUserComponent {
    void inject(RegisteNewUserActivity activity);
}