package com.xiemiao.myapplication.common.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.xiemiao.myapplication.R;
import com.xiemiao.myapplication.base.BaseActivity;
import com.xiemiao.myapplication.common.di.component.DaggerLoginComponent;
import com.xiemiao.myapplication.common.di.module.LoginModule;
import com.xiemiao.myapplication.common.mvp.contract.LoginContract;
import com.xiemiao.myapplication.common.mvp.model.bean.eventbean.MessageTag;
import com.xiemiao.myapplication.common.mvp.presenter.LoginPresenter;

import butterknife.BindView;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;

import static android.net.http.Headers.REFRESH;
import static com.jess.arms.utils.Preconditions.checkNotNull;


public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.View {
    /**
     * 销毁界面的通知
     */
    public static final String FINISH = "LoginActivityFINISH";

    @BindView(R.id.etPhone)
    EditText etPhone;
    @BindView(R.id.etPassWord)
    EditText etPassWord;
    @BindView(R.id.ivShowPassWord)
    ImageView ivShowPassWord;
    @BindView(R.id.tvRegister)
    TextView tvRegister;
    @BindView(R.id.tvForgetPassWord)
    TextView tvForgetPassWord;
    @BindView(R.id.btnLogin)
    Button btnLogin;

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerLoginComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .loginModule(new LoginModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(Bundle savedInstanceState) {
        return R.layout.activity_login; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        hintTopLayout();//隐藏顶部标题栏
    }

    @Override
    public void initListener() {

    }


    @Override
    public void showLoading() {
        showProgress();
    }

    @Override
    public void hideLoading() {
        cancelProgress();
    }

    @Override
    public void showMessage(@NonNull String message) {
        checkNotNull(message);
        ArmsUtils.snackbarText(message);
    }

    @Override
    public void launchActivity(@NonNull Intent intent) {
        checkNotNull(intent);
        ArmsUtils.startActivity(intent);
    }

    @Override
    public void killMyself() {
        finish();
    }

    private boolean isShowPassWord;

    @OnClick({R.id.ivShowPassWord, R.id.tvRegister, R.id.tvForgetPassWord, R.id.btnLogin})
    public void onViewClicked(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.ivShowPassWord://是否展示密码
                if (isShowPassWord) {
                    //隐藏密码
                    ivShowPassWord.setImageResource(R.mipmap.icon_hint_password);
                    etPassWord.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    //展示密码
                    ivShowPassWord.setImageResource(R.mipmap.icon_show_password);
                    etPassWord.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                isShowPassWord = !isShowPassWord;
                break;
            case R.id.btnLogin://登录
                String phone = etPhone.getText().toString().trim();
                String password = etPassWord.getText().toString().trim();
                // 进行登录操作
                mPresenter.login(phone, password);
                break;
            case R.id.tvRegister://注册新用户
                intent = new Intent(getApplicationContext(), RegisteNewUserActivity.class);
                startActivity(intent);
                break;
            case R.id.tvForgetPassWord://忘记密码
                //                intent = new Intent(getApplicationContext(), ForgotPasswordActivity.class);
                //                startActivity(intent);
                break;
        }
    }

    /* ================接收EventBus传过来的消息,做出相应的操作================= */
    // 接受消息的地方(在Android的UI线程中)
    public void onEventMainThread(MessageTag event) {
        switch (event.tagStr) {
            case FINISH:
                finish();
                break;
        }
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
