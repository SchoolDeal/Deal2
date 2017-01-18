package com.school.schooldeal.takeout.view;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.school.schooldeal.R;
import com.school.schooldeal.adapter.TakeOutDataAdapter;
import com.school.schooldeal.base.BaseFragment;
import com.school.schooldeal.takeout.presenter.TakeOutPresenter;

import butterknife.BindView;

/**
 * Created by U-nookia on 2016/12/19.
 * 外卖界面的view
 */

public class TakeOutFragment extends BaseFragment
        implements ImplTakeOutFragment {

    @BindView(R.id.takeOutRecycler)
    RecyclerView takeOutRecycler;

    private TakeOutPresenter presenter;

    @Override
    protected void initData() {
        initRecycler();
        presenter = new TakeOutPresenter(getContext(),this);
        presenter.initAdapter();
    }

    private void initRecycler() {
        takeOutRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    protected int getResourceId() {
        return R.layout.fragment_takeout;
    }

    @Override
    public void setAdapter(TakeOutDataAdapter adapter) {
        takeOutRecycler.setAdapter(adapter);
    }
}
