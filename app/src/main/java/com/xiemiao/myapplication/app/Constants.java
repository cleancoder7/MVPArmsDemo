package com.xiemiao.myapplication.app;

import com.xiemiao.myapplication.utils.UIUtils;

/**
 * User: xiemiao Date: 2016-10-04 Time: 21:37 Desc: 常量工具类
 */
public class Constants {

    /**
     * 是否DeBug模式
     */
    public static final boolean DEBUG = true;
    /**
     * logo图片保存路径
     */
    public static final String LOGO_PIC_PATH = UIUtils.getCachePath() + "/logo.jpg";

    /**
     * 保存登录信息的key
     */
    public static final String LOGIN_INFO_KEY = "logininfo";
    /**
     * 保存用户信息的key
     */
    public static final String USER_INFO_KEY = "userinfo";
    /**
     * 保存全局配置信息的key
     */
    public static final String CONFIG_KEY = "configkey";
    /**
     * 消息通知开关key
     */
    public static final String PUSH_MESSAGE_KEY = "push_message_key";

    /**
     * 默认第一页
     */
    public static final int DEFAULT_CURRENTPAGE = 1;

    /**
     * 默认每页记录数
     */
    public static final int DEFAULT_PAGECOUNT = 8;

    /**
     * 微信appid
     */
    public static final String WX_APP_ID = "wx1cdc8c603247a3b6";

       /*-------------------pushmsg 消息个数显示相关-----start----------------*/
    /**
     * 系统消息
     */
    public static final String MSGTYPE_SYSTEM_KEY = "msgtype_system_key";
    /**
     * 简历投递
     */
    public static final String MSGTYPE_RESUME_DELIVERY = "msgtype_resume_delivery";
    /*-------------------pushmsg 消息个数显示相关-----end----------------*/


    public static String ERROR_TITLE = "网络连接异常";
    public static String ERROR_CONTEXT = "请检查网络后重试";
    public static String ERROR_BUTTON = "重试";
    public static String EMPTY_TITLE = "没有找到数据";
    public static String EMPTY_CONTEXT = "换个姿势试试吧";

    /**
     * 请求链接相关
     */
    public static final class API {
        /**
         * 主机Host
         */
        public static final String HOST_PATH = "http://47.93.207.58/yxzp/";//外网
        /**
         * 获取验证码接口
         */
        public static final String GET_SMS_CODE = "api/send/smscode.json";
        /**
         * 检测验证码的有效性接口(因为注册用户用了2步操作,在第一步的时候验证一下)
         */
        public static final String CHECK_SMS_CODE = "api/enterprise/sign/usercaptha/check.json";
        /**
         * 注册接口
         */
        public static final String REGISTE = "api/enterprise/sign.json";
        /**
         * 忘记密码
         */
        public static final String FORGOT_CHANGE_PASSWORD = "api/back/password.json";
        /**
         * 修改密码
         */
        public static final String MODIFY_PASSWORD = "api/edit/password.json";
        /**
         * 登录接口
         */
        public static final String LOGIN = "api/enterprise/login.json";
        /**
         * 获取全局配置信息
         */
        public static final String GET_CONFIG_INFO = "api/enterprise/config.json";
        /**
         * 提交意见反馈
         */
        public static final String SUBMIT_FEEDBACK = "api/feedback/save.json";

