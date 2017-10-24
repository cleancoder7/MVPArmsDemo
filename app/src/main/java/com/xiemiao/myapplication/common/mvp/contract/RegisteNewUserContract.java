package com.xiemiao.myapplication.common.mvp.contract;

import com.jess.arms.mvp.IModel;
import com.jess.arms.mvp.IView;
import com.xiemiao.myapplication.common.mvp.model.bean.LoginResult;
import com.xiemiao.myapplication.net.BaseResponse;

import io.reactivex.Observable;


public interface RegisteNewUserContract {
    //对于经常使用的关于UI的方法可以定义到IView中,如显示隐藏进度条,和显示文字消息
    interface View extends IView {
        void showStepOne();//展示步骤1

        void showStepTwo();//展示步骤2

        void showCountDown(int time);//倒计时

        void showCountFinish();//倒计时结束
    }

    //Model层定义接口,外部只需关心Model返回的数据,无需关心内部细节,即是否使用缓存
    interface Model extends IModel {
        Observable<BaseResponse> getSmsCode(String userName);

        Observable<BaseResponse> checkSmsCode(String userName, String smsCode);

        Observable<LoginResult> registe(String userName, String smsCode, String passWord);
    }
}
