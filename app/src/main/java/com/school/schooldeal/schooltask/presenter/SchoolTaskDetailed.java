package com.school.schooldeal.schooltask.presenter;

import android.content.Context;

import com.school.schooldeal.model.CommonRequest;
import com.school.schooldeal.schooltask.model.SchoolTaskDetailedModel;
import com.school.schooldeal.schooltask.view.ImplSchoolTaskDetailedActivity;

/**
 * Created by 教科书式的机智少年 on 2017/2/5.
 */

public class SchoolTaskDetailed {
    private ImplSchoolTaskDetailedActivity schoolTaskDetailedActivity;
    private Context context;
    private SchoolTaskDetailedModel schoolTaskDetailedModel;

    public SchoolTaskDetailed (Context context, ImplSchoolTaskDetailedActivity schoolTaskDetailedActivity){
        this.context = context;
        this.schoolTaskDetailedActivity = schoolTaskDetailedActivity;
        schoolTaskDetailedModel = new SchoolTaskDetailedModel(context);
    }
    public void getrob(){
        schoolTaskDetailedModel.getRobMessage(new CommonRequest());
    }
}
