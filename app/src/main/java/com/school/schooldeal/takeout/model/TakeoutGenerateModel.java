package com.school.schooldeal.takeout.model;

import android.content.Context;

import com.school.schooldeal.commen.util.ToastUtil;
import com.school.schooldeal.model.TakeawayRequest;

import cn.bmob.v3.listener.SaveListener;

/**
 * Created by GavynZhang on 2017/2/6 18:41.
 */

public class TakeoutGenerateModel implements ImplTakeoutGenerateModel {

    private Context mContext;

    public TakeoutGenerateModel(Context context) {
        mContext = context;
    }

    @Override
    public void saveToBmob(TakeawayRequest serviceRequest) {
        serviceRequest.save(mContext, new SaveListener() {
            @Override
            public void onSuccess() {
                ToastUtil.makeShortToast(mContext, "Save to Bmob ok!!!");
            }

            @Override
            public void onFailure(int i, String s) {
                ToastUtil.makeShortToast(mContext, "Save Fail, i: "+i+" String: "+s);
            }
        });
    }
}
