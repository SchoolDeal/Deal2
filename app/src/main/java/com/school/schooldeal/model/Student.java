package com.school.schooldeal.model;

import com.school.schooldeal.sign.model.User;

import cn.bmob.v3.BmobUser;

/**
 * Created by GavynZhang on 2017/1/20 18:22.
 */

public class Student extends BmobUser {

    private String studentID;       //ID
    private School school;          //对应学校
    private Apartment apartment;    //对应公寓
    private String name;            //名字
    private String sex;             //性别
    private String phoneNum;        //电话
    private Integer credibility;    //信誉度

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return "Student{" +
                ", studentID='" + studentID + '\'' +
                ", school=" + school +
                ", apartment=" + apartment +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", credibility=" + credibility +
                '}';
    }
}
