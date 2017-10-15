package com.xiemiao.myapplication.common.mvp.model.bean;

/**
 * <P>Title: 接口返回对象基类</p>                     
 * <P>Description: 主要定义了</p>                
 * <p>Copyright:杭州手趣科技有限公司 Copyright(c)2013</p>
 * @author: 徐斌                 
 * @version 1.0  2013-08-22                     
 */

public class BaseResponse
{
	/**
	 * 返回结果
	 * 0：失败
	 * 1：成功
	 */
	protected int result;
	/**
	 * result为0时有效，错误描述信息
	 * result为1时，为空字符串
	 */
	protected String error;
	
	public int getResult()
	{
		return result;
	}
	public void setResult(int result)
	{
		this.result = result;
	}
	public String getError()
	{
		return error;
	}
	public void setError(String error)
	{
		this.error = error;
	}
}


