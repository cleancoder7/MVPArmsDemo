package com.xiemiao.myapplication.utils;

import android.content.Context;
import android.widget.Toast;

public class ToastUtil
{
	private static Toast toast;

	/**
	 * 能够连续弹的吐司，不会等上个吐司消失
	 * 
	 * @param context
	 * @param text
	 */
	public static void showSingToast(Context context, String text)
	{
		if (toast == null)
		{
			toast = Toast.makeText(context, text, 0);
		}
		toast.setText(text);//将text文本设置给吐司
		toast.show();
	}

	public static void showToast(String msg)
	{
		Toast.makeText(UIUtils.getContext(), msg, 0).show();
	}

	public static void showToast(int resId)
	{
		Toast.makeText(UIUtils.getContext(), UIUtils.getString(resId), Toast.LENGTH_LONG).show();
	}
}
