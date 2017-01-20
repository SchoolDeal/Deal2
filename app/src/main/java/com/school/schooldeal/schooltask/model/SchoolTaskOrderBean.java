package com.school.schooldeal.schooltask.model;

import cn.bmob.v3.BmobObject;

/**
 * Created by U-nookia on 2016/12/22.
 * 校园任务bean
 */

public class SchoolTaskOrderBean extends BmobObject{
    private int amount;
    public  SchoolTaskOrderBean(int amount){
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
