/**
 * <P>Title:</p>
 * <P>Description:</p>
 * <p>Copyright:手趣云商科技有限公司   Copyright(c)2016</p>
 *
 * @author:xiemiao
 * @date 上午11:43:05
 * @version 1.0
 */
package com.xiemiao.myapplication.utils;

import android.content.Context;
import android.widget.ImageView;

import com.youth.banner.loader.ImageLoader;

/**
 * banner重写的图片加载器,此处替换自己的图片加载框架
 *
 * @User: xiemiao
 * @Time: 2017 -07-29
 * @Date: 11 :04:24
 */
public class GlideImageLoader extends ImageLoader {

    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        // Glide 加载图片简单用法
//        EnterpriseBanner banner = (EnterpriseBanner) path;
//        GlideUtils.loadImageView(UIUtils.createImageUrl(banner.getImgurl()), imageView, R.mipmap.default_replace_img);
    }

}
