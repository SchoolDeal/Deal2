package com.school.schooldeal.takeout.model.impl;

import com.school.schooldeal.model.School;
import com.school.schooldeal.model.TakeawayRequest;

/**
 * Created by GavynZhang on 2017/2/4 23:20.
 */

public interface ImplTakeoutGenerateModel {
    void saveToBmob(TakeawayRequest serviceRequest);
    void loadSchoolData();
    void loadApartmentData(School school);
}
