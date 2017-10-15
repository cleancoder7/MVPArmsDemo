/**
 * <P>Title:</p>
 * <P>Description:</p>
 * <p>Copyright:手趣云商科技有限公司   Copyright(c)2016</p>
 *
 * @author:Gfc
 * @date 下午3:19:30
 * @version 1.0
 */
package com.xiemiao.myapplication.common.mvp.model.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @version 1.0
 * @Title:行业对象
 * @Description:
 * @Copyright:手趣云商科技有限公司 Copyright(c)2016
 * @author:Gfc
 * @date 2017年7月12日
 */
public class Industry implements Serializable {
    /**
     * id
     */
    private int uid;

    /**
     * 名称
     */
    private String name;

    /**
     * 行业所属工种集合
     */
    private List<Jobtype> listJobtype;

    public Industry() {
    }

    public Industry(int uid, String name) {
        this.uid = uid;
        this.name = name;
    }

    public int getUid() {
        return this.uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Jobtype> getListJobtype() {
        return listJobtype;
    }

    public void setListJobtype(List<Jobtype> listJobtype) {
        this.listJobtype = listJobtype;
    }
}
