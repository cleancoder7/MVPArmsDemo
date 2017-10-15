package com.xiemiao.myapplication.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jess.arms.di.component.AppComponent;
import com.xiemiao.myapplication.R;

import butterknife.BindView;


/**
 * date: 2017年2月17日 下午2:31:09 <br/>
 * 测试默认的fragment
 *
 * @author xiemiao
 */
public class DefauleFragment extends BaseFragment {

    @BindView(R.id.tvTitle)
    TextView mTvTitle;

    @Override
    public void setupFragmentComponent(AppComponent appComponent) {

    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_default, container, false);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        mTvTitle.setText("默认");
    }

    @Override
    public void setData(Object data) {

    }
}
