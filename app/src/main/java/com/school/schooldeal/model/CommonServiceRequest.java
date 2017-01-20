package com.school.schooldeal.model;

import cn.bmob.v3.BmobObject;

/**
 * Created by GavynZhang on 2017/1/20 18:45.
 * 用于保存非外卖的所有服务请求
 */

public class CommonServiceRequest extends BmobObject {
    private String requestID;       //请求编号
    private String generatedTime;   //生成时间
    private Student student;        //发起请求的学生
    private Store store;            //目的商店
    private String requestContent;  //请求内容
    private String requestRemarks;  //备注信息
    private Float remuneration;     //预期报酬

    public String getGeneratedTime() {
        return generatedTime;
    }

    public void setGeneratedTime(String generatedTime) {
        this.generatedTime = generatedTime;
    }

    public Float getRemuneration() {
        return remuneration;
    }

    public void setRemuneration(Float remuneration) {
        this.remuneration = remuneration;
    }

    public String getRequestContent() {
        return requestContent;
    }

    public void setRequestContent(String requestContent) {
        this.requestContent = requestContent;
    }

    public String getRequestID() {
        return requestID;
    }

    public void setRequestID(String requestID) {
        this.requestID = requestID;
    }

    public String getRequestRemarks() {
        return requestRemarks;
    }

    public void setRequestRemarks(String requestRemarks) {
        this.requestRemarks = requestRemarks;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "CommonServiceRequest{" +
                "generatedTime='" + generatedTime + '\'' +
                ", requestID='" + requestID + '\'' +
                ", student=" + student +
                ", store=" + store +
                ", requestContent='" + requestContent + '\'' +
                ", requestRemarks='" + requestRemarks + '\'' +
                ", remuneration=" + remuneration +
                '}';
    }
}
