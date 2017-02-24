package com.school.schooldeal.schooltask.model;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.school.schooldeal.commen.util.Located;
import com.school.schooldeal.commen.util.ToastUtil;
import com.school.schooldeal.model.CommonRequest;
import com.school.schooldeal.schooltask.presenter.SchoolTaskOtherPresenter;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;

/**
 * Created by 教科书式的机智少年 on 2017/1/20.
 */

public class SchoolTaskModel implements ImplSchoolTaskModel {
    private List<SchoolTaskOrderBean> orders;
    private Context context;
    private Located locate;
    //private SchoolTaskDataAdapter adapter;
    private SchoolTaskOtherPresenter presenter;
    private int type;
    public SchoolTaskModel(Context context,SchoolTaskOtherPresenter presenter,int type){
        orders = new ArrayList<>();
        this.context = context;
        this.presenter = presenter;
        this.type = type;
        locate= new Located(context);
    }
    @Override
    public void getOrders() {
        //String bql = "select include store,include student,* from CommonRequest where student in (select * from _User where name = '渝州铁板烧')" ;
                //"group by store.storeType having storeType = 1";
        /*String bql = "select include store,include student,* from CommonRequest where apartmentId = a275751aa8 and storeType = 1";
        new BmobQuery<CommonRequest>().doSQLQuery(context, bql, new SQLQueryListener<CommonRequest>() {
            @Override
            public void done(BmobQueryResult<CommonRequest> bmobQueryResult, BmobException e) {
                if (e==null){
                    List<CommonRequest> list = (List<CommonRequest>)bmobQueryResult.getResults();
                    if (list!= null && list.size()>0){
                        for (int i = 0;i<list.size();i++){
                            orders.add(new SchoolTaskOrderBean(i,list.get(i)));
                        }
                        presenter.initAdapter(orders);
                    }else {
                        ToastUtil.makeShortToast(context,"抱歉，暂时未有单子可接");
                    }
                }else {
                    Log.e("CommonResquest", "错误码："+e.getErrorCode()+"，错误描述："+e.getMessage());
                }
            }
        });*/

        BmobQuery<CommonRequest> query = new BmobQuery<CommonRequest>();
        query.addWhereEqualTo("apartmentId","a275751aa8");
        query.addWhereEqualTo("storeType",type);
        query.addWhereEqualTo("type",0);
        query.include("store");
        query.include("student");
        query.findObjects(context, new FindListener<CommonRequest>() {
            @Override
            public void onSuccess(List<CommonRequest> list) {
                if (list!= null && list.size() != 0){
                    for (int i = 0;i<list.size();i++){
                        orders.add(new SchoolTaskOrderBean(i,list.get(i)));
                    }
                    presenter.initAdapter(orders);
                }else {
                    ToastUtil.makeShortToast(context,"抱歉，暂时未有单子可接");
                }
            }

            @Override
            public void onError(int i, String s) {
                ToastUtil.makeShortToast(context,"抱歉，暂时未有单子可接");
                Log.e("TaskModel",i +"  "+s);
            }
        });
    }

    @Override
    public void getLocated() {
        //权限申请（6.0以上）
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context.checkSelfPermission(ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED
                &&context.checkSelfPermission(ACCESS_FINE_LOCATION)!=PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions((Activity)context,new String[]{ACCESS_COARSE_LOCATION,ACCESS_FINE_LOCATION},0);
        }
        locate.starLocated(new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation aMapLocation) {
                if (aMapLocation!= null){
                    if (aMapLocation.getErrorCode() == 0){
                        //这里写需要做的事情
                        Log.e("data",aMapLocation.getAddress());
                        locate.stopLocated();
                    }else {
                        Log.e("AmapError","location Error, ErrCode:"
                                + aMapLocation.getErrorCode() + ", errInfo:"
                                + aMapLocation.getErrorInfo());
                        locate.stopLocated();
                    }
                }
            }
        });
    }

    @Override
    public void getnewItems(final SchoolTaskDataAdapter adapter) {
        /*String bql = "select include store,include student,* from CommonRequest where apartmentId = a275751aa8 and storeType =" + type;
        new BmobQuery<CommonRequest>().doSQLQuery(context, bql, new SQLQueryListener<CommonRequest>() {
            @Override
            public void done(BmobQueryResult<CommonRequest> bmobQueryResult, BmobException e) {
                if (e == null){
                    List<CommonRequest> list = (ArrayList<CommonRequest>)bmobQueryResult.getResults();
                    if (list != null && list.size() != 0){
                        for (int i = 0;i<list.size();i++){
                            newOrders.add(new SchoolTaskOrderBean(i,list.get(i)));
                        }
                        adapter.setnewItems(newOrders);
                        presenter.stopRefresh();

                    }else {
                        ToastUtil.makeShortToast(context,"抱歉，暂时未有单子可接");
                        presenter.stopRefresh();
                    }
                }else {
                    Log.e("CommonResquest", "错误码："+e.getErrorCode()+"，错误描述："+e.getMessage());
                    presenter.stopRefresh();
                }
            }
        });*/
        final List<SchoolTaskOrderBean> newOrders = new ArrayList<SchoolTaskOrderBean>();
        BmobQuery<CommonRequest> query = new BmobQuery<CommonRequest>();
        query.addWhereEqualTo("apartmentId","a275751aa8");
        query.addWhereEqualTo("storeType",type);
        query.addWhereEqualTo("type",0);
        query.include("store");
        query.include("student");
        query.findObjects(context, new FindListener<CommonRequest>() {
            @Override
            public void onSuccess(List<CommonRequest> list) {
                if (list != null && list.size() != 0){
                    for (int i = 0;i<list.size();i++){
                        newOrders.add(new SchoolTaskOrderBean(i,list.get(i)));
                    }
                    adapter.setnewItems(newOrders);
                    presenter.stopRefresh();
                }else {
                    presenter.stopRefresh();
                    ToastUtil.makeShortToast(context,"抱歉，暂时未有单子可接");
                }
            }

            @Override
            public void onError(int i, String s) {
                ToastUtil.makeShortToast(context,"抱歉，暂时未有单子可接");
                presenter.stopRefresh();
                Log.e("TaskModel",i +"  "+s);
            }
        });

    }
}
