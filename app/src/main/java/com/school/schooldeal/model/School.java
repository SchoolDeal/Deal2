package com.school.schooldeal.model;

import cn.bmob.v3.BmobObject;

/**
 * Created by GavynZhang on 2017/1/20 18:06.
 */

public class School extends BmobObject {
    private String schoolID;
    private String schoolName;
    private String schoolAddress;
    private String longitude;    //经度
    private String latitude;     //纬度

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getSchoolAddress() {
        return schoolAddress;
    }

    public void setSchoolAddress(String schoolAddress) {
        this.schoolAddress = schoolAddress;
    }

    public String getSchoolID() {
        return schoolID;
    }

    public void setSchoolID(String schoolID) {
        this.schoolID = schoolID;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    @Override
    public String toString() {
        return "School{" +
                "latitude=" + latitude +
                ", schoolID='" + schoolID + '\'' +
                ", schoolName='" + schoolName + '\'' +
                ", schoolAddress='" + schoolAddress + '\'' +
                ", longitude=" + longitude +
                '}';
    }
}
