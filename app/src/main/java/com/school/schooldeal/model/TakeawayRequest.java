package com.school.schooldeal.model;

import com.school.schooldeal.sign.model.RestaurantUser;

import cn.bmob.v3.BmobObject;

/**
 * Created by GavynZhang on 2017/2/10 0:25.
 */

public class TakeawayRequest extends BmobObject {

    private String requestID;
    private String generatedTime;
    //private Apartment apartment;   //这份外卖的目的公寓
    //private String destination;         //送达地址
    private RestaurantUser restaurant;  //发起这份请求的餐馆
    private String consigneeName;       //收货人姓名
    private String remarks;             //备注信息
    private Float remuneration;         //预期报酬
    private Integer amount;             //份数
    private School school;              //收货学校
    private Apartment apartment;        //收货公寓
    private String bedroom;             //收货寝室号
    private Integer status;                 //服务单的状态，0: 未被接下  1: 已被接下  2: 被取消

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

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

    public String getConsigneeName() {
        return consigneeName;
    }

    public void setConsigneeName(String consigneeName) {
        this.consigneeName = consigneeName;
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

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public Apartment getApartment() {
        return apartment;
    }

    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
    }

    public String getBedroom() {
        return bedroom;
    }

    public void setBedroom(String bedroom) {
        this.bedroom = bedroom;
    }

    @Override
    public String toString() {
        return "TakeawayRequest{" +
                "requestID='" + requestID + '\'' +
                ", generatedTime='" + generatedTime + '\'' +
                ", restaurant=" + restaurant +
                ", consigneeName='" + consigneeName + '\'' +
                ", remarks='" + remarks + '\'' +
                ", remuneration=" + remuneration +
                ", amount=" + amount +
                ", school=" + school +
                ", apartment=" + apartment +
                ", bedroom='" + bedroom + '\'' +
                ", status=" + status +
                '}';
    }
}
