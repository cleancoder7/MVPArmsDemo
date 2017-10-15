/**
 * <P>Title:</p>
 * <P>Description:</p>
 * <p>Copyright:手趣云商科技有限公司   Copyright(c)2016</p>
 *
 * @author:Gfc
 * @date 上午9:55:50
 * @version 1.0
 */
package com.xiemiao.myapplication.common.mvp.model.bean;

import java.io.Serializable;

/**
 * @version 1.0
 * @Title: 工种VO对象
 * @Description:
 * @Copyright:手趣云商科技有限公司 Copyright(c)2016
 * @author:Gfc
 * @date 2017年7月14日
 */
public class JobInfo implements Serializable {
    /**
     * id
     */
    private int uid;

    /**
     * 岗位类型ID
     */
    private int jobtypeId;

    /**
     * 岗位类型名称
     */
    private String jobtypeName;

    /**
     * 招聘人数
     */
    private int recruiters;

    /**
     * 是否面议薪资(0否1是)
     */
    private int isNegotiable;

    /**
     * 起始薪资
     */
    private int leastSalary;

    /**
     * 最高薪资
     */
    private int maxSalary;

    /**
     * 最小年龄
     */
    private int minage;

    /**
     * 最大年龄
     */
    private int maxage;

    /**
     * 性别限制  0:不限 、1:男、2:女
     */
    private int sexlimit;

    /**
     * 工作地点-市
     */
    private String city;

    /**
     * 工作地点-区
     */
    private String area;

    /**
     * 是否全职  (0:全职 1:兼职)
     */
    private int isFulltime;

    /**
     * 是否双休(0:"轮休", 1:"单休", 2:"双休")
     */
    private int isDoublecease;

    /**
     * 日工作时长
     */
    private int workhours;

    /**
     * 岗位待遇 （例如：包食宿,五险一金,年底双薪）数据字典配置岗位待遇种类
     */
    private String treatments;

    /**
     * 岗位简介（职责描述）
     */
    private String introduce;

    /**
     * 创建时间
     */
    private String createtime;

    /**
     * 岗位更新时间
     */
    private String updatetime;

    /**
     * 已录用人数
     */
    private int employNumbers;

    /**
     * 是否已拉黑(用户端使用)
     */
    private int isBlacklist;
    /**
     * /**
     * 是否已投递 【0：未投递， 1：已投递】(用户端使用)
     */
    private int isDelivery;

    public int getIsDelivery() {
        return isDelivery;
    }

    public void setIsDelivery(int isDelivery) {
        this.isDelivery = isDelivery;
    }

    public int getIsBlacklist() {
        return isBlacklist;
    }

    public void setIsBlacklist(int isBlacklist) {
        this.isBlacklist = isBlacklist;
    }

    public String getJobtypeName() {
        return jobtypeName;
    }

    public void setJobtypeName(String jobtypeName) {
        this.jobtypeName = jobtypeName;
    }

    public int getJobtypeId() {
        return jobtypeId;
    }

    public void setJobtypeId(int jobtypeId) {
        this.jobtypeId = jobtypeId;
    }

    public int getUid() {
        return this.uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getRecruiters() {
        return this.recruiters;
    }

    public void setRecruiters(int recruiters) {
        this.recruiters = recruiters;
    }

    /**
     * 是否面议薪资(0否1是)
     */
    public int getIsNegotiable() {
        return this.isNegotiable;
    }

    public void setIsNegotiable(int isNegotiable) {
        this.isNegotiable = isNegotiable;
    }

    public int getLeastSalary() {
        return this.leastSalary;
    }

    public void setLeastSalary(int leastSalary) {
        this.leastSalary = leastSalary;
    }

    public int getMaxSalary() {
        return this.maxSalary;
    }

    public void setMaxSalary(int maxSalary) {
        this.maxSalary = maxSalary;
    }

    public int getMinage() {
        return this.minage;
    }

    public void setMinage(int minage) {
        this.minage = minage;
    }

    public int getMaxage() {
        return this.maxage;
    }

    public void setMaxage(int maxage) {
        this.maxage = maxage;
    }

    /**
     * 性别限制  0:不限 、1:男、2:女
     */
    public int getSexlimit() {
        return this.sexlimit;
    }

    public void setSexlimit(int sexlimit) {
        this.sexlimit = sexlimit;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return this.area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    /**
     * 是否全职  (0:全职 1:兼职)
     */
    public int getIsFulltime() {
        return this.isFulltime;
    }

    public void setIsFulltime(int isFulltime) {
        this.isFulltime = isFulltime;
    }

    /**
     * 是否双休(0:"轮休", 1:"单休", 2:"双休")
     */
    public int getIsDoublecease() {
        return this.isDoublecease;
    }

    public void setIsDoublecease(int isDoublecease) {
        this.isDoublecease = isDoublecease;
    }

    public int getWorkhours() {
        return this.workhours;
    }

    public void setWorkhours(int workhours) {
        this.workhours = workhours;
    }

    public String getTreatments() {
        return this.treatments;
    }

    public void setTreatments(String treatments) {
        this.treatments = treatments;
    }

    public String getIntroduce() {
        return this.introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getCreatetime() {
        return this.createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getUpdatetime() {
        return this.updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    public int getEmployNumbers() {
        return this.employNumbers;
    }

    public void setEmployNumbers(int employNumbers) {
        this.employNumbers = employNumbers;
    }
}
