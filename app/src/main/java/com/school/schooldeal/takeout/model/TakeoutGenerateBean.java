package com.school.schooldeal.takeout.model;

/**
 * Created by GavynZhang on 2017/2/5 0:20.
 */

public class TakeoutGenerateBean {

    private String destination;     //目的地
    private String studentName;     //学生名字
    private String stuPhoneNum;     //外卖收货学生电话
    private String consigneeName;   //收货学生姓名
    private String remarks;         //备注信息
    private int amount;             //数量（份数）
    private float remuneration;     //报酬（每份）

    public String getConsigneeName() {
        return consigneeName;
    }

    public void setConsigneeName(String consigneeName) {
        this.consigneeName = consigneeName;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

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
                ", remarks='" + remarks + '\'' +
                ", amount=" + amount +
                ", remuneration=" + remuneration +
                '}';
    }
}
