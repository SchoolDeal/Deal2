package com.school.schooldeal.schooltask.view;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.school.schooldeal.R;
import com.school.schooldeal.base.BaseActivity;
import com.school.schooldeal.schooltask.model.SchoolTaskDataAdapter;
import com.school.schooldeal.schooltask.presenter.SchoolTaskOtherPresenter;

import butterknife.BindView;

/**
 * Created by 教科书式的机智少年 on 2017/1/21.
 */

public class SchoolTaskActivity extends BaseActivity implements ImplSchoolTaskActivity{
    @BindView(R.id.school_task_recycler)
    RecyclerView recyclerView;
    @BindView(R.id.toolBar_school_task)
    Toolbar toolbar;
    private SchoolTaskOtherPresenter presenter;
    private String title = "SchoolTask";
    @Override
    protected void initData() {
        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        initToolBar();
        initRecyclerView();
        presenter = new SchoolTaskOtherPresenter(context,this);
        presenter.initAdapter();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_school_task;
    }

    @Override
    public void setAdapter(SchoolTaskDataAdapter adapter) {
        adapter.setOnItemClickListener(new SchoolTaskDataAdapter.OnSchoolTaskItemClickListener() {
            @Override
            public void onItemClick(View view) {
                Intent intent = new Intent(getApplicationContext(),SchoolTaskDetailedActivity.class);
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter);
    }

    private void initRecyclerView(){
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
    }
    private void initToolBar(){
        toolbar.setTitle(title);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
    }
}
