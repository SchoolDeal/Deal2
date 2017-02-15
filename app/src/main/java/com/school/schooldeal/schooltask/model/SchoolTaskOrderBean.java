package com.school.schooldeal.schooltask.model;

import com.school.schooldeal.model.CommonRequest;

import cn.bmob.v3.BmobObject;

/**
 * Created by U-nookia on 2016/12/22.
 * 校园任务bean
 */

public class SchoolTaskOrderBean extends BmobObject{
    private int amount;
    private CommonRequest commonRequest;
    public  SchoolTaskOrderBean(int amount,CommonRequest commonRequest){
        this.amount = amount;
        this.commonRequest = commonRequest;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public CommonRequest getCommonRequest() {
        return commonRequest;
    }

    public void setCommonRequest(CommonRequest commonRequest) {
        this.commonRequest = commonRequest;
    }
}
