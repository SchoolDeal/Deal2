package com.school.schooldeal.model;

import java.io.Serializable;
import java.util.List;

import cn.bmob.v3.BmobObject;

/**
 * Created by GavynZhang on 2017/1/20 19:09.
 */

public class ChatInfo extends BmobObject implements Serializable{
    private String msgID;           //消息ID
    private List<String> msgContent;      //消息内容
    private String sentTime;        //消息发送时间
    private Student sentStudent;    //发消息的学生
    private Student receiveStudent; //收消息的学生
    private int unReadMsgNum;       //未读的消息数

    public List<String> getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(List<String> msgContent) {
        this.msgContent = msgContent;
    }

    public String getMsgID() {
        return msgID;
    }

    public void setMsgID(String msgID) {
        this.msgID = msgID;
    }

    public Student getReceiveStudent() {
        return receiveStudent;
    }

    public void setReceiveStudent(Student receiveStudent) {
        this.receiveStudent = receiveStudent;
    }

    public Student getSentStudent() {
        return sentStudent;
    }

    public void setSentStudent(Student sentStudent) {
        this.sentStudent = sentStudent;
    }

    public String getSentTime() {
        return sentTime;
    }

    public void setSentTime(String sentTime) {
        this.sentTime = sentTime;
    }

    public int getUnReadMsgNum() {
        return unReadMsgNum;
    }

    public void setUnReadMsgNum(int unReadMsgNum) {
        this.unReadMsgNum = unReadMsgNum;
    }

    @Override
    public String toString() {
        return "ChatInfo{" +
                "msgContent=" + msgContent +
                ", msgID='" + msgID + '\'' +
                ", sentTime='" + sentTime + '\'' +
                ", sentStudent=" + sentStudent +
                ", receiveStudent=" + receiveStudent +
                ", unReadMsgNum=" + unReadMsgNum +
                '}';
    }
}
