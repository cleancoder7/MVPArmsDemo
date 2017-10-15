/**
 * <P>Title:</p>
 * <P>Description:</p>
 * <p>Copyright:手趣云商科技有限公司   Copyright(c)2016</p>
 *
 * @author:Gfc
 * @date 下午1:58:48
 * @version 1.0
 */
package com.xiemiao.myapplication.common.mvp.model.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @version 1.0
 * @Title: 工种VO对象
 * @Description:
 * @Copyright:手趣云商科技有限公司 Copyright(c)2016
 * @author:Gfc
 * @date 2017年7月11日
 */
public class Jobtype implements Serializable {
    /**
     * id
     */
    private int uid;

    /**
     * 工种名称
     */
    private String name;

    /**
     * 工种所属职业技能集合
     */
    private List<VocationalSkill> listSkill;

    /**
     * 工种是否被选择(企业端暂时没用到)0:否1:是
     */
    private int isSelected;

    public int getIsSelected() {
        return isSelected;
    }

    public void setIsSelected(int isSelected) {
        this.isSelected = isSelected;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUid() {
        return this.uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public List<VocationalSkill> getListSkill() {
        return listSkill;
    }

    public void setListSkill(List<VocationalSkill> listSkill) {
        this.listSkill = listSkill;
    }
}
