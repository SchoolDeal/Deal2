package com.school.schooldeal.sign.view;

/**
 * Created by U-nookia on 2017/2/18.
 */

public interface ImplSignUp {
    void setEdit();

    void showRestaurantSignUpView();

    String getAddress();

    String getPhone();

    String getEmail();

    String getName();

    String getPassword();

    String getSchoolNumber();

    String getBedroom();


    void finishView();

    void setSex(CharSequence text);

    void setSchool(CharSequence text);

    void setApartment(CharSequence text);

    void setBedroom(CharSequence text);


    void setAddress(String address);

    void showProgressDialog(String title, String content);

    void dismissProgressDialog();

    void hideClickSchoolSoftInput();

    void hideClicApartSoftInput();
}
