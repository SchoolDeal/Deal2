package com.school.schooldeal.takeout.model.model;

import android.content.Context;
import android.util.Log;

import com.school.schooldeal.model.TakeawayRequest;
import com.school.schooldeal.model.TakeawayService;
import com.school.schooldeal.sign.model.StudentUser;
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
    private String requestObjectID;

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
        this.requestObjectID = requestID;
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

    @Override
    public void loadStudentInfo() {
        BmobQuery<TakeawayService> serviceBmobQuery = new BmobQuery<>();
        TakeawayRequest request = new TakeawayRequest();
        request.setObjectId(requestObjectID);
        serviceBmobQuery.addWhereEqualTo("request",request);
        serviceBmobQuery.include("student");
        serviceBmobQuery.findObjects(mContext, new FindListener<TakeawayService>() {
            @Override
            public void onSuccess(List<TakeawayService> list) {
                Log.d(className,"请求的Service: "+list.toString());
                mDetailsPresenter.loadStudentInfoSuccess(list.get(0));
            }

            @Override
            public void onError(int i, String s) {
                Log.e(className,"Load StudentInfo Error: "+i+" message: "+s);
            }
        });
//        BmobQuery<StudentUser> userBmobQuery = new BmobQuery<>();
//        userBmobQuery.include("mobilePhoneNumber");
//        userBmobQuery.getObject(mContext, "b91d31b71e", new GetListener<StudentUser>() {
//            @Override
//            public void onSuccess(StudentUser studentUser) {
//                Log.d(className, "student: "+studentUser.toString());
//            }
//
//            @Override
//            public void onFailure(int i, String s) {
//
//            }
//        });
    }
}
