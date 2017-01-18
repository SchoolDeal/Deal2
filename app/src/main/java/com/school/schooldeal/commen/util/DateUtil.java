package com.school.schooldeal.commen.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by U-nookia on 2016/12/23.
 * 日期工具包
 */

public class DateUtil {

    public static long dateToStamp(Date date){
        return date.getTime();
    }

    public static Date stampToDate(long time){
        Date date = new Date(time);
        return date;
    }

    public static String stampToSimpleDate(long time){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        String simpleDate = dateFormat.format(stampToDate(time));
        return simpleDate;
    }

    public static String dateToSimpleDate(Date date){
        return stampToSimpleDate(dateToStamp(date));
    }

    public static Long SimpleDateToStamp(String simpleDate){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long stamp = 0;
        try {
            Date date = dateFormat.parse(simpleDate);
            stamp = dateToStamp(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return stamp;
    }
}
