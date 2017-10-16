package com.xiemiao.myapplication.net;

/**
 * Title:接口返回基类 <br />
 * Description: <br />
 * author:xiemiao <br />
 * date: 2017-10-16  <br />
 * version 1.0 <br />
 */
public class BaseResponse {
    /**
     * 返回结果 0：失败 1：成功
     */
    protected int result;
    /**
     * result为0时有效，错误描述信息
     * result为1时，为空字符串
     */
    protected String error;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public boolean isOK() {
        return result == 1;
    }
}
