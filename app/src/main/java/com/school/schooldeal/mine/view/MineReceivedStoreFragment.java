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
import com.school.schooldeal.base.BaseFragment;
import com.school.schooldeal.commen.util.ToastUtil;
import com.school.schooldeal.mine.presenter.MineReceivedStorePresenter;
import com.school.schooldeal.takeout.model.TakeOutDataAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 教科书式的机智少年 on 2017/2/25.
 */

public class MineReceivedStoreFragment extends BaseFragment implements ImplMineReceivedStoreFragment {

    public static final String className = "MReceivedRFragment";

    @BindView(R.id.takeOutRecycler_mineReceived)
    RecyclerView mTakeOutRecyclerMineReceived;
    @BindView(R.id.swipeRefresh_mineReceived)
    SwipeRefreshLayout mSwipeRefreshMineReceived;

    private MineReceivedStorePresenter mPresenter;

//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_store, container, false);
//        ButterKnife.bind(this, view);
//        return view;
//    }

    @Override
    protected void initData() {
        initRecycler();
        mPresenter = new MineReceivedStorePresenter(getContext(), this);
        mPresenter.initAdapter();
        initSwipeRefresh();
    }

    @Override
    protected int getResourceId() {
        return R.layout.fragment_store;
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    private void initRecycler(){
        mTakeOutRecyclerMineReceived.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void initSwipeRefresh(){
        mSwipeRefreshMineReceived.setColorSchemeResources(
                R.color.md_red_600,
                R.color.md_yellow_600,
                R.color.md_blue_600);
        mSwipeRefreshMineReceived.setRefreshing(true);
        mSwipeRefreshMineReceived.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.initAdapter();
            }
        });
    }

    @Override
    public void setAdapter(TakeOutDataAdapter adapter) {
        mTakeOutRecyclerMineReceived.setAdapter(adapter);
    }

    @Override
    public void loadSuccess() {
        mSwipeRefreshMineReceived.setRefreshing(false);
    }

    @Override
    public void loadDataEmpty() {
        mSwipeRefreshMineReceived.setRefreshing(false);
        ToastUtil.makeShortToast(getContext(), "数据为空");
    }
}
