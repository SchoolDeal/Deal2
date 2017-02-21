package com.school.schooldeal.sign.model;

import com.school.schooldeal.commen.util.Util;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobGeoPoint;

/**
 * Created by U-nookia on 2017/2/1.
 */

public class RestaurantUser extends BmobUser {
    private String address;  //地址
    private double longitude; //经度
    private double latitude;  //纬度
    private BmobGeoPoint position;  //用于方便查看最近的点
    private String name;     //餐馆名称
    private boolean isStudent;
    private String imgUrl;   //头像url

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public RestaurantUser() {
    }

    public RestaurantUser(String name, String password, String phone, String address, String resName, double latitude, double longtitude, BmobGeoPoint bmobGeoPoint) {
        setUsername(name);
        setPassword(password);
        setMobilePhoneNumber(phone);
        setAddress(address);
        setName(resName);
        setLatitude(latitude);
        setLongitude(longtitude);
        setPosition(bmobGeoPoint);
        setImgUrl(Util.defaultHeadImg);
    }

    public boolean isStudent() {
        return isStudent;
    }

    public void setStudent(boolean student) {
        isStudent = student;
    }

    public BmobGeoPoint getPosition() {
        return position;
    }

    public void setPosition(BmobGeoPoint position) {
        this.position = position;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "RestaurantUser{" +
                "address='" + address + '\'' +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", position=" + position +
                ", name='" + name + '\'' +
                ", isStudent=" + isStudent +
                '}';
    }
}
