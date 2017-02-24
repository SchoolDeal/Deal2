package com.school.schooldeal;

/**
 * Created by U-nookia on 2017/2/24.
 */

public interface ImplMainActivity {
    void showProgressDialog(String title, String content);

    void dismissProgressDialog();

    void startChat(String id, String name);
}
