package com.school.schooldeal.schooltask.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.school.schooldeal.commen.util.ToastUtil;
import com.school.schooldeal.model.CommonRequest;
import com.school.schooldeal.model.Store;
import com.school.schooldeal.model.Student;
import com.school.schooldeal.sign.model.StudentUser;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by 教科书式的机智少年 on 2017/2/18.
 */

public class sendCommonService extends Service {
    private String storeName;
    private String content;
    private String remarks;
    private String refund;
    private String storeID;
    private Store store;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        storeName = intent.getStringExtra("storename");
        content = intent.getStringExtra("content");
        refund = intent.getStringExtra("refund");
        remarks = intent.getStringExtra("remarks");
        Log.e("data",storeName);
        getStoreId();
        return super.onStartCommand(intent, flags, startId);
    }

    private void getStoreId(){
        /*String bql = "select * from Store where storeName = 31栋便利店";
        new BmobQuery<Store>().doSQLQuery(this, bql, new SQLQueryListener<Store>() {
            @Override
            public void done(BmobQueryResult<Store> bmobQueryResult, BmobException e) {
                if (e == null){
                    List<Store> list = (ArrayList<Store>)bmobQueryResult.getResults();
                    if (list != null && list.size()!= 0){
                        storeID = list.get(0).getObjectId();
                        send();
                    }else {
                        Log.e("data","阿勒");
                    }
                }else {
                    ToastUtil.makeShortToast(getApplicationContext(),"学校订单发送失败，请稍后重试1");
                    stopSelf();
                }
            }
        });*/

        BmobQuery<Store> query = new BmobQuery<Store>();
        query.addWhereEqualTo("storeName",storeName);
        query.setLimit(10);
        query.findObjects(this, new FindListener<Store>() {
            @Override
            public void onSuccess(List<Store> list) {
                if (list != null && list.size()!= 0){
                    store = list.get(0);
                    send();
                }else {
                    ToastUtil.makeShortToast(getApplicationContext(),"学校订单发送失败，请稍后重试");
                    stopSelf();
                }
            }

            @Override
            public void onError(int i, String s) {
                Log.e("data",i+s);
                stopSelf();
            }
        });
    }

    private void send(){
        CommonRequest commonRequest = new CommonRequest();
        Student student = new Student();
        StudentUser user = BmobUser.getCurrentUser(this,StudentUser.class);
        student.setObjectId(user.getObjectId());
        commonRequest.setApartmentId(user.getApartment().getObjectId());
        commonRequest.setType(0);
        commonRequest.setStoreType(store.getStoreType());
        commonRequest.setStore(store);
        commonRequest.setStudent(student);
        commonRequest.setRequestContent(content);
        commonRequest.setRequestRemarks(remarks);
        commonRequest.setRemuneration(Float.parseFloat(refund));
        commonRequest.save(this, new SaveListener() {
            @Override
            public void onSuccess() {
                ToastUtil.makeShortToast(getApplicationContext(),"发送成功");
                stopSelf();
            }

            @Override
            public void onFailure(int i, String s) {
                ToastUtil.makeShortToast(getApplicationContext(),"学校订单发送失败，请稍后重试");
                Log.e("erro in Commonservice",i+" "+s);
                stopSelf();
            }
        });
    }
}
