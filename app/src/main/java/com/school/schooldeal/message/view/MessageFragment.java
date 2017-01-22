package com.school.schooldeal.message.view;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.school.schooldeal.R;
import com.school.schooldeal.base.BaseFragment;
import com.school.schooldeal.message.model.MessageAdapter;
import com.school.schooldeal.message.presenter.MessagePresenter;

import butterknife.BindView;

/**
 * Created by U-nookia on 2016/12/20.
 */

public class MessageFragment extends BaseFragment
        implements ImplMessageFragment,SwipeRefreshLayout.OnRefreshListener{


    @BindView(R.id.message_recycler)
    RecyclerView messageRecycler;
    @BindView(R.id.empty)
    ImageView empty;
    @BindView(R.id.refresh)
    SwipeRefreshLayout refresh;
    private MessagePresenter presenter;

    @Override
    protected void initData() {
        initRecycler();
        initRefreshLayout();
        initPresenter();
        presenter.initAdapter();
    }

    private void initRefreshLayout() {
        refresh.setOnRefreshListener(this);
    }

    private void initPresenter() {
        presenter = new MessagePresenter(getContext(), this);
    }

    private void initRecycler() {
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        messageRecycler.setLayoutManager(manager);
    }

    @Override
    protected int getResourceId() {
        return R.layout.fragment_message;
    }

    @Override
    public void setAdapter(MessageAdapter adapter) {
        messageRecycler.setAdapter(adapter);
        checkIfEmpty(adapter);
    }

    @Override
    public void stopRefresh() {
        refresh.setRefreshing(false);
    }

    private void checkIfEmpty(MessageAdapter adapter) {
        if (adapter.getItemCount()==0){
            messageRecycler.setVisibility(View.GONE);
            empty.setVisibility(View.VISIBLE);
        }else {
            messageRecycler.setVisibility(View.VISIBLE);
            empty.setVisibility(View.GONE);
        }
    }

    @Override
    public void onRefresh() {
        presenter.onRefresh();
    }

}
