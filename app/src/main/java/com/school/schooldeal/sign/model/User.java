package com.school.schooldeal.sign.model;

import cn.bmob.v3.BmobUser;

/**
 * Created by U-nookia on 2016/12/22.
 * 用户bean
 */

public class User extends BmobUser{
    private int grade;   //等级
    private int creditScore;   //信用分
    private boolean sex;   //性别
}
