package com.school.schooldeal.application;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;

import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.view.CropImageView;
import com.school.schooldeal.mine.GlideImageLoader;

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

        initBmob();

        initRongIM();

        initImagePicker();
    }

    private void initImagePicker() {
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new GlideImageLoader());   //设置图片加载器
        imagePicker.setShowCamera(true);  //显示拍照按钮
        imagePicker.setCrop(true);        //允许裁剪（单选才有效）
        imagePicker.setSaveRectangle(false); //是否按矩形区域保存
        imagePicker.setSelectLimit(1);    //选中数量限制
        imagePicker.setStyle(CropImageView.Style.CIRCLE);  //裁剪框的形状
        imagePicker.setFocusWidth(800);   //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(800);  //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(1000);//保存文件的宽度。单位像素
        imagePicker.setOutPutY(1000);//保存文件的高度。单位像素
    }

    private void initRongIM() {
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

    private void initBmob() {
        //初始化bmob
        Bmob.initialize(this,"3f4d2c40f97655bcc35f76977f1a1158");
        // 使用推送服务时的初始化操作
        BmobInstallation.getCurrentInstallation(this).save();
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
