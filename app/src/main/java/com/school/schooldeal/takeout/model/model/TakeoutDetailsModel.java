package com.school.schooldeal.takeout.model.model;

import android.content.Context;
import android.util.Log;

import com.school.schooldeal.model.TakeawayRequest;
import com.school.schooldeal.takeout.model.impl.ImplTakeoutDetailsModel;
import com.school.schooldeal.takeout.presenter.TakeOutDetailsPresenter;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.GetListener;

/**
 * Created by GavynZhang on 2017/1/23 0:54.
 */

public class TakeoutDetailsModel implements ImplTakeoutDetailsModel {

    private static final String className = "TODetailsModel";

    private Context mContext;
    private TakeOutDetailsPresenter mDetailsPresenter;

    public TakeoutDetailsModel(Context context, TakeOutDetailsPresenter detailsPresenter){
        this.mContext = context;
        this.mDetailsPresenter = detailsPresenter;
    }

    @Override
    public void loadPicture() {

    }

    @Override
    public void loadTakeawayDetails(String requestID) {
        Log.d(className, "requestID: "+requestID);
        BmobQuery<TakeawayRequest> takeawayRequestBmobQuery = new BmobQuery<>();
        takeawayRequestBmobQuery.include("restaurant,apartment");
        takeawayRequestBmobQuery.getObject(mContext, requestID, new GetListener<TakeawayRequest>() {
            @Override
            public void onSuccess(TakeawayRequest takeawayRequest) {
                Log.d(className, "takeawayRequest: "+takeawayRequest.toString());
                mDetailsPresenter.loadTakeawayDetailsSuccess(takeawayRequest);
            }

            @Override
            public void onFailure(int i, String s) {

            }
        });
    }
}
