package com.school.schooldeal.takeout.presenter;

import com.school.schooldeal.model.TakeawayServiceRequest;
import com.school.schooldeal.takeout.model.TakeoutGenerateBean;
import com.school.schooldeal.takeout.view.ImplTakeoutGenerateActivity;

/**
 * Created by GavynZhang on 2017/2/4 23:19.
 */

public class TakeoutGeneratePresenter {
    private ImplTakeoutGenerateActivity mGenerateActivity;
    private TakeawayServiceRequest mTakeawayServiceRequest;

    public TakeoutGeneratePresenter(ImplTakeoutGenerateActivity generateActivity) {
        this.mGenerateActivity = generateActivity;
    }

    public void generateTakeawayServiceRequest(TakeoutGenerateBean generateBean){
        mTakeawayServiceRequest = new TakeawayServiceRequest();
        mTakeawayServiceRequest.setRemuneration(generateBean.getRemuneration());
        //mTakeawayServiceRequest.setGeneratedTime();
    }

}
