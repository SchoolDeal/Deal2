package com.school.schooldeal.mine.model;

import android.content.Context;
import android.util.Log;

import com.school.schooldeal.mine.presenter.MineReceivedStorePresenter;
import com.school.schooldeal.model.TakeawayRequest;
import com.school.schooldeal.model.TakeawayService;
import com.school.schooldeal.sign.model.StudentUser;
import com.school.schooldeal.takeout.TakeawayStatusConsts;
import com.school.schooldeal.takeout.model.bean.TakeOutOrderBean;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by GavynZhang on 2017/2/26 20:45.
 */

public class MineReceivedStoreModel implements ImplMineReceivedStoreModel{

    private static final String className = "MReceivedSModel";

    private Context mContext;
    private MineReceivedStorePresenter mPresenter;

    private List<TakeOutOrderBean> mOrderBeanList = new ArrayList<>();

    public MineReceivedStoreModel(Context context, MineReceivedStorePresenter presenter) {
        mContext = context;
        mPresenter = presenter;
    }

    @Override
    public void loadService() {
        BmobQuery<TakeawayService> serviceBmobQuery = new BmobQuery<>();
        StudentUser user = BmobUser.getCurrentUser(mContext, StudentUser.class);
        serviceBmobQuery.addWhereEqualTo("student", user);
        serviceBmobQuery.include("request,request.apartment,request.restaurant");
        serviceBmobQuery.findObjects(mContext, new FindListener<TakeawayService>() {
            @Override
            public void onSuccess(List<TakeawayService> list) {
                conversionAndLoadSuccess(list);
                Log.d(className, "List: "+list.get(0).toString());
            }

            @Override
            public void onError(int i, String s) {
                Log.e(className, "Load service error: "+i+" message:"+s);
            }
        });
    }

    @Override
    public void loadRequest(String objectID) {

    }

    private void conversionAndLoadSuccess(List<TakeawayService> list){
        TakeOutOrderBean bean;
        for (TakeawayService service : list){
            if (service.getRequest().getStatus() == TakeawayStatusConsts.HAS_BEING_TAKEN) {
                bean = new TakeOutOrderBean();
                bean.setId(service.getRequest().getObjectId());
                bean.setServiceID(service.getObjectId());
                bean.setStatus(service.getRequest().getStatus());
                bean.setAmount(service.getNumber());
                bean.setDestination(service.getRequest().getApartment().getApartmentName() + service.getRequest().getBedroom() + "寝室");
                bean.setRestaurantName(service.getRequest().getRestaurant().getName());
                bean.setRestaurantAddress(service.getRequest().getRestaurant().getAddress());
                bean.setMoney(service.getRemuneration());
                bean.setStudentName(service.getRequest().getConsigneeName());
                bean.setStudentBedroom(service.getRequest().getBedroom());
                bean.setStudentPhoneNum(service.getRequest().getConsigneePhoneNum());
                bean.setImgURL(service.getRequest().getRestaurant().getImgUrl());

                mOrderBeanList.add(bean);
            }
        }
        mPresenter.loadOrdersSuccess(mOrderBeanList);
    }
}