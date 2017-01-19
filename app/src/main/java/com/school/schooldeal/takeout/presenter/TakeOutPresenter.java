package com.school.schooldeal.takeout.presenter;

import android.content.Context;

import com.school.schooldeal.takeout.model.TakeOutDataAdapter;
import com.school.schooldeal.takeout.model.ImplTakeOutModel;
import com.school.schooldeal.takeout.model.TakeOutModel;
import com.school.schooldeal.takeout.view.ImplTakeOutFragment;

/**
 * Created by U-nookia on 2016/12/19.
 */

public class TakeOutPresenter {

    private ImplTakeOutFragment takeOutFragment;
    private ImplTakeOutModel takeOutModel;
    private TakeOutDataAdapter adapter;

    public TakeOutPresenter(Context context,ImplTakeOutFragment takeOutFragment) {
        this.takeOutFragment = takeOutFragment;
        takeOutModel = new TakeOutModel();
        adapter = new TakeOutDataAdapter(context);
    }

    public void initAdapter() {
        adapter.setData(takeOutModel.getOrders());
        takeOutFragment.setAdapter(adapter);
    }
}
