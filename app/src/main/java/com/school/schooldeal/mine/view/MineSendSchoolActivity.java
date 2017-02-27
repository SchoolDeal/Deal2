package com.school.schooldeal.mine.view;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.school.schooldeal.R;
import com.school.schooldeal.base.BaseActivity;
import com.school.schooldeal.mine.model.MineSendAdapter;
import com.school.schooldeal.mine.presenter.MineSendSchoolPresenter;
import com.school.schooldeal.model.CommonRequest;

import java.util.List;

import butterknife.BindView;

/**
 * Created by 教科书式的机智少年 on 2017/2/26.
 */

public class MineSendSchoolActivity extends BaseActivity {
    @BindView(R.id.minesend_toobar)
    Toolbar toolbar;
    @BindView(R.id.minesend_recycler)
    RecyclerView recycler;
    @BindView(R.id.minesend_refresh)
    SwipeRefreshLayout refresh;
    private MineSendSchoolPresenter presenter;
    private MineSendAdapter adapter = new MineSendAdapter(this);
    @Override
    protected void initData() {
        refresh.setRefreshing(true);
        presenter = new MineSendSchoolPresenter(this,this);
        presenter.getData();
        initToolBar();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_minesend;
    }

    public void initToolBar(){
        toolbar.setTitle("我的发单");
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
    }

    public void setAdapterData(List<CommonRequest> list){
        adapter.setData(list);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(adapter);
    }

    private void setRefresh(){
        refresh.setColorSchemeResources(
                R.color.md_red_600,
                R.color.md_yellow_600,
                R.color.md_blue_600);
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.getnewData();
            }
        });
    }
    public void stopRefresh(){
        refresh.setRefreshing(false);
    }
    public void setnewData(List<CommonRequest> list){
        adapter.setData(list);
    }
}
