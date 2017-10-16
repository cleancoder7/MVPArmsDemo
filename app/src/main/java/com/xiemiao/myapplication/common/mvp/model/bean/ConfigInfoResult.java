package com.xiemiao.myapplication.common.mvp.model.bean;

import com.xiemiao.myapplication.net.BaseResponse;

import java.io.Serializable;
import java.util.List;

/**
 * Title:全局配置信息结果接收
 * Description:
 * Copyright:手趣云商科技有限公司 Copyright(c)2017
 * author:xiemiao
 * date: 2017-07-14
 * version 1.0
 */
public class ConfigInfoResult extends BaseResponse implements Serializable {
    /**
     * 行业对象集合
     */
    private List<Industry> listIndustry;

    /**
     * 全局配置信息
     */
    private EnterpriseConfig enterpriseConfig;

    /**
     * 福利待遇集合
     */
    private List<String> listTreatments;

    public List<String> getListTreatments() {
        return listTreatments;
    }

    public void setListTreatments(List<String> listTreatments) {
        this.listTreatments = listTreatments;
    }

    public EnterpriseConfig getEnterpriseConfig() {
        return enterpriseConfig;
    }

    public void setEnterpriseConfig(EnterpriseConfig enterpriseConfig) {
        this.enterpriseConfig = enterpriseConfig;
    }

    public List<Industry> getListIndustry() {
        return listIndustry;
    }

    public void setListIndustry(List<Industry> listIndustry) {
        this.listIndustry = listIndustry;
    }
}
