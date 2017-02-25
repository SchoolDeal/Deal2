package com.school.schooldeal.sign.view;

/**
 * Created by U-nookia on 2017/2/18.
 */

public interface ImplSignUp {
    void setEdit();

    void showRestaurantSignUpView();

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


}
