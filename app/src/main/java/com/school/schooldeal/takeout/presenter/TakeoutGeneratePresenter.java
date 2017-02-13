package com.school.schooldeal.takeout.presenter;

import android.content.Context;
import android.util.Log;

import com.school.schooldeal.commen.util.TimeUtils;
import com.school.schooldeal.model.TakeawayRequest;
import com.school.schooldeal.sign.model.RestaurantUser;
import com.school.schooldeal.takeout.model.ImplTakeoutGenerateModel;
import com.school.schooldeal.takeout.model.TakeoutGenerateBean;
import com.school.schooldeal.takeout.model.TakeoutGenerateModel;
import com.school.schooldeal.takeout.view.ImplTakeoutGenerateActivity;

import cn.bmob.v3.BmobUser;

/**
 * Created by GavynZhang on 2017/2/4 23:19.
 */

public class TakeoutGeneratePresenter {
    public static final String className = "TGPresenter";

    private ImplTakeoutGenerateActivity mGenerateActivity;
    private ImplTakeoutGenerateModel mGenerateModel;
    private TakeawayRequest mTakeawayRequest;
    private Context mContext;

    public TakeoutGeneratePresenter(ImplTakeoutGenerateActivity generateActivity, Context context) {
        this.mContext = context;
        this.mGenerateActivity = generateActivity;
        mGenerateModel = new TakeoutGenerateModel(context);
    }

    public void generateTakeawayServiceRequest(TakeoutGenerateBean generateBean){
        Log.d(className, "On Presenter, generateTakeawayServiceRequest");

        mTakeawayRequest = new TakeawayRequest();
        mTakeawayRequest.setRemuneration(generateBean.getRemuneration());
        mTakeawayRequest.setGeneratedTime(TimeUtils.getCurTimeString());
        if (!generateBean.getRemarks().equals(""))
            mTakeawayRequest.setRemarks(generateBean.getRemarks());
        mTakeawayRequest.setDestination(generateBean.getDestination());
        mTakeawayRequest.setAmount(generateBean.getAmount());
        mTakeawayRequest.setRestaurant(BmobUser.getCurrentUser(mContext, RestaurantUser.class));
        mTakeawayRequest.setConsigneeName(generateBean.getStudentName());
        mGenerateModel.saveToBmob(mTakeawayRequest);
    }

}
