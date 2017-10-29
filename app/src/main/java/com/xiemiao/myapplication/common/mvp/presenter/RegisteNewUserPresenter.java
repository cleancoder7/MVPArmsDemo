package com.xiemiao.myapplication.common.mvp.presenter;

import android.app.Application;
import android.content.Intent;
import android.os.CountDownTimer;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import com.jess.arms.mvp.BasePresenter;
import com.xiemiao.myapplication.R;
import com.xiemiao.myapplication.common.mvp.contract.RegisteNewUserContract;
import com.xiemiao.myapplication.common.mvp.model.bean.LoginResult;
import com.xiemiao.myapplication.common.mvp.model.bean.eventbean.MessageTag;
import com.xiemiao.myapplication.common.mvp.ui.activity.LoginActivity;
import com.xiemiao.myapplication.common.mvp.ui.activity.MainActivity;
import com.xiemiao.myapplication.net.BaseResponse;
import com.xiemiao.myapplication.net.CommonObserver;
import com.xiemiao.myapplication.net.ExceptionHandle;
import com.xiemiao.myapplication.utils.NumMatchUtil;
import com.xiemiao.myapplication.utils.StringUtils;
import com.xiemiao.myapplication.utils.UIUtils;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

import static com.xiemiao.myapplication.utils.ToastUtil.showToast;


@ActivityScope
public class RegisteNewUserPresenter extends BasePresenter<RegisteNewUserContract.Model, RegisteNewUserContract.View> {
    private RxErrorHandler mErrorHandler;
    private Application mApplication;
    private ImageLoader mImageLoader;
    private AppManager mAppManager;

    private String passPhone = "";//记录手机号
    private String passSmsCode = "";//记录验证码

    @Inject
    public RegisteNewUserPresenter(RegisteNewUserContract.Model model, RegisteNewUserContract.View rootView
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
        if (timer != null) {
            timer.cancel();
        }
    }

    /**
     * 获取验证码
     *
     * @param username the username
     * @User: xiemiao
     * @Time: 2017 -10-25
     * @Date: 00 :13:18
     * @Desc:
     */
    public void getSmsCode(String username) {
        // 检查手机号的有效性
        if (!NumMatchUtil.isValidPhoneNumber(username)) {
            mRootView.showMessage("手机号格式错误");
            return;
        }

        mModel.getSmsCode(username)
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mRootView.showLoading();
                    }
                })
                .subscribe(new CommonObserver<BaseResponse>() {
                    @Override
                    public void onError(ExceptionHandle.ResponeThrowable e) {
                        mRootView.hideLoading();
                        mRootView.showMessage(e.message);
                    }

                    @Override
                    public void onNext(@NonNull BaseResponse baseResponse) {
                        mRootView.hideLoading();
                        // 点击了获取验证码后,开启一个计时器(总时长60秒,计时间隔为1秒)
                        timer = new TimeCount(60000, 1000);
                        timer.start();
                        mRootView.showMessage(UIUtils.getString(R.string.sms_code_send_success));
                    }
                });
    }

    /**
     * 检测验证码
     *
     * @param username the username
     * @User: xiemiao
     * @Time: 2017 -10-25
     * @Date: 00 :13:18
     * @Desc:
     */
    public void checkSmsCode(final String username, final String smscode) {
        // 检查手机号的有效性
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(smscode)) {
            mRootView.showMessage("手机号或验证码不能为空");
            return;
        }

        // 检查手机号的有效性
        if (!NumMatchUtil.isValidPhoneNumber(username)) {
            mRootView.showMessage("手机号格式错误");
            return;
        }

        mModel.checkSmsCode(username, smscode)
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mRootView.showLoading();
                    }
                })
                .subscribe(new CommonObserver<BaseResponse>() {
                    @Override
                    public void onError(ExceptionHandle.ResponeThrowable e) {
                        mRootView.hideLoading();
                        mRootView.showMessage(e.message);
                    }

                    @Override
                    public void onNext(@NonNull BaseResponse baseResponse) {
                        mRootView.hideLoading();
                        mRootView.showStepTwo();//展示第二步
                        //记录此时的手机号和验证码
                        //记录输入的手机号和验证码
                        passPhone = username;
                        passSmsCode = smscode;
                    }
                });
    }

    /**
     * 注册登录
     *
     * @User: xiemiao
     * @Time: 2017 -10-25
     * @Date: 00 :13:18
     * @Desc:
     */
    public void registe(String setPassWord, String confirmPassWord) {
        if (setPassWord.length() < 6 || confirmPassWord.length() < 6) {
            showToast("密码最少设置6位");
            return;
        }

        if (!StringUtils.isEquals(setPassWord, confirmPassWord)) {
            showToast("两次输入的密码不一致");
            return;
        }

        mModel.registe(passPhone, passSmsCode, UIUtils.md5(confirmPassWord))
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
                        mRootView.showMessage("登录成功");
                        //发送消息通知登录界面销毁
                        EventBus.getDefault().post(new MessageTag(LoginActivity.FINISH));
                        UIUtils.putLoginResult(loginResult);//存登录结果
                        //跳转到主界面
                        Intent intent = new Intent(UIUtils.getContext(), MainActivity.class);
                        intent.putExtra("isRegiste", true);//告知是从注册页面跳转过去的
                        mRootView.launchActivity(intent);
                        mRootView.killMyself();
                    }
                });
    }

    /**
     * 计时器
     */
    private TimeCount timer;

    /**
     * 计时器
     *
     * @User: xiemiao
     * @Time: 2017 -07-14
     * @Date: 15 :22:30
     */
    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);// 参数依次为总时长,和计时的时间间隔
        }

        @Override
        public void onFinish() {// 计时完毕时触发
            mRootView.showCountFinish();
        }

        @Override
        public void onTick(long millisUntilFinished) {// 计时过程显示
            mRootView.showCountDown((int) (millisUntilFinished / 1000));
        }
    }

}
