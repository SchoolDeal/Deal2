package com.school.schooldeal.takeout.presenter;

import android.content.Context;

import com.school.schooldeal.commen.util.ToastUtil;
import com.school.schooldeal.takeout.model.TakeOutDataAdapter;
import com.school.schooldeal.takeout.model.bean.TakeOutOrderBean;
import com.school.schooldeal.takeout.model.impl.ImplTakeOutFragmentModel;
import com.school.schooldeal.takeout.model.model.TakeOutFragmentModel;
import com.school.schooldeal.takeout.view.ImplTakeOutFragment;

import java.util.List;

/**
 * Created by U-nookia on 2016/12/19.
 */

public class TakeOutFragmentPresenter {
    private static final String className = "TOFPresenter";

    private ImplTakeOutFragment takeOutFragment;
    private ImplTakeOutFragmentModel takeOutModel;
    private TakeOutDataAdapter adapter;
    private Context mContext;

    public TakeOutFragmentPresenter(Context context, ImplTakeOutFragment takeOutFragment) {
        this.takeOutFragment = takeOutFragment;
        takeOutModel = new TakeOutFragmentModel(this, context);
        adapter = new TakeOutDataAdapter(context);
        mContext = context;
    }

    public void initAdapter() {
        //adapter.setData(takeOutModel.getOrders());
        takeOutModel.loadOrders();
    }

    public void loadOrdersSuccess(List<TakeOutOrderBean> orders){
        adapter.setData(orders);
        takeOutFragment.setAdapter(adapter);
    }
}
