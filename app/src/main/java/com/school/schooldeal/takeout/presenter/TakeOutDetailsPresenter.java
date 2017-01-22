package com.school.schooldeal.takeout.presenter;

import android.content.Context;

import com.school.schooldeal.takeout.model.ImplTakeoutDetailsModel;
import com.school.schooldeal.takeout.model.TakeoutDetailsModel;
import com.school.schooldeal.takeout.view.ImplTakeoutDetailsActivity;

/**
 * Created by GavynZhang on 2017/1/23 0:52.
 */

public class TakeOutDetailsPresenter {
    private Context mContext;
    private ImplTakeoutDetailsActivity mDetailsActivity;
    private ImplTakeoutDetailsModel mDetailsModel;

    public TakeOutDetailsPresenter(Context context, ImplTakeoutDetailsActivity takeoutDetailsActivity){
        this.mContext = context;
        this.mDetailsActivity = takeoutDetailsActivity;
        mDetailsModel = new TakeoutDetailsModel(context,this);
    }

    public void loadRestaurantPicture(){

    }
}
