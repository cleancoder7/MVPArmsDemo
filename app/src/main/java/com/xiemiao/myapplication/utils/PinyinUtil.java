package com.xiemiao.myapplication.utils;

import opensource.jpinyin.PinyinFormat;
import opensource.jpinyin.PinyinHelper;

/**
 * The type Pinyin util.
 *
 * @User: xiemiao
 * @Time: 2017 -05-05
 * @Date: 13 :18:29
 * @Desc: 汉字转拼音工具类
 */
public class PinyinUtil {
    public static String getPinyin(String str) {
        return PinyinHelper.convertToPinyinString(str, "", PinyinFormat.WITHOUT_TONE).toUpperCase();
    }
}
