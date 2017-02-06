package com.school.schooldeal.takeout.presenter;

import android.content.Context;

import com.school.schooldeal.commen.util.TimeUtils;
import com.school.schooldeal.model.TakeawayServiceRequest;
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
    private ImplTakeoutGenerateActivity mGenerateActivity;
    private ImplTakeoutGenerateModel mGenerateModel;
    private TakeawayServiceRequest mTakeawayServiceRequest;
    private Context mContext;

    public TakeoutGeneratePresenter(ImplTakeoutGenerateActivity generateActivity, Context context) {
        this.mContext = context;
        this.mGenerateActivity = generateActivity;
        mGenerateModel = new TakeoutGenerateModel(context);
    }

    public void generateTakeawayServiceRequest(TakeoutGenerateBean generateBean){

        mTakeawayServiceRequest = new TakeawayServiceRequest();
        mTakeawayServiceRequest.setRemuneration(generateBean.getRemuneration());
        mTakeawayServiceRequest.setGeneratedTime(TimeUtils.getCurTimeString());
        if (!generateBean.getRemarks().equals(""))
            mTakeawayServiceRequest.setRemarks(generateBean.getRemarks());
        mTakeawayServiceRequest.setDestination(generateBean.getDestination());
        mTakeawayServiceRequest.setAmount(generateBean.getAmount());
        mTakeawayServiceRequest.setRestaurant(BmobUser.getCurrentUser(mContext, RestaurantUser.class));

        mGenerateModel.saveToBmob(mTakeawayServiceRequest);
    }

}
