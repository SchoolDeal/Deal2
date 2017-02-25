package com.school.schooldeal.takeout.model.model;

import android.content.Context;
import android.util.Log;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.school.schooldeal.commen.util.Located;
import com.school.schooldeal.commen.util.Util;
import com.school.schooldeal.model.TakeawayRequest;
import com.school.schooldeal.sign.model.RestaurantUser;
import com.school.schooldeal.sign.model.StudentUser;
import com.school.schooldeal.takeout.TakeawayStatusConsts;
import com.school.schooldeal.takeout.model.bean.TakeOutOrderBean;
import com.school.schooldeal.takeout.model.impl.ImplTakeOutFragmentModel;
import com.school.schooldeal.takeout.presenter.TakeOutFragmentPresenter;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobQueryResult;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SQLQueryListener;

/**
 * Created by U-nookia on 2016/12/19.
 * 外卖界面的数据处理类，用于获取数据集合，对数据集合做操作
 */

public class TakeOutFragmentModel implements ImplTakeOutFragmentModel {

    private static final String className = "TOFragmentModel";
    private TakeOutFragmentPresenter mPresenter;

    private List<TakeOutOrderBean> orders;
    private Context mContext;
    private Located mLocated;

    public TakeOutFragmentModel(TakeOutFragmentPresenter presenter, Context context) {
        this.mPresenter = presenter;
        orders = new ArrayList<>();
        this.mContext = context;
        if (Util.IS_STUDENT)
            mLocated = new Located(context);
    }

    @Override
    public void loadOrders() {
        if (Util.IS_STUDENT){
            Log.d(className, "is student");
            //根据地理位置来请求数据，展示由近到远的外卖服务单
            String apartmentObjectID = BmobUser.getCurrentUser(mContext, StudentUser.class).getApartment().getObjectId();

            String sql = "select include apartment, include restaurant,* from TakeawayRequest where " +
                    "restaurant in" +                        // ↓ 需要改成定位获取的数据
                    "(select * from _User where position near [106.23384,29.2735366])" +
                    "and apartment = pointer('Apartment','"+apartmentObjectID+"')" +
                    "and status = 0";
            BmobQuery<TakeawayRequest> query = new BmobQuery<>();
            query.setSQL(sql);
            query.doSQLQuery(mContext, new SQLQueryListener<TakeawayRequest>() {
                @Override
                public void done(BmobQueryResult<TakeawayRequest> bmobQueryResult, BmobException e) {
                    if (e == null) {
                        List<TakeawayRequest> result = bmobQueryResult.getResults();
                        if (result != null && result.size() > 0) {
                            conversionAndLoadSuccess(result);
                        } else {
                            Log.d(className, "查询成功，无数据返回");
                            mPresenter.loadOrdersSuccess(null);
                        }
                    }else{
                        Log.d(className, "load error: "+e.getErrorCode()+" message: "+e.getMessage());
                        mPresenter.loadOrdersFail(e.getErrorCode(), e.getMessage());
                    }
                }
            });

        }else {
            Log.d(className, "is restaurant");
            RestaurantUser restaurantUser = BmobUser.getCurrentUser(mContext, RestaurantUser.class);
            BmobQuery<TakeawayRequest> requestQuery = new BmobQuery<>();
            requestQuery.addWhereEqualTo("restaurant", restaurantUser);
            requestQuery.addWhereEqualTo("status", TakeawayStatusConsts.NOT_BEING_TAKEN);
            requestQuery.include("apartment,restaurant");
            requestQuery.findObjects(mContext, new FindListener<TakeawayRequest>() {
                @Override
                public void onSuccess(List<TakeawayRequest> list) {
                    conversionAndLoadSuccess(list);
                }

                @Override
                public void onError(int i, String s) {
                    Log.d(className, "Query error, code: " + i + " , " + s);
                    mPresenter.loadOrdersFail(i, s);
                }
            });
        }
    }

    /**
     * 转化成TakeoutOrderBean并传递至presenter
     * @param list
     */
    private void conversionAndLoadSuccess(List<TakeawayRequest> list){
        int addNum = 0;
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
            if (!orders.contains(orderBean))
                orders.add(orderBean);
        }
        mPresenter.loadOrdersSuccess(orders);
    }

    public void startLocate(){
        mLocated.starLocated(new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation aMapLocation) {
                if (aMapLocation != null){
                    Log.d(className, "onLocationChanged: "+aMapLocation.getAddress());
                }else{
                    Log.e(className, "Error, code:"+aMapLocation.getErrorCode()+" Info: "+aMapLocation.getErrorInfo());
                }
            }
        });
    }
}
