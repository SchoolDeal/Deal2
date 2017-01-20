package com.school.schooldeal.schooltask.presenter;

import android.content.Context;

import com.school.schooldeal.schooltask.model.ImplSchoolTaskModel;
import com.school.schooldeal.schooltask.model.SchoolTaskDataAdapter;
import com.school.schooldeal.schooltask.model.SchoolTaskModel;
import com.school.schooldeal.schooltask.view.ImplSchoolTaskFragment;

/**
 * Created by 教科书式的机智少年 on 2017/1/20.
 */

public class SchoolTaskPresenter {
    private SchoolTaskDataAdapter adapter;
    private Context context;
    private ImplSchoolTaskFragment schoolTaskFragment;
    private ImplSchoolTaskModel schoolTaskModel;
    public SchoolTaskPresenter(Context context,ImplSchoolTaskFragment schoolTaskFragment){
        this.context = context;
        this.schoolTaskFragment = schoolTaskFragment;
        schoolTaskModel = new SchoolTaskModel();
        adapter = new SchoolTaskDataAdapter(context);
    }
    public void initAdapter() {
        adapter.setData(schoolTaskModel.getOrders());
        schoolTaskFragment.setAdapter(adapter);
    }
}
