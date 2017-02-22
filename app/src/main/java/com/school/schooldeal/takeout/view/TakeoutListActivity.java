package com.school.schooldeal.takeout.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.school.schooldeal.R;
import com.school.schooldeal.base.BaseActivity;
import com.school.schooldeal.takeout.model.TakeOutDataAdapter;
import com.school.schooldeal.takeout.presenter.TakeoutListPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TakeoutListActivity extends BaseActivity implements ImplTakeoutListActivity{

    //我发布的
    public static final int PUBLISHED = 0;
    //我收到的
    public static final int RECEIVED = 1;
    //我完成的
    public static final int FINISHED = 2;

    @BindView(R.id.toolbar_takeoutList)
    Toolbar mToolbarTakeoutList;
    @BindView(R.id.recycler_takeout_list)
    RecyclerView mRecyclerTakeoutList;

    //当前状态
    private int status = -1;
    private TakeoutListPresenter mPresenter;

    private TakeOutDataAdapter mDataAdapter = new TakeOutDataAdapter(this);

    public static void actionStart(Context context, int status) {
        Intent intent = new Intent(context, TakeoutListActivity.class);
        intent.putExtra("status", status);
        context.startActivity(intent);
    }

    @Override
    protected void initData() {
        //初始化当前状态
        Intent intent = getIntent();
        status = intent.getIntExtra("status", -1);
        mPresenter = new TakeoutListPresenter(this,this);
        initToolbar();
        initRecycler();
        mPresenter.loadData(status);
    }

    /**
     * 进行ToolBar的初始化工作
     */
    private void initToolbar() {
        if (status != -1) {
            if (status == PUBLISHED) {
                mToolbarTakeoutList.setTitle("我发布的");
            } else if (status == RECEIVED) {
                mToolbarTakeoutList.setTitle("我收到的");
            } else if (status == FINISHED) {
                mToolbarTakeoutList.setTitle("我完成的");
            }
            mToolbarTakeoutList.setTitleTextColor(getResources().getColor(R.color.white));
        }
    }

    /**
     * 进行RecyclerView的初始化工作
     */
    private void initRecycler() {
        mRecyclerTakeoutList.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_takeout_list;
    }

    @Override
    public void setAdapter(TakeOutDataAdapter adapter) {
        mRecyclerTakeoutList.setAdapter(adapter);
    }
}
