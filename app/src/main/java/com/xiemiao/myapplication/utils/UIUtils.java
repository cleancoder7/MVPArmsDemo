package com.xiemiao.myapplication.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Process;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.CharacterStyle;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.google.gson.Gson;
import com.xiemiao.myapplication.app.Constants;
import com.xiemiao.myapplication.app.BaseApplication;
import com.xiemiao.myapplication.common.mvp.model.bean.ConfigInfoResult;
import com.xiemiao.myapplication.common.mvp.model.bean.LoginResult;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.util.List;
import java.util.UUID;

import static android.content.Context.INPUT_METHOD_SERVICE;


/**
 * date: 2017年2月17日 下午2:26:37 <br/>
 * 创建一些UI相关的工具类
 *
 * @author xiemiao
 */
public class UIUtils {

    /**
     * getContext:(获取全局上下文). <br/>
     *
     * @return
     * @author xiemiao
     */
    public static Context getContext() {
        return BaseApplication.getContext();
    }

    /**
     * getResources:(获取资源对象). <br/>
     *
     * @return
     * @author xiemiao
     */
    public static Resources getResources() {
        return getContext().getResources();
    }

    /**
     * getString:(根据资源ID获取string.xml下的字符串). <br/>
     *
     * @param resId
     * @return
     * @author xiemiao
     */
    public static String getString(int resId) {
        return getResources().getString(resId);
    }

    /**
     * getStringArray:(根据资源ID获取string.xml下的字符串数组). <br/>
     *
     * @param resId
     * @return
     * @author xiemiao
     */
    public static String[] getStringArray(int resId) {
        return getResources().getStringArray(resId);
    }

    /**
     * getColor:(根据资源ID获取colors.xml下的颜色). <br/>
     *
     * @param resId
     * @return
     * @author xiemiao
     */
    public static int getColor(int resId) {
        return getResources().getColor(resId);
    }

    /**
     * getPackageName:(获取应用包名). <br/>
     *
     * @return
     * @author xiemiao
     */
    public static String getPackageName() {
        return getContext().getPackageName();
    }

    /**
     * getHandler:(获取Handler). <br/>
     *
     * @return
     * @author xiemiao
     */
    public static Handler getHandler() {
        return BaseApplication.getHandler();
    }

    /**
     * getMainThreadId:(获取主线程ID). <br/>
     *
     * @return
     * @author xiemiao
     */
    public static long getMainThreadId() {
        return BaseApplication.getMainThreadId();
    }

    /**
     * postSafeTask:(执行安全任务，自行判断是否在主线程). <br/>
     *
     * @param task
     * @author xiemiao
     */
    public static void postSafeTask(Runnable task) {
        // 调用此方法时，先获取当前线程ID
        int myCurThreadId = Process.myTid();
        // 与主线程ID进行比较
        if (myCurThreadId == getMainThreadId()) {
            // 如果是主线程，就直接执行务
            task.run();
        } else {
            // 如果是子线程，就用handler去post任务
            getHandler().post(task);
        }
    }

    /**
     * dp2px:(dp转px). <br/>
     *
     * @param dp
     * @return
     * @author xiemiao
     */
    public static int dp2px(float dp) {
        float density = getContext().getResources().getDisplayMetrics().density;
        int px = (int) (dp * density + 0.5f);
        return px;
    }

    /**
     * px2dp:(px转dp). <br/>
     *
     * @param ctx 上下文
     * @param px  px像素值
     * @return
     * @author xiemiao
     */
    public static float px2dp(Context ctx, int px) {
        float density = getContext().getResources().getDisplayMetrics().density;
        float dp = px / density;
        return dp;
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param spValue （DisplayMetrics类中属性scaledDensity）
     * @return
     */
    public static int sp2px(float spValue) {
        final float fontScale = getContext().getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }


    /**
     * 旋转图片
     *
     * @param angle  旋转角度
     * @param bitmap 要旋转的图片
     * @return 旋转后的图片
     */
    public static Bitmap rotate(Bitmap bitmap, int angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix,
                true);
    }

