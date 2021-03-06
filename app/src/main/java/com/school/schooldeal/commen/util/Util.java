package com.school.schooldeal.commen.util;

import android.content.Context;

import com.school.schooldeal.R;
import com.school.schooldeal.sign.model.SchoolData;

import java.io.CharArrayReader;
import java.util.Arrays;

/**
 * Created by U-nookia on 2017/1/23.
 */

public class Util {
    public static boolean IS_STUDENT = false;
    public static final int CONVERSATION_LEFT = 1;
    public static final int CONVERSATION_RIGHT = 2;
    public static boolean FIRST_TIME_SIGNIN = true;

    public static String token_10086 = "nPQezcD7+5dl8Zi9aqJcPVCQ0kQwNttiVMuh9nCDrJ57B2Ngwb4yGwnd+J9YRjLpXLYT6/rCmOJ4fEb4yIaxmvqzN+8JbP2g";
    public static String token_hhh = "5CE3CaknyLsOZP/MoLckR0D0jcgTjKydEYEt1a5FoJo1zVWNCJNLYUQBH5XurmKlugJtXUiUK0SSKDMeLoTfDmjUT53BbNzu";
    public static String id_10086 = "ea9c412700";
    public static String id_hhh = "bd01ae9f81";
    public static String img_hhh = "http://static.yingyonghui.com/screenshots/1657/1657011_4.jpg";
    public static String img_10086 = "http://i5.hexunimg.cn/2012-11-07/147694350.jpg";
    public static String defaultHeadImg = "http://bmob-cdn-9205.b0.upaiyun.com/2017/02/21/88d0734d04e0432f90aa06f722904f5e.jpg";

    public static final String order_release = "发布订单";
    public static final String order_receive = "接收订单";
    public static final String order_finish = "完成订单";
    public static final String fed_back = "反馈错误";
    public static final String customer_service = "联系客服";
    public static final String about_us = "关于我们";
    public static final String version_up = "版本更新";

    public static String[] getApartmentArray(Context context,int school){
        switch (school){
            case SchoolData.CQUPT:
                return context.getResources().getStringArray(R.array.cqupt_apart);
            case SchoolData.CQU:
                return context.getResources().getStringArray(R.array.cqu_apart);
            case SchoolData.CQUMT:
                return context.getResources().getStringArray(R.array.cqmu_apart);
            case SchoolData.SU:
                return context.getResources().getStringArray(R.array.su_apart);
            default:return null;
        }
    }
}
