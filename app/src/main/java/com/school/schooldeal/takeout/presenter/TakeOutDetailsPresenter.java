package com.school.schooldeal.takeout.presenter;

import android.content.Context;

import com.school.schooldeal.model.TakeawayRequest;
import com.school.schooldeal.takeout.model.impl.ImplTakeoutDetailsModel;
import com.school.schooldeal.takeout.model.model.TakeoutDetailsModel;
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

    /**
     * 用于Activity加载数据调用
     * @param requestID TakeawayRequest 表的ObjectID
     */
    public void loadTakeawayDetails(String requestID){
        mDetailsModel.loadTakeawayDetails(requestID);
    }

    /**
     * @param takeawayRequest model加载成功的回调
     */
    public void loadTakeawayDetailsSuccess(TakeawayRequest takeawayRequest){
        mDetailsActivity.showDetails(takeawayRequest);
    }

}
