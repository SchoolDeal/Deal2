package com.school.schooldeal.mine.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.school.schooldeal.R;
import com.school.schooldeal.mine.model.ReceivedSchoolAdapter;
import com.school.schooldeal.mine.presenter.MineOverSchoolPresenter;
import com.school.schooldeal.model.CommonService;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 教科书式的机智少年 on 2017/2/27.
 */

public class MineOverSchoolFragment extends Fragment {
    @BindView(R.id.fragment_school_recycler)
    RecyclerView recycler;
    @BindView(R.id.fragment_school_refresh)
    SwipeRefreshLayout refresh;
    private MineOverSchoolPresenter presenter;
    private ReceivedSchoolAdapter adapter = new ReceivedSchoolAdapter(getContext());
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_school, container, false);
        ButterKnife.bind(this, view);
        initData();
        return view;
    }

    public void initData(){
        refresh.setRefreshing(true);
        presenter = new MineOverSchoolPresenter(getContext(),this);
        setRefresh();
    }

    private void setRefresh(){
        refresh.setColorSchemeResources(
                R.color.md_red_600,
                R.color.md_yellow_600,
                R.color.md_blue_600);
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.getData();
            }
        });
    }

    public void setAdapter(List<CommonService> list){
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter.setData(list);
    }

    public void stopRefresh(){
        refresh.setRefreshing(false);
    }

    public void setnewData(List<CommonService> list){
        adapter.setData(list);
    }
}
