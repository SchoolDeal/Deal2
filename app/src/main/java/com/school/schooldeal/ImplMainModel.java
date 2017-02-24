package com.school.schooldeal;

import java.util.List;

import cn.bmob.v3.BmobUser;

/**
 * Created by U-nookia on 2017/2/24.
 */

public interface ImplMainModel {
    void getUserListFromBmob();

    List<BmobUser> getUserList();

    BmobUser queryUser(String id);
}
