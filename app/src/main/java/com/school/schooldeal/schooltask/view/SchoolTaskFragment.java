package com.school.schooldeal.schooltask.view;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.school.schooldeal.R;
import com.school.schooldeal.base.BaseFragment;
import com.school.schooldeal.schooltask.model.SchoolTaskDataAdapter;
import com.school.schooldeal.schooltask.presenter.SchoolTaskPresenter;

import butterknife.BindView;

/**
 * Created by U-nookia on 2016/12/20.
 */

public class SchoolTaskFragment extends BaseFragment implements ImplSchoolTaskFragment{
    @BindView(R.id.schoolTaskRecycler)
    RecyclerView schoolTaskRecyclerView;
    private SchoolTaskPresenter presenter;
    @Override
    protected void initData() {
        initRecyclerView();
        presenter = new SchoolTaskPresenter(getContext(),this);
        presenter.initAdapter();
    }

    @Override
    protected int getResourceId() {
        return R.layout.fragment_schooltask;
    }

    @Override
    public void setAdapter(SchoolTaskDataAdapter adapter) {
        schoolTaskRecyclerView.setAdapter(adapter);
    }
    private void initRecyclerView(){
        schoolTaskRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}
