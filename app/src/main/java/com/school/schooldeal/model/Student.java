package com.school.schooldeal.model;

import java.io.Serializable;

import cn.bmob.v3.BmobUser;

/**
 * Created by GavynZhang on 2017/1/20 18:22.
 */

public class Student extends BmobUser implements Serializable{

    private String studentID;       //ID
    private School school;          //对应学校
    private Apartment apartment;    //对应公寓
    private String sex;             //性别
    private String phoneNum;        //电话
    private Integer credibility;    //信誉度
    private String bedroom;
    private String imgUrl;  //头像url

    public Apartment getApartment() {
        return apartment;
    }

    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
    }

    public Integer getCredibility() {
        return credibility;
    }

    public void setCredibility(Integer credibility) {
        this.credibility = credibility;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getBedroom() {
        return bedroom;
    }

    public void setBedroom(String bedroom) {
        this.bedroom = bedroom;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentID='" + studentID + '\'' +
                ", school=" + school +
                ", apartment=" + apartment +
                ", sex='" + sex + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", credibility=" + credibility +
                ", bedroom='" + bedroom + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                '}';
    }
}
