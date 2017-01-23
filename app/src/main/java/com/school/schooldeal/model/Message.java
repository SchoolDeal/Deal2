package com.school.schooldeal.model;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by U-nookia on 2017/1/23.
 */

public class Message extends BmobObject {
    private BmobFile head;
    private String content;
    private String time;
    private int leftOrRight;

    public int getLeftOrRight() {
        return leftOrRight;
    }

    public void setLeftOrRight(int leftOrRight) {
        this.leftOrRight = leftOrRight;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public BmobFile getHead() {
        return head;
    }

    public void setHead(BmobFile head) {
        this.head = head;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Message{" +
                "content='" + content + '\'' +
                ", head=" + head +
                ", time='" + time + '\'' +
                ", leftOrRight=" + leftOrRight +
                '}';
    }
}
