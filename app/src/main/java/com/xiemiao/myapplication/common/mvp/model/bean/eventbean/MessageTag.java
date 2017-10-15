/**
 * <P>Title:</p>
 * <P>Description:</p>
 * <p>Copyright:手趣云商科技有限公司   Copyright(c)2016</p>
 *
 * @author:xiemiao
 * @date 下午4:10:42
 * @version 1.0
 */
package com.xiemiao.myapplication.common.mvp.model.bean.eventbean;

/**
 * eventbus传值类
 *
 * @User: xiemiao
 * @Time: 2017 -05-08
 * @Date: 14 :52:44
 */
public class MessageTag {
    public String tagStr;
    public int what;
    public String text;
    public String text1;
    public String text2;
    public int int1;
    public int int2;
    public double double1;
    public double double2;
    public Object obj;

    public MessageTag() {
    }

    public MessageTag(String tagStr) {
        super();
        this.tagStr = tagStr;
    }

    public MessageTag(int what) {
        super();
        this.what = what;
    }
}
