package com.school.schooldeal.takeout.model;

import cn.bmob.v3.BmobObject;

/**
 * Created by U-nookia on 2016/12/22.
 * 外卖订单bean
 */

public class TakeOutOrderBean{
    private int amount;

    public TakeOutOrderBean(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
