package com.school.schooldeal.mine.model;

import android.content.Context;
import android.util.Log;

import com.school.schooldeal.commen.util.ToastUtil;
import com.school.schooldeal.mine.presenter.MineOverSchoolPresenter;
import com.school.schooldeal.model.CommonService;
import com.school.schooldeal.sign.model.StudentUser;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by 教科书式的机智少年 on 2017/2/27.
 */

public class MineOverSchoolModel {
    private Context context;
    private MineOverSchoolPresenter presenter;
    public MineOverSchoolModel(Context context,MineOverSchoolPresenter presenter){
        this.context = context;
        this.presenter = presenter;
    }
    public void getData(){
        StudentUser user = BmobUser.getCurrentUser(context,StudentUser.class);
        BmobQuery<CommonService> query = new BmobQuery<CommonService>();
        query.include("student,student.apartment,request,request.student,request.store");
        query.addWhereEqualTo("student",user.getObjectId());
        query.addWhereEqualTo("request.type",2);
        query.findObjects(context, new FindListener<CommonService>() {
            @Override
            public void onSuccess(List<CommonService> list) {
                if (list != null && list.size() != 0){
                    presenter.stopRefresh();
                    presenter.setData(list);
                }else {
                    ToastUtil.makeShortToast(context,"尚未有完成的订单");
                    presenter.stopRefresh();
                }
            }

            @Override
            public void onError(int i, String s) {
                presenter.stopRefresh();
                ToastUtil.makeShortToast(context,"尚未有完成的订单");
                Log.e("ReceivedSchoolModel",i+s);
            }
        });
    }
    public void getnewData(){
        StudentUser user = BmobUser.getCurrentUser(context,StudentUser.class);
        BmobQuery<CommonService> query = new BmobQuery<CommonService>();
        query.include("student,student.apartment,request,request.student,request.store");
        query.addWhereEqualTo("student",user.getObjectId());
        query.addWhereEqualTo("request.type",2);
        query.findObjects(context, new FindListener<CommonService>() {
            @Override
            public void onSuccess(List<CommonService> list) {
                if (list != null && list.size() != 0){
                    presenter.stopRefresh();
                    presenter.setnewData(list);
                }else {
                    ToastUtil.makeShortToast(context,"尚未有完成的订单");
                    presenter.stopRefresh();
                }
            }

            @Override
            public void onError(int i, String s) {
                presenter.stopRefresh();
                ToastUtil.makeShortToast(context,"尚未有完成的订单");
                Log.e("ReceivedSchoolModel",i+s);
            }
        });
    }
}
