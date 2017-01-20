package com.school.schooldeal.model;

import cn.bmob.v3.BmobObject;

/**
 * Created by GavynZhang on 2017/1/20 18:52.
 * 服务请求被学生接收之后生成服务单
 */

public class CommonService extends BmobObject {
    private String serviceID;   //服务单号
    private Student student;
    private CommonServiceRequest request;
    private Boolean isFinish;
    private Float remuneration;

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

    public CommonServiceRequest getRequest() {
        return request;
    }

    public void setRequest(CommonServiceRequest request) {
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
        return "CommonService{" +
                "isFinish=" + isFinish +
                ", serviceID='" + serviceID + '\'' +
                ", student=" + student +
                ", request=" + request +
                ", remuneration=" + remuneration +
                '}';
    }
}
