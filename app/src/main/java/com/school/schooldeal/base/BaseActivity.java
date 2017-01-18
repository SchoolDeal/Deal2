package com.school.schooldeal.base;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.WindowManager;

import butterknife.ButterKnife;

/**
 * Created by U-nookia on 2016/12/19.
 * activity的base
 */

public abstract class BaseActivity extends FragmentActivity {

    protected Context context = this;
    protected String TAG = this.getClass().getName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());
        ButterKnife.bind(this);

        initData();
        initState();  //实现沉浸式状态栏
    }

    private void initState() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            //getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

    //用于做数据或其他初始化的方法
    protected abstract void initData();

    //获取到当前activity的view的方法
    protected abstract int getContentViewId();
}
