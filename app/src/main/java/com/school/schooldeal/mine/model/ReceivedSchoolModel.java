package com.school.schooldeal.mine.model;

import android.content.Context;
import android.util.Log;

import com.school.schooldeal.commen.util.ToastUtil;
import com.school.schooldeal.mine.presenter.ReceivedSchoolPresenter;
import com.school.schooldeal.model.CommonService;
import com.school.schooldeal.sign.model.StudentUser;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by 教科书式的机智少年 on 2017/2/26.
 */

public class ReceivedSchoolModel {
    private Context context;
    private ReceivedSchoolPresenter presenter;
    public ReceivedSchoolModel (Context context,ReceivedSchoolPresenter presenter){
        this.presenter = presenter;
        this.context = context;
    }
    public void getData(){
        StudentUser user = BmobUser.getCurrentUser(context,StudentUser.class);
        BmobQuery<CommonService> query = new BmobQuery<CommonService>();
        query.addWhereEqualTo("student",user.getObjectId());
        query.include("student,student.apartment,request,request.student,request.store");
        query.findObjects(context, new FindListener<CommonService>() {
            @Override
            public void onSuccess(List<CommonService> list) {
                if (list != null && list.size() != 0){
                    presenter.stopRefresh();
                    presenter.setAdapter(list);
                }else {
                    ToastUtil.makeShortToast(context,"尚未有接单");
                    presenter.stopRefresh();
                }
            }

            @Override
            public void onError(int i, String s) {
                presenter.stopRefresh();
                Log.e("ReceivedSchoolModel",i+s);
            }
        });
    }

    public void getnewData(){
        StudentUser user = BmobUser.getCurrentUser(context,StudentUser.class);
        BmobQuery<CommonService> query = new BmobQuery<CommonService>();
        query.addWhereEqualTo("student",user.getObjectId());
        query.include("student,student.apartment,request,request.student,request.store");
        query.findObjects(context, new FindListener<CommonService>() {
            @Override
            public void onSuccess(List<CommonService> list) {
                if (list != null && list.size() != 0){
                    presenter.stopRefresh();
                    presenter.getnewData(list);
                }else {
                    presenter.stopRefresh();
                }
            }

            @Override
            public void onError(int i, String s) {
                presenter.stopRefresh();
                Log.e("ReceivedSchoolModelget",i+s);
            }
        });
    }
}
