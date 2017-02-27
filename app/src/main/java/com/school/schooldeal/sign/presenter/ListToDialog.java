package com.school.schooldeal.sign.presenter;

import com.school.schooldeal.model.Apartment;
import com.school.schooldeal.model.School;

import java.util.List;

/**
 * Created by U-nookia on 2017/2/18.
 */

public interface ListToDialog {
    void setSchoolListToDialog(List<School> schoolList);

    void setApartmentListToDialog(List<Apartment> list);

    void getSchoolOrApartMsgError(String s);
}