    /**
     * 将double保留n位小数
     *
     * @param num the num
     * @return the string
     * @User: xiemiao
     * @Time: 2017 -05-10
     * @Date: 11 :35:12
     */
    public static String double2Str(double num, int n) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < n; i++) {
            sb.append("0");
        }
        DecimalFormat df = null;
        if (n == 0) {
            df = new DecimalFormat("###" + sb.toString());
        } else {
            df = new DecimalFormat("###0." + sb.toString());
        }
        return df.format(num);
    }

    /**
     * 功能：通过服务器返回的int类型,返回真假(0:false 1:true) 描述：
     *
     * @param i
     * @return
     * @author：xiemiao
     * @date:2017年3月9日
     */
    public static boolean intToBoolean(int i) {
        switch (i) {
            case 1:
                return true;
            case 0:
                return false;
        }
        return false;
    }

    /**
     * 功能：通过true和false,返回服务器需要的1和2 描述：
     *
     * @param b
     * @return
     * @author：xiemiao
     * @date:2017年3月9日
     */
    public static int booleanToInt(Boolean b) {
        if (b) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * 功能：动态计算listview孩子的高度,解决listview嵌套scrollview显示不全 描述：
     *
     * @param listView
     * @author：xiemiao
     * @date:2017年3月9日
     */
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        // 获取ListView对应的Adapter
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        for (int i = 0, len = listAdapter.getCount(); i < len; i++) {
            // listAdapter.getCount()返回数据项的数目
            View listItem = listAdapter.getView(i, null, listView);
            // 计算子项View 的宽高
            listItem.measure(0, 0);
            // 统计所有子项的总高度
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        // listView.getDividerHeight()获取子项间分隔符占用的高度
        // params.height最后得到整个ListView完整显示需要的高度
        listView.setLayoutParams(params);
    }

    /**
     * 功能：动态计算listview孩子的高度,解决listview嵌套scrollview显示不全 描述：
     *
     * @param listView
     * @author：xiemiao
     * @date:2017年3月9日
     */
    public static void setListViewHeightBasedOnChildren(ListView listView, int offset) {
        // 获取ListView对应的Adapter
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        for (int i = 0, len = listAdapter.getCount(); i < len; i++) {
            // listAdapter.getCount()返回数据项的数目
            View listItem = listAdapter.getView(i, null, listView);
            // 计算子项View 的宽高
            listItem.measure(0, 0);
            // 统计所有子项的总高度
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1)
        ) + offset;
        // listView.getDividerHeight()获取子项间分隔符占用的高度
        // params.height最后得到整个ListView完整显示需要的高度
        listView.setLayoutParams(params);
    }

    /**
     * 功能：获取随机唯一的字符串码 描述：
     *
     * @return
     * @author：xiemiao
     * @date:2017年4月6日
     */
    public static String getRandomStr() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 得到sdcard路径
     *
     * @return
     */
    public static String getCachePath() {
        return getContext().getCacheDir().getPath();
    }


    /**
     * 功能：保存资源文件图片到SD卡 描述：
     *
     * @param logoPath
     * @throws Throwable
     * @author：xiemiao
     * @date:2017年4月7日
     */
    public static void saveResToSDCard(String logoPath, int resID) throws Throwable {
        InputStream inStream = getContext().getResources().openRawResource(resID);
        File file = new File(logoPath);
        // 文件存在就不保存了
        if (file.exists()) {
            return;
        }
        FileOutputStream fileOutputStream = new FileOutputStream(file);// 存入SDCard
        byte[] buffer = new byte[10];
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        int len = 0;
        while ((len = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        byte[] bs = outStream.toByteArray();
        fileOutputStream.write(bs);
        outStream.close();
        inStream.close();
        fileOutputStream.flush();
        fileOutputStream.close();
    }

    /**
     * 功能：将字符串转换为唯一的6位整型 描述：
     *
     * @param source
     * @return
     * @author：xiemiao
     * @date:2017年4月18日
     */
    public static int get6MD5(String source) {
        String s = null;
        int parseInt = 0;
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(source.getBytes());
            byte tmp[] = md.digest();
            char str[] = new char[6];
            int k = 0;
            for (int i = 0; i < 6; i++) {
                byte byte0 = tmp[i];
                // 只取高位
                str[k++] = hexDigits[(byte0 >>> 4 & 0xf) % 10];
                // str[k++] = hexDigits[byte0 & 0xf];
            }
            s = new String(str); // 换后的结果转换为字符串
            parseInt = Integer.parseInt(s);// 将字符串转换为整型
        } catch (Exception e) {
            e.printStackTrace();
        }
        return parseInt;
    }

    /**
     * 判断应用是否是在后台
     */
    public static boolean isBackground(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context
                .ACTIVITY_SERVICE);
        KeyguardManager keyguardManager = (KeyguardManager) context.getSystemService(Context
                .KEYGUARD_SERVICE);

        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager
                .getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
            if (TextUtils.equals(appProcess.processName, context.getPackageName())) {
                boolean isBackground = (appProcess.importance != ActivityManager
                        .RunningAppProcessInfo.IMPORTANCE_FOREGROUND
                        && appProcess.importance != ActivityManager.RunningAppProcessInfo
                        .IMPORTANCE_VISIBLE);
                boolean isLockedState = keyguardManager.inKeyguardRestrictedInputMode();
                return isBackground || isLockedState;
            }
        }
        return false;
    }

    /**
     * 判断应用是否已经启动
     *
     * @param context     一个context
     * @param packageName 要判断应用的包名
     * @return boolean
     */
    public static boolean isAppAlive(Context context, String packageName) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context
                .ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> processInfos = activityManager
                .getRunningAppProcesses();
        for (int i = 0; i < processInfos.size(); i++) {
            if (processInfos.get(i).processName.equals(packageName)) {
                Log.i("NotificationLaunch", String.format("the %s is running, isAppAlive return " +
                        "true", packageName));
                return true;
            }
        }
        Log.i("NotificationLaunch", String.format("the %s is not running, isAppAlive return " +
                "false", packageName));
        return false;
    }

    /**
     * 改变textview的drawabletop图片的大小
     *
     * @param textview
     * @param size     传入dp值
     */
    public static void changeDrawableTopSize(TextView textview, int size) {
        Drawable[] drawables = textview.getCompoundDrawables();
        Drawable drawable = drawables[1];
        drawable.setBounds(0, 0, UIUtils.dp2px(size), UIUtils.dp2px(size));
        textview.setCompoundDrawables(null, drawable, null, null);
    }

    /**
     * 改变radiobutton的drawableleft图片的大小
     *
     * @param radioButton
     * @param size        传入dp值
     */
    public static void changeDrawableLeftSize(RadioButton radioButton, int size) {
        Drawable[] drawables = radioButton.getCompoundDrawables();
        Drawable drawable = drawables[0];
        drawable.setBounds(0, 0, UIUtils.dp2px(size), UIUtils.dp2px(size));
        radioButton.setCompoundDrawables(drawable, null, null, null);
    }


    /**
     * md5加密
     */
    public final static String md5(String s) {
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F'};
        try {
            byte[] btInput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str).toLowerCase();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * getLoginResult:(获取到缓存的登录信息). <br/>
     *
     * @return
     * @author xiemiao
     */
    public static LoginResult getLoginResult() {
        LoginResult loginResult = (LoginResult) FileUtils.getBean(getContext(), Constants.LOGIN_INFO_KEY);
        return loginResult;
    }

    /**
     * putLoginResult:(存放用户信息). <br/>
     *
     * @param result
     * @author xiemiao
     */
    public static void putLoginResult(LoginResult result) {
        FileUtils.putBean(getContext(), Constants.LOGIN_INFO_KEY, result);
    }

    /**
     * 存放全局配置信息
     *
     * @param result the result
     * @User: xiemiao
     * @Time: 2017 -05-15
     * @Date: 11 :29:55
     */
    public static void putConfigInfoResult(ConfigInfoResult result) {
        FileUtils.putBean(getContext(), Constants.CONFIG_KEY, result);
    }

    /**
     * 获取全局配置信息
     *
     * @return the config info result
     * @User: xiemiao
     * @Time: 2017 -05-15
     * @Date: 11 :33:48
     * @Desc:
     */
    public static ConfigInfoResult getConfigInfoResult() {
        ConfigInfoResult configInfoResult = (ConfigInfoResult) FileUtils.getBean(getContext(), Constants.CONFIG_KEY);
        return configInfoResult;
    }


    /**
     * 功能:获取版本
     *
     * @param pkg 应用包名
     */
    public static int getVersionByPackage(Context context, String pkg) {
        List<PackageInfo> pil = context.getPackageManager().getInstalledPackages(0);
        int size = pil.size();
        for (int i = 0; i < size; i++) {
            PackageInfo pi = pil.get(i);
            if (pi.packageName.equalsIgnoreCase(pkg)) {
                return pi.versionCode;
            }
        }
        return 0;
    }

    /**
     * 判断是否有sdcard
     *
     * @return
     */
    public static boolean hasSDCard() {
        boolean b = false;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            b = true;
        }
        return b;
    }

    /**
     * 检测编辑框输入长度是否符合
     *
     * @param editText the edit text
     * @User: xiemiao
     * @Time: 2017 -05-17
     * @Date: 11 :23:26
     */
    public static boolean checkEditInputLength(String editText) {
        int inputCount = 5;
        if (editText.length() < inputCount) {
            ToastUtil.showToast(String.format("最少输入%s个字符", inputCount));
            return false;
        }
        if (containsEmoji(editText)) {
            ToastUtil.showToast("不支持Emoji表情");
            return false;
        }
        return true;
    }

    /**
     * 检测编辑框输入长度是否符合
     *
     * @param editText the edit text
     * @User: xiemiao
     * @Time: 2017 -05-17
     * @Date: 11 :23:26
     */
    public static boolean checkEditInputLength(String editText, int inputCount) {
        if (editText.length() < inputCount) {
            ToastUtil.showToast(String.format("最少输入%s个字符", inputCount));
            return false;
        }
        if (containsEmoji(editText)) {
            ToastUtil.showToast("不支持Emoji表情");
            return false;
        }
        return true;
    }

    /**
     * 检测编辑框输入长度是否符合,可附加字符
     *
     * @param editText the edit text
     * @User: xiemiao
     * @Time: 2017 -05-17
     * @Date: 11 :23:26
     */
    public static boolean checkEditInputLength(String editText, int inputCount, String addText) {
        if (editText.length() < inputCount) {
            ToastUtil.showToast(String.format(addText + "最少输入%s个字符", inputCount));
            return false;
        }
        if (containsEmoji(editText)) {
            ToastUtil.showToast("不支持Emoji表情");
            return false;
        }
        return true;
    }

    /**
     * 判断字符串是否包含emoji表情
     *
     * @param source the source
     * @return the boolean
     * @User: xiemiao
     * @Time: 2017 -05-17
     * @Date: 13 :01:51
     */
    public static boolean containsEmoji(String source) {
        int len = source.length();
        boolean isEmoji = false;
        for (int i = 0; i < len; i++) {
            char hs = source.charAt(i);
            if (0xd800 <= hs && hs <= 0xdbff) {
                if (source.length() > 1) {
                    char ls = source.charAt(i + 1);
                    int uc = ((hs - 0xd800) * 0x400) + (ls - 0xdc00) + 0x10000;
                    if (0x1d000 <= uc && uc <= 0x1f77f) {
                        return true;
                    }
                }
            } else {
                // non surrogate
                if (0x2100 <= hs && hs <= 0x27ff && hs != 0x263b) {
                    return true;
                } else if (0x2B05 <= hs && hs <= 0x2b07) {
                    return true;
                } else if (0x2934 <= hs && hs <= 0x2935) {
                    return true;
                } else if (0x3297 <= hs && hs <= 0x3299) {
                    return true;
                } else if (hs == 0xa9 || hs == 0xae || hs == 0x303d || hs == 0x3030 || hs == 0x2b55 || hs == 0x2b1c || hs == 0x2b1b || hs == 0x2b50 || hs == 0x231a) {
                    return true;
                }
                if (!isEmoji && source.length() > 1 && i < source.length() - 1) {
                    char ls = source.charAt(i + 1);
                    if (ls == 0x20e3) {
                        return true;
                    }
                }
            }
        }
        return isEmoji;
    }

    /**
     * 功能:解析json字符串为对象
     */
    public static Object parseJson2Obj(String respStr, Class<?> returnClass) {
        return new Gson().fromJson(respStr, returnClass);
    }

    /**
     * 解析对象为json
     *
     * @param obj the obj
     * @return the string
     * @User: xiemiao
     * @Time: 2017 -06-16
     * @Date: 14 :17:09
     */
    public static String parseObj2Json(Object obj) {
        return new Gson().toJson(obj);
    }

    /**
     * 隐藏输入法键盘
     *
     * @param v the v
     * @User: xiemiao
     * @Time: 2017 -05-24
     * @Date: 13 :17:36
     */
    public static void hintSoftInput(Activity activity, View v) {
        // 隐藏键盘
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0); //强制隐藏键盘
    }

    /**
     * 高亮显示字符串里的关键字
     *
     * @param str   the str 原字符串
     * @param key   the key 要高亮展示的key
     * @param color the color 高亮颜色
     * @return the spannable string builder
     * @User: xiemiao
     * @Time: 2017 -05-26
     * @Date: 14 :02:23
     */
    public static SpannableStringBuilder formatStrHeightLight(String str, String key, int color) {
        int start = 0, end = 0;
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(key)) {
            if (str.contains(key)) {
                /*
                 *  返回heightLightStr字符串str字符串中第一次出现处的索引。
				 */
                start = str.indexOf(key);
                end = start + key.length();
            } else {
                return null;
            }
        } else {
            return null;
        }
        SpannableStringBuilder spBuilder = new SpannableStringBuilder(str);
        color = getResources().getColor(color);
        CharacterStyle charaStyle = new ForegroundColorSpan(color);
        spBuilder.setSpan(charaStyle, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spBuilder;
    }

    /**
     * 隐藏软键盘,这种方式参数为activity
     *
     * @param activity the activity
     * @User: xiemiao
     * @Time: 2017 -06-02
     * @Date: 09 :57:59
     */
    public static void hideInputForce(Activity activity) {
        if (activity == null || activity.getCurrentFocus() == null)
            return;
        ((InputMethodManager) activity.getSystemService(INPUT_METHOD_SERVICE))
                .hideSoftInputFromWindow(activity.getCurrentFocus()
                        .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /**
     * 将bitmap保存为图片
     *
     * @param bm the bm
     * @param f  the path
     * @User: xiemiao
     * @Time: 2017 -06-17
     * @Date: 17 :33:35
     */
    public static boolean saveBitmap(Bitmap bm, File f) {
        if (f.exists()) {
            f.delete();
        }
        boolean isSuccess = true;
        try {
            FileOutputStream out = new FileOutputStream(f);
            bm.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            isSuccess = false;
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            isSuccess = false;
        }
        return isSuccess;
    }

    /**
     * 检测文本double格式是否正确
     *
     * @param money the money
     * @User: xiemiao
     * @Time: 2017 -06-28
     * @Date: 15 :51:17
     */
    public static boolean checkIsDouble(String money) {
        boolean isOk = true;
        try {
            Double.parseDouble(money);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            isOk = false;
        }
        return isOk;
    }

    /**
     * 获取APP版本名称
     *
     * @return the version name
     * @User: xiemiao
     * @Time: 2017 -07-04
     * @Date: 17 :21:22
     * @Desc:
     */
    public static String getVersionName() {
        String versionName = "";
        try {
            String pkName = getContext().getPackageName();
            versionName = getContext().getPackageManager().getPackageInfo(
                    pkName, 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionName;
    }

    /**
     * 获取APP版本号
     *
     * @return the version name
     * @User: xiemiao
     * @Time: 2017 -07-04
     * @Date: 17 :21:22
     * @Desc:
     */
    public static int getVersionCode() {
        int versionCode = 0;
        try {
            String pkName = getContext().getPackageName();
            versionCode = UIUtils.getContext().getPackageManager()
                    .getPackageInfo(pkName, 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }

    /**
     * 功能:判断输入框是否有值
     */
    public static boolean editTextIsNull(EditText editText) {
        return editText.getText().toString().trim().equals("") && editText.getText().toString().trim().length() == 0;
    }

    /**
     * 进行编辑框清空
     *
     * @param editText the edit text
     * @param button   the button
     * @User: xiemiao
     * @Time: 2017 -05-25
     * @Date: 11 :05:19
     */
    public static void clearEditTextListener(final EditText editText, final ImageView button) {
        if (editTextIsNull(editText)) {
            button.setVisibility(View.GONE);
        } else {
            button.setVisibility(View.VISIBLE);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    editText.setText("");
                }
            });
        }
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (editTextIsNull(editText)) {
                    button.setVisibility(View.GONE);
                } else {
                    button.setVisibility(View.VISIBLE);
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            editText.setText("");
                        }
                    });
                }
            }
        });
    }

    /**
     * 打电话
     *
     * @param phone the phone
     * @User: xiemiao
     * @Time: 2017 -07-17
     * @Date: 11 :54:36
     */
    public static void callPhone(String phone) {
        Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone));
        dialIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getContext().startActivity(dialIntent);
    }

    /**
     * 拼接图片链接完整地址
     *
     * @param targetUrl the target url
     * @return the string
     * @User: xiemiao
     * @Time: 2017 -07-19
     * @Date: 11 :58:00
     */
    public static String createImageUrl(String targetUrl) {
        String host = Constants.API.HOST_PATH;
        StringBuffer buffer = new StringBuffer();
        buffer.append(host);
        buffer.append(targetUrl);
        return buffer.toString();
    }

    /**
     * 将字符串转换为int值
     *
     * @param str the str
     * @return the int
     * @User: xiemiao
     * @Time: 2017 -07-20
     * @Date: 19 :57:27
     */
    public static int str2int(String str) {
        int result = 0;
        try {
            result = Integer.valueOf(str);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            Log.e("str2int", "格式转换错误");
        }
        return result;
    }

    /**
     * 将字符串转换为double值
     *
     * @param str the str
     * @return the int
     * @User: xiemiao
     * @Time: 2017 -07-20
     * @Date: 19 :57:27
     */
    public static double str2double(String str) {
        double result = 0;
        try {
            result = Double.valueOf(str);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            Log.e("str2double", "格式转换错误");
        }
        return result;
    }

    /**
     * 隐藏手机中间4位,替换为星号
     *
     * @param mobile the mobile
     * @return the int
     * @User: xiemiao
     * @Time: 2017 -07-27
     * @Date: 19 :04:10
     */
    public static String hintPhoneCenter4(String mobile) {
        StringBuffer sb = new StringBuffer();
        try {
            sb.append(mobile.substring(0, 3));
            sb.append("****");
            sb.append(mobile.substring(7, 11));
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("hintPhoneCenter4", "手机号转换中间4位为星号异常");
        }
        return sb.toString();
    }

    /**
     * 播放本地raw目录下的音乐
     *
     * @param rawId the raw id
     * @User: xiemiao
     * @Time: 2017 -09-11
     * @Date: 14 :27:42
     */
    public static void playVoice(int rawId) {
        try {
            MediaPlayer mediaPlayer = MediaPlayer.create(getContext(), rawId);
            mediaPlayer.start();
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mp.release();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
