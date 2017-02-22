package com.school.schooldeal.takeout;

/**
 * Created by GavynZhang on 2017/2/22 23:28.
 */

public class TakeawayStatusConsts {
    //订单未被接下
    public static final Integer NOT_BEING_TAKEN = 0;
    //订单已被接下，但还未完成
    public static final Integer HAS_BEING_TAKEN = 1;
    //订单被取消
    public static final Integer CANCELLED = 2;
    //订单已完成
    public static final Integer COMPLETED = 3;
}
