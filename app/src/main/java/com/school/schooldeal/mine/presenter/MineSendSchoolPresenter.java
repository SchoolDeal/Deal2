package com.school.schooldeal.mine.presenter;

import android.content.Context;

import com.school.schooldeal.mine.model.MineSendSchoolModel;
import com.school.schooldeal.mine.view.MineSendSchoolActivity;
import com.school.schooldeal.model.CommonRequest;

import java.util.List;

/**
 * Created by 教科书式的机智少年 on 2017/2/26.
 */

public class MineSendSchoolPresenter {
    private Context context;
    private MineSendSchoolActivity view;
    private MineSendSchoolModel model;
    public MineSendSchoolPresenter(Context context,MineSendSchoolActivity view){
        this.context = context;
        this.view = view;
        model = new MineSendSchoolModel(context,this);
    }

    public void getData(){
        model.getData();
    }

    public void setData(List<CommonRequest> list){
        view.setAdapterData(list);
    }
    public void getnewData(){
        model.getnewData();
    }
    public void setnewData(List<CommonRequest> list){
        view.setnewData(list);
    }
    public void stopRefresh(){
        view.stopRefresh();
    }
}
