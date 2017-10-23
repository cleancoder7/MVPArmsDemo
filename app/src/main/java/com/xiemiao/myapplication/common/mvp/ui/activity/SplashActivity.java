package com.xiemiao.myapplication.common.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.jess.arms.di.component.AppComponent;
import com.xiemiao.guidepagelib.view.GuidePage;
import com.xiemiao.myapplication.R;
import com.xiemiao.myapplication.base.BaseActivity;
import com.xiemiao.myapplication.common.mvp.model.bean.LoginResult;
import com.xiemiao.myapplication.utils.Sputil;
import com.xiemiao.myapplication.utils.UIUtils;

import java.util.Arrays;

import butterknife.BindView;

/**
 * Title:闪屏页面
 * Description:
 * Copyright:手趣云商科技有限公司 Copyright(c)2017
 * author:xiemiao
 * date: 2017-07-14
 * version 1.0
 */
public class SplashActivity extends BaseActivity {
    @BindView(R.id.guidePage)
    GuidePage guidePage;
    private LoginResult mLoginResult;
    private Integer[] images = {R.drawable.new_guide_001, R.drawable.new_guide_002, R.drawable.new_guide_003};
    private Intent intent;

    @Override
    public void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    public int initView(Bundle savedInstanceState) {
        return R.layout.activity_splash;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        mLoginResult = UIUtils.getLoginResult();

        // 从sp获取是否是第一次进入
        boolean flag = Sputil.getBoolean(this, "ISFISRTENTRY", true);
        //         如果是第一次进入APP
        if (flag) {
            // 保存为不是第一次进入
            Sputil.putBoolean(this, "ISFISRTENTRY", false);
            initGuide();//初始化引导
        } else {
            // 不是第一次进入就直接进首页
            goHome();
        }
    }

    /**
     * 初始化引导图
     *
     * @User: xiemiao
     * @Time: 2017 -07-14
     * @Date: 10 :32:47
     */
    private void initGuide() {
        //设置图片集合,圆点颜色大小
        guidePage.setLocalImageResList(Arrays.asList(images)).setOvalIndicator(UIUtils.getColor(R.color.deepblue), UIUtils.getColor(R.color.half_gray), UIUtils.dp2px(3));

        //设置进入按钮（文字，颜色，大小，背景）点击事件
        guidePage.setOnEntryClickListener("      进入首页      ", UIUtils.getColor(R.color.white), 14, R.drawable
                .shape_radius_30dp_deepblue_bg, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //隐藏自己
                guidePage.setVisibility(View.GONE);
                goHome();
            }
        });
    }

    /**
     * 进入主页或登录界面
     *
     * @User: xiemiao
     * @Time: 2017 -07-14
     * @Date: 10 :34:57
     */
    private void goHome() {
        if (mLoginResult != null && !mLoginResult.getEnterprise().getSessionid().equals("")) {
            //跳转到主页
            intent = new Intent(getApplicationContext(), MainActivity.class);
        } else {
            //跳转到登录页
            intent = new Intent(getApplicationContext(), LoginActivity.class);
        }

        //延时1.5秒进入
        UIUtils.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(intent);
                finish();
            }
        }, 1500);

    }

    @Override
    public void initListener() {

    }

}
