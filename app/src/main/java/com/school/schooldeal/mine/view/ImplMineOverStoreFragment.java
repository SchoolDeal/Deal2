package com.school.schooldeal.mine.view;

import com.school.schooldeal.takeout.model.TakeOutDataAdapter;

/**
 * Created by GavynZhang on 2017/2/27 16:24.
 */

public interface ImplMineOverStoreFragment {
    void setAdapter(TakeOutDataAdapter adapter);
    void loadSuccess();
    void loadDataEmpty();
}
