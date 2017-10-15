/**
 * <P>Title:</p>
 * <P>Description:</p>
 * <p>Copyright:手趣云商科技有限公司   Copyright(c)2016</p>
 *
 * @author:xiemiao
 * @date 下午1:52:08
 * @version 1.0
 */
package com.xiemiao.myapplication.common.mvp.model.bean;


import java.io.Serializable;

/**
 * @version 1.0
 * @Title:订单确认对象
 * @Description:
 * @Copyright:手趣云商科技有限公司 Copyright(c)2017
 * @author:xiemiao
 * @date 2017年4月13日
 */
public class OrderConfirmBean implements Serializable {
    /**
     * 订单类型【1:购买简历2:充值】
     */
    public static final int[] ORDERTYPE = {1, 2};

    /**
     * 是否是待支付状态(0:正常支付状态 1:待支付状态)
     */
    public int isWaitPay;

    /**
     * 待支付订单有订单码
     */
    public String ordersn;

    /**
     * 简历Id集合,逗号隔开(eg. 1,2,3,4)
     */
    public String resumeIds;

    /**
     * 岗位投递id(主要用来维护简历的8种状态的专用id)
     */
    public String rdids;

    /**
     * 岗位对象Id
     */
    public int jobinfoId;

    /**
     * 简历单价
     */
    public double unitPrice;
    /**
     * 简历数量
     */
    public int resumeCount;
    /**
     * 账户余额
     */
    public double balance;

    /**
     * 充值金额
     */
    public double rechargeMoney;

    /**
     * 订单类型【1:购买简历2:充值】
     */
    public int orderType;
    /**
     * 类型描述
     */
    public String typeDes;
}
