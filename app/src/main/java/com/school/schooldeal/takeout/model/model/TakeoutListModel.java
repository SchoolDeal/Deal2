package com.school.schooldeal.takeout.model.model;

import android.content.Context;
import android.util.Log;

import com.school.schooldeal.model.TakeawayRequest;
import com.school.schooldeal.sign.model.RestaurantUser;
import com.school.schooldeal.takeout.model.bean.TakeOutOrderBean;
import com.school.schooldeal.takeout.model.impl.ImplTakeoutListModel;
import com.school.schooldeal.takeout.presenter.TakeoutListPresenter;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by GavynZhang on 2017/2/21 17:49.
 */

public class TakeoutListModel implements ImplTakeoutListModel{

    public static final String className = "TOListModel";

    private Context mContext;
    private TakeoutListPresenter mListPresenter;

    public TakeoutListModel(Context context, TakeoutListPresenter presenter){
        this.mContext = context;
        this.mListPresenter = presenter;
    }

    @Override
    public void loadMinePublished() {
        RestaurantUser user = BmobUser.getCurrentUser(mContext, RestaurantUser.class);
        BmobQuery<TakeawayRequest> takeawayRequestBmobQuery = new BmobQuery<>();
        takeawayRequestBmobQuery.addWhereEqualTo("restaurant", user);
        takeawayRequestBmobQuery.include("apartment,restaurant");
        takeawayRequestBmobQuery.findObjects(mContext, new FindListener<TakeawayRequest>() {
            @Override
            public void onSuccess(List<TakeawayRequest> list) {
                conversionAndLoadSuccess(list);
            }

            @Override
            public void onError(int i, String s) {
                mListPresenter.loadDataError(i, s);
            }
        });

    }

    @Override
    public void loadMineReceived() {

    }

    @Override
    public void loadMineFinished() {

    }

    /**
     * 转化成TakeoutOrderBean并传递至presenter
     * @param list
     */
    private void conversionAndLoadSuccess(List<TakeawayRequest> list){
        List<TakeOutOrderBean> orders = new ArrayList<>();
        for (TakeawayRequest request : list) {
            Log.d(className, "request: " + request.toString());
            TakeOutOrderBean orderBean = new TakeOutOrderBean(
                    request.getObjectId(),
                    request.getAmount(),
                    request.getApartment().getApartmentName() + request.getBedroom()+"寝室",
                    request.getRestaurant().getName(),
                    request.getRestaurant().getAddress(),
                    request.getRemuneration()
            );
            orderBean.setId(request.getObjectId());
            orderBean.setStatus(request.getStatus());
            orders.add(orderBean);
        }
        mListPresenter.loadDataSuccess(orders);
    }
}
