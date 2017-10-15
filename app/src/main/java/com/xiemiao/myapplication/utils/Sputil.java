package com.xiemiao.myapplication.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @version 1.0
 * @Title:
 * @Description:存储配置相关内容
 * @Copyright:手趣云商科技有限公司 Copyright(c)2016
 * @author:xiemiao
 * @date 2017年3月8日
 */
public class Sputil {
    // 写
    private static SharedPreferences sp;

    /**
     * 将boolean变量写入sp中
     *
     * @param context 上下文环境
     * @param key     存储节点名称
     * @param value   存储节点对应的值boolean
     */
    public static void putBoolean(Context context, String key, boolean value) {
        // 因为sp是静态的，不知道它什么时候消失，所以要判断sp是不是空的
        if (sp == null) {
            sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        }
        sp.edit().putBoolean(key, value).commit();
    }

    // 读

    /**
     * 通过节点取对应的值，从sp中
     *
     * @param context  上下文环境
     * @param key      存储节点名称
     * @param defValue 存储节点对应的值boolean
     * @return
     */
    public static boolean getBoolean(Context context, String key,
                                     boolean defValue) {
        // 因为sp是静态的，不知道它什么时候消失，所以要判断sp是不是空的
        if (sp == null) {
            sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        }
        return sp.getBoolean(key, defValue);// 通过节点key去取值
    }

    /**
     * 将string变量写入sp中
     *
     * @param context 上下文环境
     * @param key     存储节点名称
     * @param value   存储节点对应的值string
     */
    public static void putString(Context context, String key, String value) {
        // 因为sp是静态的，不知道它什么时候消失，所以要判断sp是不是空的
        if (sp == null) {
            sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        }
        sp.edit().putString(key, value).commit();
    }

    // 读

    /**
     * 通过节点取对应的值，从sp中
     *
     * @param context  上下文环境
     * @param key      存储节点名称
     * @param defValue 存储节点对应的值string
     * @return
     */
    public static String getString(Context context, String key, String defValue) {
        // 因为sp是静态的，不知道它什么时候消失，所以要判断sp是不是空的
        if (sp == null) {
            sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        }
        return sp.getString(key, defValue);// 通过节点key去取值
    }

    /**
     * 移除sp内的节点
     *
     * @param context 上下文环境
     * @param key     节点名称
     */
    public static void remove(Context context, String key) {
        if (sp == null) {
            sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        }
        sp.edit().remove(key).commit();
    }

    public static void putInt(Context context, String key, int value) {
        // 因为sp是静态的，不知道它什么时候消失，所以要判断sp是不是空的
        if (sp == null) {
            sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        }
        sp.edit().putInt(key, value).commit();
    }
    // 读

    public static int getInt(Context context, String key, int defValue) {
        // 因为sp是静态的，不知道它什么时候消失，所以要判断sp是不是空的
        if (sp == null) {
            sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        }
        return sp.getInt(key, defValue);// 通过节点key去取值
    }

}
