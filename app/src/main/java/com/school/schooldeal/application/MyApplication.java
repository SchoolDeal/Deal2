package com.school.schooldeal.application;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobInstallation;
import io.rong.imkit.RongIM;

/**
 * Created by U-nookia on 2016/12/19.
 * 做全局初始化
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化bmob
        Bmob.initialize(this,"3f4d2c40f97655bcc35f76977f1a1158");
        // 使用推送服务时的初始化操作
        BmobInstallation.getCurrentInstallation(this).save();
        /**
         * OnCreate 会被多个进程重入，这段保护代码，确保只有需要使用 RongIM 的进程和 Push 进程执行了 init。
         */
        if (getApplicationInfo().packageName.equals(getCurProcessName(getApplicationContext())) ||
                "io.rong.push".equals(getCurProcessName(getApplicationContext()))) {
            /**
             * IMKit SDK调用第一步 初始化
             */
            RongIM.init(this);
        }
    }

    /**
     * 获得当前进程的名字
     *
     * @param context
     * @return 进程号
     */
    public static String getCurProcessName(Context context) {

        int pid = android.os.Process.myPid();

        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);

        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager
                .getRunningAppProcesses()) {

            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
    }
}
