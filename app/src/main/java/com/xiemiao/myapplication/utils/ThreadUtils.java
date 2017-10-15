package com.xiemiao.myapplication.utils;


import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

/**
 * 线程工具
 * Created by xiemiao on 2016/10/1.
 */
public class ThreadUtils {
    /**
     * 在子线程运行的任务
     * @param task
     */
    public static void runInThread(Runnable task){
        new Thread(task).start();
    }


    public static Handler mHandler=new Handler();
    /**
     * 在子线程运行的任务
     * @param task
     */
    public static void runOnUIThread(Runnable task){
       mHandler.post(task);
    }

    /**
     * 在子线程展示土司
     * @param context
     * @param msg
     */
    public static void showThreadToast(final Context context, final String msg){
        runOnUIThread(new Runnable() {
            public void run() {
                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
