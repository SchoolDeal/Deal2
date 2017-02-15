package com.school.schooldeal.takeout.presenter;

import android.content.Context;

import com.school.schooldeal.takeout.model.TakeOutDataAdapter;
import com.school.schooldeal.takeout.model.impl.ImplTakeOutFragmentModel;
import com.school.schooldeal.takeout.model.model.TakeOutFragmentModel;
import com.school.schooldeal.takeout.view.ImplTakeOutFragment;

/**
 * Created by U-nookia on 2016/12/19.
 */

public class TakeOutFragmentPresenter {
    private static final String className = "TOFPresenter";

    private ImplTakeOutFragment takeOutFragment;
    private ImplTakeOutFragmentModel takeOutModel;
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
