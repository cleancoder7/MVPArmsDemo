/**
 * <P>Title:</p>
 * <P>Description:</p>
 * <p>Copyright:手趣云商科技有限公司   Copyright(c)2016</p>
 *
 * @author:Gfc
 * @date 下午3:08:36
 * @version 1.0
 */
package com.xiemiao.myapplication.common.mvp.model.bean;


import java.io.Serializable;

/**
 * @version 1.0
 * @Title: 职业技能VO对象
 * @Description:
 * @Copyright:手趣云商科技有限公司 Copyright(c)2016
 * @author:Gfc
 * @date 2017年7月12日
 */
public class VocationalSkill implements Serializable {
    /**
     * id
     */
    private int uid;

    /**
     * 技能名称
     */
    private String name;

    /**
     * 技能是否被选择(企业端暂时没用到)0:否1:是
     */
    private int isSelected;

    public int getIsSelected() {
        return isSelected;
    }

    public void setIsSelected(int isSelected) {
        this.isSelected = isSelected;
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
}
