/**
 * <P>Title:</p>
 * <P>Description:</p>
 * <p>Copyright:手趣云商科技有限公司   Copyright(c)2016</p>
 *
 * @author:Gfc
 * @date 下午1:57:32
 * @version 1.0
 */
package com.xiemiao.myapplication.common.mvp.model.bean;


import java.io.Serializable;

/**
 * @version 1.0
 * @Title: 企业端全局配置VO对象
 * @Description:
 * @Copyright:手趣云商科技有限公司 Copyright(c)2016
 * @author:Gfc
 * @date 2017年7月13日
 */
public class EnterpriseConfig implements Serializable {
    /**
     * 关于我们
     */
    private String aboutus;

    /**
     * 用户协议
     */
    private String agreement;

    /**
     * 帮助说明
     */
    private String help;
    /**
     * 客服电话
     */
    private String phone;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAboutus() {
        return this.aboutus;
    }

    public void setAboutus(String aboutus) {
        this.aboutus = aboutus;
    }

    public String getAgreement() {
        return this.agreement;
    }

    public void setAgreement(String agreement) {
        this.agreement = agreement;
    }

    public String getHelp() {
        return this.help;
    }

    public void setHelp(String help) {
        this.help = help;
    }
}
