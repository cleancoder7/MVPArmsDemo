package com.xiemiao.myapplication.common.mvp.model.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Title:分页对象
 * Description:
 * Copyright:手趣云商科技有限公司 Copyright(c)2017
 * author:xiemiao
 * date: 2017-07-18
 * version 1.0
 */
public class PageModel implements Serializable {
    private int currentPage;
    private int pageSize;
    private int count;
    private int totalPage;
    private List list;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
}
