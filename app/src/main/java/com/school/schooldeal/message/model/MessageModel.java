package com.school.schooldeal.message.model;

import com.school.schooldeal.commen.util.Util;
import com.school.schooldeal.model.ChatInfo;
import com.school.schooldeal.model.Message;
import com.school.schooldeal.model.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by U-nookia on 2017/1/21.
 */

public class MessageModel implements ImplMessageModel {

    private static List<ChatInfo> infos;

    public static List<ChatInfo> getMessageList() {
        infos = new ArrayList<>();
        ChatInfo info = new ChatInfo();
        Student student = new Student();
        student.setUsername("段自成");
        info.setSentStudent(student);
        List<Message> messages = new ArrayList<>();
        Message message = new Message();
        message.setContent("你好");
        message.setLeftOrRight(Util.CONVERSATION_LEFT);
        Message message1 = new Message();
        message1.setContent("你好");
        message1.setLeftOrRight(Util.CONVERSATION_RIGHT);
        Message message2 = new Message();
        message2.setContent("请问是你接的我的订单吗");
        message2.setLeftOrRight(Util.CONVERSATION_LEFT);
        Message message3 = new Message();
        message3.setContent("是的");
        message3.setLeftOrRight(Util.CONVERSATION_RIGHT);
        Message message4 = new Message();
        message4.setContent("我希望可以在中午十二点之前拿到外卖，谢谢");
        message4.setLeftOrRight(Util.CONVERSATION_LEFT);
        Message message9 = new Message();
        message9.setContent("路上顺便帮忙买点饮料，费用另算");
        message9.setLeftOrRight(Util.CONVERSATION_LEFT);
        message9.setTime("09:17");
        Message message10 = new Message();
        message10.setContent("谢谢");
        message10.setLeftOrRight(Util.CONVERSATION_LEFT);
        message10.setTime("19:17");
        messages.add(message);
        messages.add(message1);
        messages.add(message2);
        messages.add(message3);
        messages.add(message4);
        messages.add(message9);
        messages.add(message10);
        info.setMsgContent(messages);
        info.setUnReadMsgNum(1);
        info.setSentTime(messages.get(messages.size()-1).getTime());
        infos.add(info);
        ChatInfo info1 = new ChatInfo();
        Student student1 = new Student();
        student1.setUsername("打发斯蒂芬");
        info1.setSentStudent(student1);
        List<Message> messages1 = new ArrayList<>();
        Message message5 = new Message();
        message5.setContent("大撒发顺丰的");
        message5.setLeftOrRight(Util.CONVERSATION_LEFT);
        Message message6 = new Message();
        message6.setContent("真的？");
        message6.setLeftOrRight(Util.CONVERSATION_RIGHT);
        Message message7 = new Message();
        message7.setContent("大发生的发生发");
        message7.setTime("13:16");
        message7.setLeftOrRight(Util.CONVERSATION_LEFT);
        messages1.add(message5);
        messages1.add(message6);
        messages1.add(message7);
        info1.setMsgContent(messages1);
        info1.setUnReadMsgNum(0);
        infos.add(info1);

        return infos;
    }
}
