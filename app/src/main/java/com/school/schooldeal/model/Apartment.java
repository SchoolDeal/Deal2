package com.school.schooldeal.model;

import cn.bmob.v3.BmobObject;

/**
 * Created by GavynZhang on 2017/1/20 18:18.
 */

public class Apartment extends BmobObject {
    private String ApartmentID;     //公寓编号
    private School school;          //对应学校
    private String ApartmentName;   //公寓名称

    public String getApartmentID() {
        return ApartmentID;
    }

    public void setApartmentID(String apartmentID) {
        ApartmentID = apartmentID;
    }

    public String getApartmentName() {
        return ApartmentName;
    }

    public void setApartmentName(String apartmentName) {
        ApartmentName = apartmentName;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    @Override
    public String toString() {
        return "Apartment{" +
                "ApartmentID='" + ApartmentID + '\'' +
                ", school=" + school +
                ", ApartmentName='" + ApartmentName + '\'' +
                '}';
    }
}
