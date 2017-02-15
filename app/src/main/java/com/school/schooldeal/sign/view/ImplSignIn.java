package com.school.schooldeal.sign.view;

/**
 * Created by U-nookia on 2017/1/18.
 */

public interface ImplSignIn {
    void finishActivity();

    //获取登录用户名
    String getUserName();
    //获取用户密码
    String getUserPassword();

    void connectRongServer(String token);

    void showDialog(String title,String content);

    void dismissDialog();

    void clearView();

    void showView();

    void clearEdit();
}
