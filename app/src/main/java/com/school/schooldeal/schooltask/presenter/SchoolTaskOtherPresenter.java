package com.school.schooldeal.schooltask.presenter;

import android.content.Context;

import com.school.schooldeal.schooltask.model.ImplSchoolTaskModel;
import com.school.schooldeal.schooltask.model.SchoolTaskDataAdapter;
import com.school.schooldeal.schooltask.model.SchoolTaskModel;
import com.school.schooldeal.schooltask.model.SchoolTaskOrderBean;
import com.school.schooldeal.schooltask.view.ImplSchoolTaskActivity;

import java.util.List;

/**
 * Created by 教科书式的机智少年 on 2017/1/21.
 */

public class SchoolTaskOtherPresenter {
    private ImplSchoolTaskActivity schoolTaskActivity;
    private Context context;
    private SchoolTaskDataAdapter adapter;
    private ImplSchoolTaskModel schoolTaskModel;
    private String title;
    public SchoolTaskOtherPresenter(Context context, ImplSchoolTaskActivity implSchoolTaskActivity,String title,int type){
        this.context = context;
        this.title = title;
        schoolTaskActivity = implSchoolTaskActivity;
        schoolTaskModel = new SchoolTaskModel(context,this,type);
        adapter = new SchoolTaskDataAdapter(context);
        schoolTaskModel.getLocated();
        schoolTaskModel.getOrders();
    }
    public void initAdapter(List<SchoolTaskOrderBean> orders) {
        adapter.setData(orders);
        schoolTaskActivity.setAdapter(adapter);
    }

    public void getnewItems(SchoolTaskDataAdapter adapter){
        schoolTaskModel.getnewItems(adapter);
    }

    public void stopRefresh(){
        schoolTaskActivity.stopRefresh();
    }

}
