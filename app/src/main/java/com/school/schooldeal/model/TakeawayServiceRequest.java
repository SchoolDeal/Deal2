package com.school.schooldeal.model;

import com.school.schooldeal.sign.model.RestaurantUser;

import cn.bmob.v3.BmobObject;

/**
 * Created by GavynZhang on 2017/1/20 19:04.
 * 餐馆发起的送外卖请求
 */

public class TakeawayServiceRequest extends BmobObject {
    private String requestID;
    private String generatedTime;
    //private Student student;       //买这份外卖的学生
    //private Restaurant restaurant; //发起这份请求的餐馆
    //private Apartment apartment;   //这份外卖的目的公寓
    private RestaurantUser restaurant;
    private String remarks;        //备注信息
    private Float remuneration;    //预期报酬
    private Integer amount;        //份数
    private String destination;     //送达地址

    public String getRequestID() {
        return requestID;
    }

    public void setRequestID(String requestID) {
        this.requestID = requestID;
    }

    public String getGeneratedTime() {
        return generatedTime;
    }

    public void setGeneratedTime(String generatedTime) {
        this.generatedTime = generatedTime;
    }

    public RestaurantUser getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(RestaurantUser restaurant) {
        this.restaurant = restaurant;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Float getRemuneration() {
        return remuneration;
    }

    public void setRemuneration(Float remuneration) {
        this.remuneration = remuneration;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "TakeawayServiceRequest{" +
                "requestID='" + requestID + '\'' +
                ", generatedTime='" + generatedTime + '\'' +
                ", restaurant=" + restaurant +
                ", remarks='" + remarks + '\'' +
                ", remuneration=" + remuneration +
                ", amount=" + amount +
                ", destination='" + destination + '\'' +
                '}';
    }
}
