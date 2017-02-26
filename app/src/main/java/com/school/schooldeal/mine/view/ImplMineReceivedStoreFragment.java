package com.school.schooldeal.mine.view;

import com.school.schooldeal.takeout.model.TakeOutDataAdapter;

/**
 * Created by GavynZhang on 2017/2/26 20:52.
 */

public interface ImplMineReceivedStoreFragment {
    void setAdapter(TakeOutDataAdapter adapter);
    void loadSuccess();
    void loadDataEmpty();
}
