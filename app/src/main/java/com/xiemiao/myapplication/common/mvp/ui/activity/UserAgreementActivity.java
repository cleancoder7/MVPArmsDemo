package com.xiemiao.myapplication.common.mvp.ui.activity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.jess.arms.di.component.AppComponent;
import com.xiemiao.myapplication.R;
import com.xiemiao.myapplication.base.BaseActivity;
import com.xiemiao.myapplication.common.mvp.model.bean.ConfigInfoResult;
import com.xiemiao.myapplication.common.mvp.model.bean.EnterpriseConfig;
import com.xiemiao.myapplication.utils.UIUtils;

import butterknife.BindView;

/**
 * User: xiemiao
 * Date: 2017-05-17
 * Time: 14:01
 * Desc: 用户协议页面
 */
public class UserAgreementActivity extends BaseActivity {
    @BindView(R.id.webView)
    WebView webView;

    private ConfigInfoResult mConfigInfoResult;
    private EnterpriseConfig mEnterpriseConfig;//企业全局配置


    @Override
    public void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    public int initView(Bundle savedInstanceState) {
        return R.layout.activity_about_we;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        setLeftButton();
        setTitleText(R.string.user_agreement);
        mConfigInfoResult = UIUtils.getConfigInfoResult();
        if (mConfigInfoResult != null) {
            mEnterpriseConfig = mConfigInfoResult.getEnterpriseConfig();
        }

        if (mEnterpriseConfig != null) {
            String body = mEnterpriseConfig.getAgreement();
            webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
            webView.getSettings().setJavaScriptEnabled(true);
            webView.getSettings().setTextSize(WebSettings.TextSize.SMALLER);
            webView.getSettings().setPluginState(WebSettings.PluginState.ON);
            // webView.getSettings().set// 可以使用插件
            webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
            if (null != body && !"".equals(body)) {
                body = body.replace("<p>　　", "<p style=\"text-indent: 2em\">");
                body = body.replaceAll("<p>", "<p style=\"text-indent: 2em\">");
            }
            webView.loadDataWithBaseURL(null, body, "text/html", "UTF-8", null);
        }
    }

    @Override
    public void initListener() {

    }
}
