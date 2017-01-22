package com.school.schooldeal.model;

import cn.bmob.v3.BmobObject;

/**
 * Created by GavynZhang on 2017/1/20 19:04.
 * 餐馆发起的送外卖请求
 */

public class TakeawayServiceRequest extends BmobObject {
    private String requestID;
    private String generatedTime;
    private Student student;       //买这份外卖的学生
    private Restaurant restaurant; //发起这份请求的餐馆
    private Apartment apartment;   //这份外卖的目的公寓
    private String remarks;        //备注信息
    private Float remuneration;    //预期报酬
    private Integer number;        //份数

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Apartment getApartment() {
        return apartment;
    }

    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
    }

    public String getGeneratedTime() {
        return generatedTime;
    }

    public void setGeneratedTime(String generatedTime) {
        this.generatedTime = generatedTime;
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

    public String getRequestID() {
        return requestID;
    }

    public void setRequestID(String requestID) {
        this.requestID = requestID;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "TakeawayServiceRequest{" +
                "apartment=" + apartment +
                ", requestID='" + requestID + '\'' +
                ", generatedTime='" + generatedTime + '\'' +
                ", student=" + student +
                ", restaurant=" + restaurant +
                ", remarks='" + remarks + '\'' +
                ", remuneration=" + remuneration +
                ", number=" + number +
                '}';
    }
}
