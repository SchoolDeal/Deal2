package com.school.schooldeal.schooltask.view;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.school.schooldeal.R;
import com.school.schooldeal.base.BaseActivity;
import com.school.schooldeal.model.CommonRequest;
import com.school.schooldeal.schooltask.model.SchoolTaskDataAdapter;
import com.school.schooldeal.schooltask.presenter.SchoolTaskOtherPresenter;

import butterknife.BindView;

/**
 * Created by 教科书式的机智少年 on 2017/1/21.
 */

public class SchoolTaskActivity extends BaseActivity implements ImplSchoolTaskActivity,View.OnClickListener{
    @BindView(R.id.school_task_recycler)
    RecyclerView recyclerView;
    @BindView(R.id.toolBar_school_task)
    Toolbar toolbar;
    @BindView(R.id.school_task_fab)
    FloatingActionButton fab;
    @BindView(R.id.school_task_swipe)
    SwipeRefreshLayout refresh;
    private SchoolTaskOtherPresenter presenter;
    private String title = "SchoolTask";
    private SchoolTaskDataAdapter adapter;
    private int type;
    @Override
    protected void initData() {
        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        type = intent.getIntExtra("type",1);
        initToolBar();
        initRecyclerView();
        presenter = new SchoolTaskOtherPresenter(context,this,title,type);
        setClickListener();
        setRefresh();
        refresh.setRefreshing(true);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_school_task;
    }

    @Override
    public void setAdapter(SchoolTaskDataAdapter adapter) {
        adapter.setOnItemClickListener(new SchoolTaskDataAdapter.OnSchoolTaskItemClickListener() {
            @Override
            public void onItemClick(View view, CommonRequest commonRequest,int position) {
                Intent intent = new Intent(context,SchoolTaskDetailedActivity.class);
                intent.putExtra("CommonRequest",commonRequest);
                intent.putExtra("position",position);
                startActivityForResult(intent,1000);
            }
        });
        this.adapter = adapter;
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.school_task_fab:
                Intent intent = new Intent(getApplicationContext(),SchoolTaskReleaseActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void initRecyclerView(){
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
    }

    private void initToolBar(){
        toolbar.setTitle(title);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
    }

    private void setClickListener(){
        fab.setOnClickListener(this);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 2){
            int position = data.getIntExtra("position",0);
            /*不知是我眼拙还是什么
            * 这里为什么就没有item移除的动画呢，这是个值得思考的问题(ˇˍˇ）~*/
            adapter.getLists().remove(position);
            adapter.notifyItemRemoved(position);
            adapter.notifyItemRangeChanged(position,adapter.getItemCount());
        }else {
            Log.e("data","NO");
        }
    }

    private void setRefresh(){
        refresh.setColorSchemeResources(
                R.color.md_red_600,
                R.color.md_yellow_600,
                R.color.md_blue_600);
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.getnewItems(adapter);
            }
        });
    }

    @Override
    public void stopRefresh(){
        refresh.setRefreshing(false);
    }

}
