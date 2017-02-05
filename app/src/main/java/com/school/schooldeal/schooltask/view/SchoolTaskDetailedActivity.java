package com.school.schooldeal.schooltask.view;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.school.schooldeal.R;
import com.school.schooldeal.base.BaseActivity;

import butterknife.BindView;

/**
 * Created by 教科书式的机智少年 on 2017/2/5.
 */

public class SchoolTaskDetailedActivity extends BaseActivity implements View.OnClickListener{
    @BindView(R.id.toolBar_school_task_detailed)
    Toolbar toolbar;
    @BindView(R.id.school_task_detailed_button)
    Button button;
    @Override
    protected void initData() {
        initToolBar();
        button.setOnClickListener(this);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_schooltask_detailed;
    }
    private void initToolBar(){
        toolbar.setTitle("详细信息");
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(context, "抢单成功", Toast.LENGTH_SHORT).show();
    }
}
