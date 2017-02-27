package com.school.schooldeal.takeout.view;

import java.util.List;

/**
 * Created by GavynZhang on 2017/2/5 0:14.
 */

public interface ImplTakeoutGenerateActivity {
    void saveSuccess();
    void saveFail();
    void loadSchoolSuccess(List<String> schools);
    void loadApartmentSuccess(List<String> apartments);
}
