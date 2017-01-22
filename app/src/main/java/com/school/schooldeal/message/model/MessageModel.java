package com.school.schooldeal.message.model;

import com.school.schooldeal.model.ChatInfo;

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
        info.setMsgID("乐乐");
        info.setMsgContent("牙刷买到之后可以过几天在拿过来这边不急着用");
        info.setUnReadMsgNum(1);
        info.setSentTime("09:17");
        infos.add(info);
        ChatInfo info1 = new ChatInfo();
        info1.setMsgID("大名");
        info1.setMsgContent("阿达撒发傻傻的噶啥打法阿斯顿发斯蒂芬啊啊士大夫");
        info1.setSentTime("07:26");
        info1.setUnReadMsgNum(0);
        infos.add(info1);
        ChatInfo info2 = new ChatInfo();
        info2.setMsgID("乐乐");
        info2.setMsgContent("牙刷买到之后可以过几天在拿过来这边不急着用");
        info2.setSentTime("13:17");
        info2.setUnReadMsgNum(0);
        infos.add(info2);
        ChatInfo info3 = new ChatInfo();
        info3.setMsgID("dongdong");
        info3.setMsgContent("牙刷买到之后可以过几天在拿过来这边不急着用");
        info3.setSentTime("09:24");
        info3.setUnReadMsgNum(2);
        infos.add(info3);
        ChatInfo info4 = new ChatInfo();
        info4.setMsgID("sadfa");
        info4.setMsgContent("牙刷买到之后可以过几天在拿过来这边不急着用");
        info4.setSentTime("15:17");
        info4.setUnReadMsgNum(0);
        infos.add(info4);
        ChatInfo info5 = new ChatInfo();
        info5.setMsgID("kjlj ");
        info5.setMsgContent("牙刷买到之后可以过几天在拿过来这边不急着用");
        info5.setSentTime("09:17");
        info5.setUnReadMsgNum(0);
        infos.add(info5);
        ChatInfo info6 = new ChatInfo();
        info6.setMsgID("乐乐");
        info6.setMsgContent("牙刷买到之后可以过几天在拿过来这边不急着用");
        info6.setSentTime("09:17");
        info6.setUnReadMsgNum(0);
        infos.add(info6);
        ChatInfo info7 = new ChatInfo();
        info7.setMsgID("dasf");
        info7.setMsgContent("牙刷买到之后可以过几天在拿过来这边不急着用");
        info7.setSentTime("09:17");
        info7.setUnReadMsgNum(0);
        infos.add(info7);
        ChatInfo info8 = new ChatInfo();
        info8.setMsgID("huahuahua");
        info8.setMsgContent("牙刷买到之后可以过几天在拿过来这边不急着用");
        info8.setSentTime("09:17");
        info8.setUnReadMsgNum(0);
        infos.add(info8);
        ChatInfo info9 = new ChatInfo();
        info9.setMsgID("旭东");
        info9.setMsgContent("牙刷买到之后可以过几天在拿过来这边不急着用");
        info9.setSentTime("09:17");
        info9.setUnReadMsgNum(0);
        infos.add(info9);
        ChatInfo info10 = new ChatInfo();
        info10.setMsgID("画画");
        info10.setMsgContent("牙刷买到之后可以过几天在拿过来这边不急着用");
        info10.setSentTime("09:17");
        info10.setUnReadMsgNum(0);
        infos.add(info10);
        ChatInfo info11 = new ChatInfo();
        info11.setMsgID("指针");
        info11.setMsgContent("牙刷买到之后可以过几天在拿过来这边不急着用");
        info11.setSentTime("09:17");
        info11.setUnReadMsgNum(0);
        infos.add(info11);
        return infos;
    }
}
