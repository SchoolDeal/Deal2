package com.school.schooldeal.message.view;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.school.schooldeal.R;
import com.school.schooldeal.base.BaseFragment;
import com.school.schooldeal.message.model.MessageAdapter;
import com.school.schooldeal.message.presenter.MessagePresenter;

import butterknife.BindView;

/**
 * Created by U-nookia on 2016/12/20.
 */

public class MessageFragment extends BaseFragment
        implements ImplMessageFragment{


    @BindView(R.id.message_recycler)
    RecyclerView messageRecycler;
    private MessagePresenter presenter;

    @Override
    protected void initData() {
        initRecycler();
        initPresenter();
        presenter.initAdapter();
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
    }

}
