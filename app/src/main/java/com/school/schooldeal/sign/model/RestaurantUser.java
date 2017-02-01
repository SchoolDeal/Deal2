package com.school.schooldeal.sign.model;

import cn.bmob.v3.BmobUser;

/**
 * Created by U-nookia on 2017/2/1.
 */

public class RestaurantUser extends BmobUser {
    private String address;  //地址
    private float longitude; //经度
    private float latitude;  //纬度

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "RestaurantUser{" +
                "address='" + address + '\'' +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                '}';
    }
}
