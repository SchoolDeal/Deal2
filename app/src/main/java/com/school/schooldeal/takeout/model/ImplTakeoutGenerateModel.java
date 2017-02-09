package com.school.schooldeal.takeout.model;

import com.school.schooldeal.model.TakeawayRequest;

/**
 * Created by GavynZhang on 2017/2/4 23:20.
 */

public interface ImplTakeoutGenerateModel {
    void saveToBmob(TakeawayRequest serviceRequest);
}
