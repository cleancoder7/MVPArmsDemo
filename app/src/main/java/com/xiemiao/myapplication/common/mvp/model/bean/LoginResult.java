package com.xiemiao.myapplication.common.mvp.model.bean;


import java.io.Serializable;

/**
 * Title:登录结果接收
 * Description:
 * Copyright:手趣云商科技有限公司 Copyright(c)2017
 * author:xiemiao
 * date: 2017-07-14
 * version 1.0
 */
public class LoginResult extends BaseResponse implements Serializable {
    /**
     * 企业用户对象
     */
    private Enterprise enterprise;

    public Enterprise getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(Enterprise enterprise) {
        this.enterprise = enterprise;
    }
}
