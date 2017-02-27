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
import com.school.schooldeal.mine.presenter.ReceivedSchoolPresenter;
import com.school.schooldeal.model.CommonService;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 教科书式的机智少年 on 2017/2/25.
 */

public class MineReceivedSchoolFragment extends Fragment {
    @BindView(R.id.fragment_school_recycler)
    RecyclerView recycler;
    @BindView(R.id.fragment_school_refresh)
    SwipeRefreshLayout refresh;
    private ReceivedSchoolPresenter presenter;
    private ReceivedSchoolAdapter adapter = new ReceivedSchoolAdapter(getContext());

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_school, container, false);
        ButterKnife.bind(this, view);
        initData();
        return view;
    }

    public void setAdapter(List<CommonService> list) {
        adapter.setData(list);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        recycler.setAdapter(adapter);
    }

    public void initData() {
        presenter = new ReceivedSchoolPresenter(this, getContext());
        presenter.getData();
        setRefresh();
        refresh.setRefreshing(true);
    }

    public void stopRefresh() {
        refresh.setRefreshing(false);
    }

    private void setRefresh(){
        refresh.setColorSchemeResources(
                R.color.md_red_600,
                R.color.md_yellow_600,
                R.color.md_blue_600);
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.setnewData();
            }
        });
    }

    public void setnewData(List<CommonService> list){
        adapter.setData(list);
    }


}
