package com.school.schooldeal.takeout.presenter;

import android.content.Context;

import com.school.schooldeal.takeout.model.TakeOutDataAdapter;
import com.school.schooldeal.takeout.model.ImplTakeOutModel;
import com.school.schooldeal.takeout.model.TakeOutFragmentModel;
import com.school.schooldeal.takeout.view.ImplTakeOutFragment;

/**
 * Created by U-nookia on 2016/12/19.
 */

public class TakeOutFragmentPresenter {

    private ImplTakeOutFragment takeOutFragment;
    private ImplTakeOutModel takeOutModel;
    private TakeOutDataAdapter adapter;

    public TakeOutFragmentPresenter(Context context, ImplTakeOutFragment takeOutFragment) {
        this.takeOutFragment = takeOutFragment;
        takeOutModel = new TakeOutFragmentModel(context);
        adapter = new TakeOutDataAdapter(context);
    }

    public void initAdapter() {
        adapter.setData(takeOutModel.getOrders());
        takeOutFragment.setAdapter(adapter);
    }
}
