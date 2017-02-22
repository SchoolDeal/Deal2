package com.school.schooldeal.takeout.model.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by U-nookia on 2016/12/22.
 * 外卖订单bean
 */

public class TakeOutOrderBean{

    private String id;
    private int amount;
    private String destination;
    private String restaurantName;
    private String restaurantAddress;
    private Float money;
    //状态，根据此状态调整界面（按钮）
    private int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public TakeOutOrderBean(){

    }

    public TakeOutOrderBean(int amount) {
        this.amount = amount;
        money = 10f;
        destination = "dsfa";
        restaurantName = "dsaf";
        restaurantAddress = "asdfg";
    }

    public TakeOutOrderBean(int amount, String destination, Float money, String restaurantAddress, String restaurantName) {
        this.amount = amount;
        this.destination = destination;
        this.money = money;
        this.restaurantAddress = restaurantAddress;
        this.restaurantName = restaurantName;
    }

    public TakeOutOrderBean(String id, int amount, String destination, String restaurantName, String restaurantAddress, Float money) {
        this.id = id;
        this.amount = amount;
        this.destination = destination;
        this.restaurantName = restaurantName;
        this.restaurantAddress = restaurantAddress;
        this.money = money;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getRestaurantAddress() {
        return restaurantAddress;
    }

    public void setRestaurantAddress(String restaurantAddress) {
        this.restaurantAddress = restaurantAddress;
    }

    public Float getMoney() {
        return money;
    }

    public void setMoney(Float money) {
        this.money = money;
    }
}
