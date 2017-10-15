package com.xiemiao.myapplication.common.mvp.model.bean;


import com.xiemiao.myapplication.utils.PinyinUtil;

import java.io.Serializable;

/**
 * Title:城市对象
 * Description:
 * Copyright:手趣云商科技有限公司 Copyright(c)2017
 * author:xiemiao
 * date: 2017-07-19
 * version 1.0
 */
public class City implements Serializable, Comparable<City> {
    /**
     * 城市Id
     */
    private String areaId;
    /**
     * 城市名
     */
    private String areaName;

    /**
     * 城市名的拼音字母
     */
    public String pinyin;

    @Override
    public int compareTo(City another) {
        //此处实现的比较方法，就直接用拼音进行比较
        return pinyin.compareTo(another.getPinyin());
    }

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
        // 因为汉字转拼音会消耗性能，所以就只在set方法掉一次,赋值给pinyin
        setPinyin(PinyinUtil.getPinyin(areaName));
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }
}
