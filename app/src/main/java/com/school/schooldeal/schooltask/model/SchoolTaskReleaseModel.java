package com.school.schooldeal.schooltask.model;

import android.content.Context;
import android.util.Log;

import com.school.schooldeal.commen.util.ToastUtil;
import com.school.schooldeal.model.CommonRequest;
import com.school.schooldeal.model.Store;
import com.school.schooldeal.schooltask.presenter.SchoolTaskReasePresenter;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobQueryResult;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SQLQueryListener;

/**
 * Created by 教科书式的机智少年 on 2017/2/15.
 */

public class SchoolTaskReleaseModel  implements ImplSchoolTaskReleaseModel {
    private SchoolTaskReasePresenter presenter;
    private Context context;
    public SchoolTaskReleaseModel(SchoolTaskReasePresenter presenter,Context context){
        this.presenter = presenter;
        this.context = context;
    }
    @Override
    public void sendMessage(CommonRequest commonRequest) {

    }

    @Override
    public void getStores() {
        Log.e("data","OK3");
        String bql = "select * from Store";
        new BmobQuery<Store>().doSQLQuery(context, bql, new SQLQueryListener<Store>() {
            @Override
            public void done(BmobQueryResult<Store> bmobQueryResult, BmobException e) {
                Log.e("data","OK4");
                if (e == null){
                    List<Store> list = (ArrayList<Store>)bmobQueryResult.getResults();
                    if (list == null && list.size()==0){
                        ToastUtil.makeShortToast(context,"商店数据为空哦");
                    }else {
                        Log.e("data","OK1");
                        List<String> stores = new ArrayList<String>();
                        for (int i = 0;i<list.size();i++){
                            stores.add(list.get(i).getStoreName());
                        }
                        presenter.setStores(stores);
                    }
                }else {
                    Log.e("data","ERRO");
                    Log.e("ReleaseModel  ", "错误码："+e.getErrorCode()+"，错误描述："+e.getMessage());
                    ToastUtil.makeShortToast(context,"请求商店信息错误，请稍后再试");
                }
            }
        });
    }
}
