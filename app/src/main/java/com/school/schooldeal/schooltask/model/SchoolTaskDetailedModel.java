package com.school.schooldeal.schooltask.model;

import android.content.Context;

import com.school.schooldeal.commen.util.ToastUtil;
import com.school.schooldeal.model.CommonRequest;

import cn.bmob.v3.listener.SaveListener;

/**
 * Created by 教科书式的机智少年 on 2017/2/15.
 */

public class SchoolTaskDetailedModel implements ImplSchoolTaskDetailedModel{
    private Context context;

    public SchoolTaskDetailedModel(Context context){
        this.context = context;
    }
    @Override
    public void getRobMessage(CommonRequest commonRequest) {
        commonRequest.save(context, new SaveListener() {
            @Override
            public void onSuccess() {
                ToastUtil.makeShortToast(context,"success");
            }

            @Override
            public void onFailure(int i, String s) {
                ToastUtil.makeShortToast(context,"failed");
            }
        });
    }
}