        /**
         * 保存企业信息
         */
        public static final String SAVE_ENTERPRISE_INFO = "api/enterprise/auth.json";
        /**
         * 检测旧手机
         */
        public static final String CHECK_OLD_PHONE = "api/check/smscode.json";
        /**
         * 绑定新手机
         */
        public static final String BIND_NEW_PHONE = "api/change/mobile.json";
        /**
         * 获取企业列表
         */
        public static final String GET_ENTERPRISE_LIST = "api/enterprise/circle/list.json";
        /**
         * 获取企业详情
         */
        public static final String GET_ENTERPRISE_DETAIL_INFO = "api/enterprise/circle/info.json";
        /**
         * 关注,取消关注企业
         */
        public static final String FOLLOW_OR_CANCEL_FOLLOW = "api/enterprise/circle/followstatus/save.json";
        /**
         * 我的关注列表
         */
        public static final String GET_MY_FOLLOW_LIST = "api/enterprise/follow/list.json";
        /**
         * 获取个人中心tab数据
         */
        public static final String GET_PERSONAL_CENTER_TAB = "api/enterprise/personalCenter.json";
        /**
         * 获取招聘管理岗位列表
         */
        public static final String GET_RECRUIT_MANAGE_JOB_LIST_TAB = "api/enterprise/recruit/jobinfo/list.json";
        /**
         * 发布岗位
         */
        public static final String SEND_JOB = "api/enterprise/recruit/jobinfo/save.json";
        /**
         * 刷新岗位
         */
        public static final String UPDATE_JOB = "api/enterprise/recruit/jobinfo/update.json";
        /**
         * 获取职位详情
         */
        public static final String GET_JOB_INFO_DETAIL = "api/enterprise/recruit/jobinfo.json";
        /**
         * 获取简历录用列表(未录用,已录用)
         */
        public static final String GET_RESUME_HIRED_LIST = "api/enterprise/recruit/resume/list.json";
        /**
         * 获取简历详情
         */
        public static final String GET_RESUME_DETAIL_INFO = "api/enterprise/recruit/resume/info.json";
        /**
         * 屏蔽简历
         */
        public static final String SET_RESUME_SHIELD_OR_NOT_SHIELD = "api/enterprise/recruit/resume/shield.json";
        /**
         * 拒绝录用
         */
        public static final String REFUSE_HIRE_RESUME = "api/enterprise/resume/refuse.json";
        /**
         * 确认入职
         */
        public static final String CONFIRM_ENTRY = "api/enterprise/resume/confirmentry.json";
        /**
         * 拒绝入职
         */
        public static final String REFUSE_ENTRY = "api/enterprise/resume/nonentry.json";
        /**
         * 获取简历单价和账户余额
         */
        public static final String GET_UNIT_PRICE_AND_BALANCE = "api/enterprise/order/info.json";
        /**
         * 通过ordersn获取待支付订单信息(简历详情里点击 继续支付所调用的接口)
         */
        public static final String GET_WAIT_PAY_ORDER_INFO = "api/enterprise/order/getorderinfo.json";
        /**
         * 生成支付所需订单信息【0：余额； 1 ：微信支付； 2：支付宝支付】
         */
        public static final String GET_PAY_ORDER_INFO = "api/enterprise/order/create.json";
        /**
         * 生成待支付订单重新支付订单信息【0：余额； 1 ：微信支付； 2：支付宝支付】
         */
        public static final String GET_PAY_AGAIN_ORDER_INFO = "api/enterprise/order/payagain.json";
        /**
         * 生成充值支付订单信息【0：余额； 1 ：微信支付； 2：支付宝支付】
         */
        public static final String GET_PAY_RECHARGE_ORDER_INFO = "api/enterprise/recharge/create.json";
        /**
         * 通知服务器已支付完成,可去查询支付结果的回调
         */
        public static final String NOTIFY_PAY_FINISH = "api/enterprise/pay/callback.json";
        /**
         * 百度推送传递ChannelId
         */
        public static final String SAVE_CHANNEL_ID = "api/enterprise/pushparameter.json";
        /**
         * 保存百度推送状态(开启,关闭,免打扰)
         */
        public static final String SAVE_PUSH_STATUS = "api/enterprise/pushstatus/save.json";
        /**
         * 获取首页数据
         */
        public static final String GET_HOME_TAB = "api/enterprise/home/info.json";
        /**
         * 获取首页推荐简历更多列表
         */
        public static final String GET_HOME_MORE_RESUME_LIST = "api/enterprise/home/more/list.json";
        /**
         * 获取被屏蔽简历列表
         */
        public static final String GET_SHIELD_RESUME_LIST = "api/enterprise/shield/list.json";
        /**
         * 推送消息列表(按类型1:系统消息 2:投递消息)
         */
        public static final String GET_PUSH_MESSAGE_LIST_BY_TYPE = "api/enterprise/pushmessage/list.json";
        /**
         * 获取订单列表(0:待支付 1:已支付 2:已失效 3:全部)
         */
        public static final String GET_ORDER_LIST_BY_TYPE = "api/enterprise/myorder/list.json";
        /**
         * 获取交易明细列表
         */
        public static final String GET_BILL_LIST = "api/enterprise/fundsflow/list.json";
        /**
         * 获取当前订单的已购买简历列表
         */
        public static final String GET_ORDER_RESUME_LIST = "api/enterprise/fundsflow/resume/list.json";
        /**
         * 获取我的账户数据(我的余额,和充值对象)
         */
        public static final String GET_MY_ACCOUNT_DATA = "api/enterprise/myaccount/info.json";
    }
}
