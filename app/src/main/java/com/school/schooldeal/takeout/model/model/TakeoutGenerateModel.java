package com.school.schooldeal.takeout.model.model;

import android.content.Context;
import android.util.Log;

import com.school.schooldeal.commen.util.ToastUtil;
import com.school.schooldeal.model.Apartment;
import com.school.schooldeal.model.School;
import com.school.schooldeal.model.TakeawayRequest;
import com.school.schooldeal.takeout.model.impl.ImplTakeoutGenerateModel;
import com.school.schooldeal.takeout.presenter.TakeoutGeneratePresenter;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobPointer;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by GavynZhang on 2017/2/6 18:41.
 */

public class TakeoutGenerateModel implements ImplTakeoutGenerateModel {

    private static final String className = "TGModel";

    private Context mContext;
    private TakeoutGeneratePresenter mGeneratePresenter;

    public TakeoutGenerateModel(TakeoutGeneratePresenter presenter , Context context) {
        mContext = context;
        mGeneratePresenter = presenter;
    }

    /**
     * 保存填写的数据到Bmob
     * @param serviceRequest
     */
    @Override
    public void saveToBmob(final TakeawayRequest serviceRequest) {
        serviceRequest.save(mContext, new SaveListener() {
            @Override
            public void onSuccess() {
                //ToastUtil.makeShortToast(mContext, "Save to Bmob ok!!!");
                mGeneratePresenter.saveToBmobSuccess(serviceRequest.getObjectId());

            }

            @Override
            public void onFailure(int i, String s) {
                //ToastUtil.makeShortToast(mContext, "Save Fail, i: "+i+" String: "+s);
                Log.e(className, "Save fail, error: "+i+" message: "+s);
                mGeneratePresenter.saveRequestToBmobFail(i, s);
            }
        });
    }

    /**
     * 加载学校的数据
     */
    @Override
    public void loadSchoolData() {
        BmobQuery<School> schoolBmobQuery = new BmobQuery<>();
        schoolBmobQuery.findObjects(mContext, new FindListener<School>() {
            @Override
            public void onSuccess(List<School> list) {
                mGeneratePresenter.loadSchoolSuccess(list);
            }

            @Override
            public void onError(int i, String s) {
                mGeneratePresenter.loadSchoolFail(i, s);
            }
        });
    }

    /**
     * 加载公寓的数据
     * @param school 所要查找公寓的学校
     */
    @Override
    public void loadApartmentData(School school) {
        Log.d(className, "school: "+school.toString());
        BmobQuery<Apartment> apartmentBmobQuery = new BmobQuery<>();
//        School school = new School();
//        school.setObjectId(schoolObjectID);
        apartmentBmobQuery.addWhereEqualTo("school", school);
        apartmentBmobQuery.include("school");
        apartmentBmobQuery.findObjects(mContext, new FindListener<Apartment>() {
            @Override
            public void onSuccess(List<Apartment> list) {
                mGeneratePresenter.loadApartmentSuccess(list);
            }

            @Override
            public void onError(int i, String s) {
                mGeneratePresenter.loadSchoolFail(i, s);
            }
        });
    }


}
