package com.xiemiao.myapplication.common.mvp.model.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Title:省对象
 * Description:
 * Copyright:手趣云商科技有限公司 Copyright(c)2017
 * author:xiemiao
 * date: 2017-07-19
 * version 1.0
 */
public class Province implements Serializable {
    private String areaId;
    private String areaName;
    private List<City> cities;

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }
}
