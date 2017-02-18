package com.school.schooldeal.sign.model;

import com.school.schooldeal.model.School;

/**
 * Created by U-nookia on 2017/2/18.
 */
public interface ImplSignUpModel {
    void getSchoolListFromBmob();

    void getApartmentListFromBmob(School school);
}
