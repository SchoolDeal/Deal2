package com.school.schooldeal.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by U-nookia on 2016/12/19.
 * fragment的base
 */

public abstract class BaseFragment extends Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getResourceId(),container,false);
        ButterKnife.bind(this,view);
        initData();
        return view;
    }

    //做数据或其他初始化的方法
    protected abstract void initData();

    //获取当前fragment的view的方法
    protected abstract int getResourceId();
}
