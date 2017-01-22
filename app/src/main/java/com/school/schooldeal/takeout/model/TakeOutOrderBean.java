package com.school.schooldeal.takeout.model;

import cn.bmob.v3.BmobObject;

/**
 * Created by U-nookia on 2016/12/22.
 * 外卖订单bean
 */

public class TakeOutOrderBean{
    private int amount;
    private String destination;
    private String restaurantName;
    private String restaurantAddress;
    private int money;

    public TakeOutOrderBean(int amount) {
        this.amount = amount;
    }

    public TakeOutOrderBean(int amount, String destination, int money, String restaurantAddress, String restaurantName) {
        this.amount = amount;
        this.destination = destination;
        this.money = money;
        this.restaurantAddress = restaurantAddress;
        this.restaurantName = restaurantName;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
