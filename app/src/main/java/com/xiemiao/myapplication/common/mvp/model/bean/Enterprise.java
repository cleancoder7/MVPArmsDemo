/**
 * <P>Title:</p>
 * <P>Description:</p>
 * <p>Copyright:手趣云商科技有限公司   Copyright(c)2016</p>
 *
 * @author:Gfc
 * @date 上午9:52:58
 * @version 1.0
 */
package com.xiemiao.myapplication.common.mvp.model.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @version 1.0
 * @Title: 企业VO对象
 * @Description:
 * @Copyright:手趣云商科技有限公司 Copyright(c)2016
 * @author:Gfc
 * @date 2017年7月11日
 */
public class Enterprise implements Serializable {
    /**
     * id
     */
    private int uid;

    /**
     * 登录账号、手机号
     */
    private String username;

    /**
     * 企业照 logo
     */
    private String logo;

    /**
     * 企业名称
     */
    private String name;
    /**
     * 主营业务
     */
    private String mainBusiness;

    /**
     * 所属行业id
     */
    private int indusstryId;

    /**
     * 所属行业
     */
    private String industryName;

    /**
     * 行业所有工种
     */
    private List<Jobtype> listJobtype;

    /**
     * 企业所在地-市
     */
    private String city;

    /**
     * 企业所在地-区
     */
    private String area;

    /**
     * 详细地址
     */
    private String detailedAddress;

    /**
     * 经度
     */
    private double lon;

    /**
     * 纬度
     */
    private double lat;

    /**
     * 联系人
     */
    private String linkman;

    /**
     * 联系电话
     */
    private String contact;

    /**
     * 企业规模
     */
    private String enpscale;

    /**
     * 营业执照
     */
    private String license;

    /**
     * 主营业务
     */
    private String business;

    /**
     * 企业简介
     */
    private String introduce;

    /**
     * 认证状态  0:未认证、1:已认证、2:被驳回、3:审核中
     */
    private int authStatus;

    /**
     * 驳回理由
     */
    private String failReason;

    /**
     * 账号余额
     */
    private double accountBalance;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 唯一码
     */
    private String usercode;

    /**
     * app登录身份标识
     */
    private String sessionid;

    /**
     * 推送状态 【0:开启，1:关闭，2:免打扰】
     */
    private int pushstatus;

    /**
     * 关注状态 0:未关注 1:已关注
     */
    private int followstatus;

    /**
     * 关注状态 0:未关注 1:已关注
     */
    public int getFollowstatus() {
        return followstatus;
    }

    public void setFollowstatus(int followstatus) {
        this.followstatus = followstatus;
    }

    /**
     * 推送状态 【0:开启，1:关闭，2:免打扰】
     */
    public int getPushstatus() {
        return pushstatus;
    }

    public void setPushstatus(int pushstatus) {
        this.pushstatus = pushstatus;
    }

    public String getEnpscale() {
        return enpscale;
    }

    public void setEnpscale(String enpscale) {
        this.enpscale = enpscale;
    }

    public String getMainBusiness() {
        return mainBusiness;
    }

    public void setMainBusiness(String mainBusiness) {
        this.mainBusiness = mainBusiness;
    }

    public List<Jobtype> getListJobtype() {
        return listJobtype;
    }

    public void setListJobtype(List<Jobtype> listJobtype) {
        this.listJobtype = listJobtype;
    }

    public int getUid() {
        return this.uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getLogo() {
        return this.logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndusstryId() {
        return this.indusstryId;
    }

    public void setIndusstryId(int indusstryId) {
        this.indusstryId = indusstryId;
    }

    public String getIndustryName() {
        return this.industryName;
    }

    public void setIndustryName(String industryName) {
        this.industryName = industryName;
    }

    public String getDetailedAddress() {
        return this.detailedAddress;
    }

    public void setDetailedAddress(String detailedAddress) {
        this.detailedAddress = detailedAddress;
    }

    public double getLon() {
        return this.lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getLat() {
        return this.lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public String getLinkman() {
        return this.linkman;
    }

    public void setLinkman(String linkman) {
        this.linkman = linkman;
    }

    public String getContact() {
        return this.contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getLicense() {
        return this.license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getBusiness() {
        return this.business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    public String getIntroduce() {
        return this.introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    /**
     * 认证状态  0:未认证、1:已认证、2:被驳回、3:审核中
     */
    public int getAuthStatus() {
        return this.authStatus;
    }

    public void setAuthStatus(int authStatus) {
        this.authStatus = authStatus;
    }

    public String getFailReason() {
        return this.failReason;
    }

    public void setFailReason(String failReason) {
        this.failReason = failReason;
    }

    public double getAccountBalance() {
        return this.accountBalance;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public String getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUsercode() {
        return this.usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }

    public String getSessionid() {
        return this.sessionid;
    }

    public void setSessionid(String sessionid) {
        this.sessionid = sessionid;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
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
}
