package com.school.schooldeal.takeout.model.model;

import android.content.Context;

import com.school.schooldeal.takeout.model.impl.ImplTakeoutDetailsModel;
import com.school.schooldeal.takeout.presenter.TakeOutDetailsPresenter;

/**
 * Created by GavynZhang on 2017/1/23 0:54.
 */

public class TakeoutDetailsModel implements ImplTakeoutDetailsModel {

    private Context mContext;
    private TakeOutDetailsPresenter mDetailsPresenter;

    public TakeoutDetailsModel(Context context, TakeOutDetailsPresenter detailsPresenter){
        this.mContext = context;
        this.mDetailsPresenter = detailsPresenter;
    }

    @Override
    public void loadPicture() {

    }
}
