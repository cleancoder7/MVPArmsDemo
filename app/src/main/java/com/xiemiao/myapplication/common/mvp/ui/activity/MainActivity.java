package com.xiemiao.myapplication.common.mvp.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.jess.arms.di.component.AppComponent;
import com.xiemiao.myapplication.R;
import com.xiemiao.myapplication.base.BaseActivity;
import com.xiemiao.myapplication.base.BaseFragment;
import com.xiemiao.myapplication.base.DefauleFragment;
import com.xiemiao.myapplication.common.mvp.model.bean.TabEntity;

import java.util.ArrayList;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    @BindView(R.id.commonTabLayout)
    CommonTabLayout commonTabLayout;

    //底部导航标题
    private String[] mTitles = {"首页", "招聘管理", "企业圈", "个人中心"};

    //tab页面fragments集合
    private ArrayList<Fragment> mFragments = new ArrayList<>();

    //未选中tab图标
    private int[] mIconUnselectIds = {
            R.drawable.tab_home_unselect, R.drawable.tab_recruit_manage_unselect,
            R.drawable.tab_enterprise_circle_unselect, R.drawable.tab_person_center_unselect};

    //已选中tab图标
    private int[] mIconSelectIds = {
            R.drawable.tab_home_select, R.drawable.tab_recruit_manage_select,
            R.drawable.tab_enterprise_circle_select, R.drawable.tab_person_center_select};

    //tab实体集合
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();

    @Override
    public void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    public int initView(Bundle savedInstanceState) {
        return R.layout.activity_main;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        hintTopLayout();// 隐藏顶部标题布局
        //1初始化fragment
        mFragments.add(new DefauleFragment());
        mFragments.add(new DefauleFragment());
        mFragments.add(new DefauleFragment());
        mFragments.add(new DefauleFragment());
        //2创建tab实体
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }

        //3设置tab数据
        commonTabLayout.setTabData(mTabEntities, this, R.id.flContainer, mFragments);
    }

    @Override
    public void initListener() {
        //设置tab被选择时监听
        commonTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                BaseFragment fragment = (BaseFragment) mFragments.get(position);
                fragment.loadData();
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }
}
