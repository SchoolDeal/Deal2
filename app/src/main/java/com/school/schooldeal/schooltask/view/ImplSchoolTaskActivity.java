package com.school.schooldeal.schooltask.view;

import com.school.schooldeal.schooltask.model.SchoolTaskDataAdapter;

/**
 * Created by 教科书式的机智少年 on 2017/1/21.
 */

public interface ImplSchoolTaskActivity {
    void setAdapter(SchoolTaskDataAdapter adapter);
    void stopRefresh();
}
