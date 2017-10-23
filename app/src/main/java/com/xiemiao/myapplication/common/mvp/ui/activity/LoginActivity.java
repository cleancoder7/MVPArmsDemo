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
import com.xiemiao.myapplication.common.mvp.presenter.LoginPresenter;
import com.xiemiao.myapplication.utils.NumMatchUtil;
import com.xiemiao.myapplication.utils.StringUtils;
import com.xiemiao.myapplication.utils.UIUtils;

import butterknife.BindView;
import butterknife.OnClick;

import static com.jess.arms.utils.Preconditions.checkNotNull;


public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.View {


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
                checkContent();//检测输入框内容
                break;
            case R.id.tvRegister://注册新用户
                //                intent = new Intent(getApplicationContext(), RegisteNewUserActivity.class);
                //                startActivity(intent);
                break;
            case R.id.tvForgetPassWord://忘记密码
                //                intent = new Intent(getApplicationContext(), ForgotPasswordActivity.class);
                //                startActivity(intent);
                break;
        }
    }

    /**
     * 检测输入框内容有效性
     *
     * @User: xiemiao
     * @Time: 2017 -05-08
     * @Date: 13 :19:23
     */
    private void checkContent() {
        String phone = etPhone.getText().toString().trim();
        String password = etPassWord.getText().toString().trim();

        if (StringUtils.isEmpty(phone) || StringUtils.isEmpty(password)) {
            showMessage("手机号或密码不能为空");
            return;
        }
        boolean validPhoneNumber = NumMatchUtil.isValidPhoneNumber(phone);// 检测手机号有效性
        if (validPhoneNumber) {
            // 进行登录操作
            mPresenter.login(phone, UIUtils.md5(password));
        } else {
            showMessage("手机号格式有误");
        }
    }
}
