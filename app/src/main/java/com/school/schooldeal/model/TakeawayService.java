package com.school.schooldeal.model;

import cn.bmob.v3.BmobObject;

/**
 * Created by GavynZhang on 2017/1/20 19:03.
 */

public class TakeawayService extends BmobObject {
    private String serviceID;               //外卖服务单ID
    private Student student;                //送外卖的学生
    private TakeawayServiceRequest request; //对应的送外卖的请求
    private Boolean isFinish;               //是否完成
    private String finishTime;              //完成时间
    private Float remuneration;             //报酬
    private Integer number;                 //份数

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }

    public Boolean getFinish() {
        return isFinish;
    }

    public void setFinish(Boolean finish) {
        isFinish = finish;
    }

    public Float getRemuneration() {
        return remuneration;
    }

    public void setRemuneration(Float remuneration) {
        this.remuneration = remuneration;
    }

    public TakeawayServiceRequest getRequest() {
        return request;
    }

    public void setRequest(TakeawayServiceRequest request) {
        this.request = request;
    }

    public String getServiceID() {
        return serviceID;
    }

    public void setServiceID(String serviceID) {
        this.serviceID = serviceID;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "TakeawayService{" +
                "finishTime='" + finishTime + '\'' +
                ", serviceID='" + serviceID + '\'' +
                ", student=" + student +
                ", request=" + request +
                ", isFinish=" + isFinish +
                ", remuneration=" + remuneration +
                ", number=" + number +
                '}';
    }
}
