package com.school.schooldeal.takeout.model.bean;

import java.io.Serializable;

import cn.bmob.v3.BmobObject;

/**
 * Created by U-nookia on 2016/12/22.
 * 外卖订单bean
 */

public class TakeOutOrderBean implements Serializable{

    private String id;
    private int amount;
    private String destination;
    private String restaurantName;
    private String restaurantAddress;
    private Float money;
    //状态，根据此状态调整界面（按钮）
    private int status;
    private String imgURL;

    private String studentName; //收货人姓名
    private String studentBedroom;  //收货人寝室
    private String studentPhoneNum; //收货人手机号
    private String serviceID;       //对应 Service 的 ID
    private String remarks;         //备注


    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

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

    public TakeOutOrderBean(String id, int amount, String destination, String restaurantName, String restaurantAddress, Float money, String imgURL) {
        this.id = id;
        this.amount = amount;
        this.destination = destination;
        this.restaurantName = restaurantName;
        this.restaurantAddress = restaurantAddress;
        this.money = money;

        this.imgURL = imgURL;
    }

    public String getServiceID() {
        return serviceID;
    }

    public void setServiceID(String serviceID) {
        this.serviceID = serviceID;
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

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentBedroom() {
        return studentBedroom;
    }

    public void setStudentBedroom(String studentBedroom) {
        this.studentBedroom = studentBedroom;
    }

    public String getStudentPhoneNum() {
        return studentPhoneNum;
    }

    public void setStudentPhoneNum(String studentPhoneNum) {
        this.studentPhoneNum = studentPhoneNum;
    }

    @Override
    public String toString() {
        return "TakeOutOrderBean{" +
                "id='" + id + '\'' +
                ", amount=" + amount +
                ", destination='" + destination + '\'' +
                ", restaurantName='" + restaurantName + '\'' +
                ", restaurantAddress='" + restaurantAddress + '\'' +
                ", money=" + money +
                ", status=" + status +
                ", imgURL='" + imgURL + '\'' +
                ", studentName='" + studentName + '\'' +
                ", studentBedroom='" + studentBedroom + '\'' +
                ", studentPhoneNum='" + studentPhoneNum + '\'' +
                ", serviceID='" + serviceID + '\'' +
                ", remarks='" + remarks + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TakeOutOrderBean that = (TakeOutOrderBean) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
