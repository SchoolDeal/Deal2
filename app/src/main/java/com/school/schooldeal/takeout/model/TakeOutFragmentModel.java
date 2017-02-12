package com.school.schooldeal.takeout.model;

import android.content.Context;
import android.util.Log;

import com.school.schooldeal.commen.util.Util;
import com.school.schooldeal.model.TakeawayRequest;
import com.school.schooldeal.sign.model.RestaurantUser;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by U-nookia on 2016/12/19.
 * 外卖界面的数据处理类，用于获取数据集合，对数据集合做操作
 */

public class TakeOutFragmentModel implements ImplTakeOutModel{

    private static final String className = "TOFragmentModel";

    private List<TakeOutOrderBean> orders;
    private Context mContext;

    public TakeOutFragmentModel(Context context) {
        orders = new ArrayList<>();
        this.mContext = context;
    }


    @Override
    public List<TakeOutOrderBean> getOrders() {

        if (Util.IS_STUDENT){
            //定位并获取附近商家订单
            orders.add(new TakeOutOrderBean(1));
            orders.add(new TakeOutOrderBean(2));
            orders.add(new TakeOutOrderBean(3));
            orders.add(new TakeOutOrderBean(4));
            orders.add(new TakeOutOrderBean(5));
            orders.add(new TakeOutOrderBean(6));
            orders.add(new TakeOutOrderBean(7));
            orders.add(new TakeOutOrderBean(8));
            orders.add(new TakeOutOrderBean(1));
            orders.add(new TakeOutOrderBean(2));
            orders.add(new TakeOutOrderBean(3));
            orders.add(new TakeOutOrderBean(4));
            orders.add(new TakeOutOrderBean(5));
            orders.add(new TakeOutOrderBean(6));
            orders.add(new TakeOutOrderBean(7));
            orders.add(new TakeOutOrderBean(8));
        }else{
            //获取商家发布的订单
            final RestaurantUser restaurantUser = BmobUser.getCurrentUser(mContext, RestaurantUser.class);
            BmobQuery<TakeawayRequest> requestQuery = new BmobQuery<>();
            requestQuery.addWhereEqualTo("restaurant", restaurantUser);
            requestQuery.include("restaurant");
            requestQuery.findObjects(mContext, new FindListener<TakeawayRequest>() {
                @Override
                public void onSuccess(List<TakeawayRequest> list) {
                    for (TakeawayRequest request : list){
//                        Log.d(className, "餐馆数据： "+request.getRestaurant().toString());
//                        Log.d(className, "餐馆名称： "+request.getRestaurant().getName()+" 餐馆地址："+
//                                request.getRestaurant().getAddress());
                        TakeOutOrderBean orderBean = new TakeOutOrderBean(
                                request.getObjectId(),
                                request.getAmount(),
                                request.getDestination(),
                                request.getRestaurant().getName(),
                                request.getRestaurant().getAddress(),
                                request.getRemuneration()

                        );
                        orders.add(orderBean);
                    }
                }

                @Override
                public void onError(int i, String s) {
                    Log.d(className, "Query error, code: "+i+" , "+s);
                }
            });
        }

        return orders;
    }
}
