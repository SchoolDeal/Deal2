package com.school.schooldeal.schooltask.presenter;

import android.content.Context;

import com.school.schooldeal.schooltask.model.ImplSchoolTaskModel;
import com.school.schooldeal.schooltask.model.SchoolTaskDataAdapter;
import com.school.schooldeal.schooltask.model.SchoolTaskModel;
import com.school.schooldeal.schooltask.view.ImplSchoolTaskActivity;

/**
 * Created by 教科书式的机智少年 on 2017/1/21.
 */

public class SchoolTaskOtherPresenter {
    private ImplSchoolTaskActivity schoolTaskActivity;
    private Context context;
    private SchoolTaskDataAdapter adapter;
    private ImplSchoolTaskModel schoolTaskModel;
    public SchoolTaskOtherPresenter(Context context, ImplSchoolTaskActivity implSchoolTaskActivity){
        this.context = context;
        schoolTaskActivity = implSchoolTaskActivity;
        schoolTaskModel = new SchoolTaskModel();
        adapter = new SchoolTaskDataAdapter(context);
    }
    public void initAdapter() {
        adapter.setData(schoolTaskModel.getOrders());
        schoolTaskActivity.setAdapter(adapter);
    }
}
