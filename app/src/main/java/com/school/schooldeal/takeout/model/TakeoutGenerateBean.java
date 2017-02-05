package com.school.schooldeal.takeout.model;

/**
 * Created by GavynZhang on 2017/2/5 0:20.
 */

public class TakeoutGenerateBean {

    private String destination;
    private String studentName;
    private String stuPhoneNum;
    private int amount;
    private float remuneration;

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getStuPhoneNum() {
        return stuPhoneNum;
    }

    public void setStuPhoneNum(String stuPhoneNum) {
        this.stuPhoneNum = stuPhoneNum;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public float getRemuneration() {
        return remuneration;
    }

    public void setRemuneration(float remuneration) {
        this.remuneration = remuneration;
    }

    @Override
    public String toString() {
        return "TakeoutGenerateBean{" +
                "destination='" + destination + '\'' +
                ", studentName='" + studentName + '\'' +
                ", stuPhoneNum='" + stuPhoneNum + '\'' +
                ", amount=" + amount +
                ", remuneration=" + remuneration +
                '}';
    }
}
