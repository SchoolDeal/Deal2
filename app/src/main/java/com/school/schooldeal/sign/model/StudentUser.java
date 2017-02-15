package com.school.schooldeal.sign.model;

import cn.bmob.v3.BmobUser;

/**
 * Created by U-nookia on 2016/12/22.
 * 用户bean
 */

public class StudentUser extends BmobUser{
    private int creditScore;   //信用分
    private int sex;   //性别
    private String schoolNumber;  //学号
    private int school;          //学校编号
    private int apartment;       //公寓编号
    private boolean isStudent;
    public boolean isStudent() {
        return isStudent;
    }
    public void setStudent(boolean student) {
        isStudent = student;
    }

    public int getApartment() {
        return apartment;
    }

    public void setApartment(int apartment) {
        this.apartment = apartment;
    }

    public int getCreditScore() {
        return creditScore;
    }

    public void setCreditScore(int creditScore) {
        this.creditScore = creditScore;
    }

    public int getSchool() {
        return school;
    }

    public void setSchool(int school) {
        this.school = school;
    }

    public String getSchoolNumber() {
        return schoolNumber;
    }

    public void setSchoolNumber(String schoolNumber) {
        this.schoolNumber = schoolNumber;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "StudentUser{" +
                "apartment=" + apartment +
                ", creditScore=" + creditScore +
                ", sex=" + sex +
                ", schoolNumber='" + schoolNumber + '\'' +
                ", school=" + school +
                '}';
    }
}
