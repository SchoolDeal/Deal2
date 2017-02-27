package com.school.schooldeal.model;

import java.io.Serializable;

import cn.bmob.v3.BmobObject;

/**
 * Created by GavynZhang on 2017/1/20 18:09.
 *
 * 用于外卖之外的其他目的地，如食堂，商店，餐馆
 */

public class Store extends BmobObject implements Serializable{

    private String storeID;
    private int storeType;      //店铺类型
    private String storeName;   //店名
    private String storeAddress;//地址
    private String longitude;   //经度
    private String latitude;    //纬度
    private String phoneNum;    //联系电话

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }

    public String getStoreID() {
        return storeID;
    }

    public void setStoreID(String storeID) {
        this.storeID = storeID;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public int getStoreType() {
        return storeType;
    }

    public void setStoreType(int storeType) {
        this.storeType = storeType;
    }

    @Override
    public String toString() {
        return "Store{" +
                "latitude='" + latitude + '\'' +
                ", storeID='" + storeID + '\'' +
                ", storeType=" + storeType +
                ", storeName='" + storeName + '\'' +
                ", storeAddress='" + storeAddress + '\'' +
                ", longitude='" + longitude + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                '}';
    }
}
