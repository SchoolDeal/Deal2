package com.school.schooldeal.mine.model;

import android.content.Context;
import android.util.Log;

import com.school.schooldeal.mine.presenter.MineSendSchoolPresenter;
import com.school.schooldeal.model.CommonRequest;
import com.school.schooldeal.sign.model.StudentUser;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by 教科书式的机智少年 on 2017/2/26.
 */

public class MineSendSchoolModel {
    private Context context;
    private MineSendSchoolPresenter presenter;
    public MineSendSchoolModel(Context context,MineSendSchoolPresenter presenter){
        this.context = context;
        this.presenter = presenter;
    }

    public void getData(){
        StudentUser user = BmobUser.getCurrentUser(context,StudentUser.class);
        BmobQuery<CommonRequest> query = new BmobQuery<CommonRequest>();
        query.addWhereEqualTo("student",user.getObjectId());
        query.addWhereEqualTo("type",1);
        query.addWhereEqualTo("type",0);
        query.include("student,store");
        query.findObjects(context, new FindListener<CommonRequest>() {
            @Override
            public void onSuccess(List<CommonRequest> list) {
                if (list != null && list.size() != 0){
                    presenter.stopRefresh();
                    presenter.setData(list);
                }else {
                    presenter.stopRefresh();
                }
            }

            @Override
            public void onError(int i, String s) {
                Log.e("erro in MineSendModel",i+s);
                presenter.stopRefresh();
            }
        });
    }

    public void getnewData(){
        StudentUser user = BmobUser.getCurrentUser(context,StudentUser.class);
        BmobQuery<CommonRequest> query = new BmobQuery<CommonRequest>();
        query.addWhereEqualTo("student",user.getObjectId());
        query.addWhereEqualTo("type",1);
        query.addWhereEqualTo("type",0);
        query.include("student,store");
        query.findObjects(context, new FindListener<CommonRequest>() {
            @Override
            public void onSuccess(List<CommonRequest> list) {
                if (list != null && list.size() != 0){
                    presenter.stopRefresh();
                    presenter.setnewData(list);
                }else {
                    presenter.stopRefresh();
                }
            }

            @Override
            public void onError(int i, String s) {
                Log.e("erro in MineSendModel",i+s);
                presenter.stopRefresh();
            }
        });
    }
}
