package com.xiemiao.myapplication.common.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.xiemiao.myapplication.R;
import com.xiemiao.myapplication.base.BaseActivity;
import com.xiemiao.myapplication.common.di.component.DaggerRegisteNewUserComponent;
import com.xiemiao.myapplication.common.di.module.RegisteNewUserModule;
import com.xiemiao.myapplication.common.mvp.contract.RegisteNewUserContract;
import com.xiemiao.myapplication.common.mvp.presenter.RegisteNewUserPresenter;
import com.xiemiao.myapplication.utils.UIUtils;

import butterknife.BindView;
import butterknife.OnClick;

import static com.jess.arms.utils.Preconditions.checkNotNull;


public class RegisteNewUserActivity extends BaseActivity<RegisteNewUserPresenter> implements RegisteNewUserContract.View {


    @BindView(R.id.tvStepOne)
    TextView tvStepOne;
    @BindView(R.id.tvStepTwo)
    TextView tvStepTwo;
    @BindView(R.id.etPhone)
    EditText etPhone;
    @BindView(R.id.ivClearPhone)
    ImageView ivClearPhone;
    @BindView(R.id.etSmsCode)
    EditText etSmsCode;
    @BindView(R.id.tvGetSmsCode)
    TextView tvGetSmsCode;
    @BindView(R.id.llSetupOneContainer)
    LinearLayout llSetupOneContainer;
    @BindView(R.id.etSetPassWord)
    EditText etSetPassWord;
    @BindView(R.id.ivShowSetPassWord)
    ImageView ivShowSetPassWord;
    @BindView(R.id.etConfirmPassWord)
    EditText etConfirmPassWord;
    @BindView(R.id.ivShowConfirmPassWord)
    ImageView ivShowConfirmPassWord;
    @BindView(R.id.llSetupTwoContainer)
    LinearLayout llSetupTwoContainer;
    @BindView(R.id.cbUserRule)
    CheckBox cbUserRule;
    @BindView(R.id.tvUserRule)
    TextView tvUserRule;
    @BindView(R.id.btnNextSetup)
    Button btnNextSetup;
    @BindView(R.id.btnConfirmLogin)
    Button btnConfirmLogin;

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerRegisteNewUserComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .registeNewUserModule(new RegisteNewUserModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(Bundle savedInstanceState) {
        return R.layout.activity_registe_new_user; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        setLeftButton();
        setTitleText(R.string.registe_new_user);
        //编辑框清除监听
        UIUtils.clearEditTextListener(etPhone, ivClearPhone);

        //设置密码是星号格式(解决输入框字体问题)
        etSetPassWord.setTransformationMethod(PasswordTransformationMethod.getInstance());
        etConfirmPassWord.setTransformationMethod(PasswordTransformationMethod.getInstance());
        //默认展示步骤一
        showStepOne();
    }


    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

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

    private boolean isShowSetPassWord;//是否显示设置密码
    private boolean isShowConfirmPassWord;//是否显示确认密码

    String phone;

    @OnClick({R.id.ivClearPhone, R.id.tvGetSmsCode, R.id.ivShowSetPassWord, R.id.ivShowConfirmPassWord, R.id.tvUserRule, R.id.btnNextSetup, R.id.btnConfirmLogin})
    public void onViewClicked(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.tvGetSmsCode://获取验证码
                phone = etPhone.getText().toString().trim();
                mPresenter.getSmsCode(phone);
                break;
            case R.id.ivShowSetPassWord://点击隐藏或展示 设置密码
                if (isShowSetPassWord) {
                    //隐藏密码
                    ivShowSetPassWord.setImageResource(R.mipmap.icon_hint_password_gary);
                    etSetPassWord.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    //展示密码
                    ivShowSetPassWord.setImageResource(R.mipmap.icon_show_password_gary);
                    etSetPassWord.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                isShowSetPassWord = !isShowSetPassWord;
                break;
            case R.id.ivShowConfirmPassWord://点击隐藏或展示 确认密码
                if (isShowConfirmPassWord) {
                    //隐藏密码
                    ivShowConfirmPassWord.setImageResource(R.mipmap.icon_hint_password_gary);
                    etConfirmPassWord.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    //展示密码
                    ivShowConfirmPassWord.setImageResource(R.mipmap.icon_show_password_gary);
                    etConfirmPassWord.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                isShowConfirmPassWord = !isShowConfirmPassWord;
                break;
            case R.id.tvUserRule://用户协议
                intent = new Intent(UIUtils.getContext(), UserAgreementActivity.class);
                startActivity(intent);
                break;
            case R.id.btnNextSetup://下一步
                if (!cbUserRule.isChecked()) {
                    showMessage("未勾选确认用户协议");
                    return;
                }
                phone = etPhone.getText().toString().trim();
                String smscode = etSmsCode.getText().toString().trim();
                mPresenter.checkSmsCode(phone, smscode);
                break;
            case R.id.btnConfirmLogin://确认登录
                if (!cbUserRule.isChecked()) {
                    showToast("未勾选确认用户协议");
                    return;
                }
                String setPassWord = etSetPassWord.getText().toString().trim();
                String confirmPassWord = etConfirmPassWord.getText().toString().trim();
                mPresenter.registe(setPassWord, confirmPassWord);
                break;
        }
    }

    @Override
    public void showStepOne() {
        tvStepOne.setSelected(false);
        tvStepTwo.setSelected(false);

        llSetupOneContainer.setVisibility(View.VISIBLE);
        llSetupTwoContainer.setVisibility(View.GONE);
        btnNextSetup.setVisibility(View.VISIBLE);
        btnConfirmLogin.setVisibility(View.GONE);
    }

    @Override
    public void showStepTwo() {
        tvStepOne.setSelected(true);
        tvStepTwo.setSelected(true);

        llSetupOneContainer.setVisibility(View.GONE);
        llSetupTwoContainer.setVisibility(View.VISIBLE);
        btnNextSetup.setVisibility(View.GONE);
        btnConfirmLogin.setVisibility(View.VISIBLE);
    }

    @Override
    public void showCountDown(int time) {
        tvGetSmsCode.setClickable(false);// 设置不能被点击
        tvGetSmsCode.setTextColor(UIUtils.getColor(R.color.gray_999999));
        tvGetSmsCode.setBackgroundResource(R.drawable.shape_text_bound_radius_5dp_gray);
        tvGetSmsCode.setText(getString(R.string.smscode_second, String.valueOf(time)));
    }

    @Override
    public void showCountFinish() {
        tvGetSmsCode.setTextColor(UIUtils.getColor(R.color.deepblue));
        tvGetSmsCode.setBackgroundResource(R.drawable.shape_text_bound_radius_5dp_deepblue);
        tvGetSmsCode.setText(getString(R.string.get_sms_code));
        tvGetSmsCode.setClickable(true);// 计时完成,可被点击
    }

    @Override
    public void initListener() {

    }
}
