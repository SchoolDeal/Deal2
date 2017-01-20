package com.school.schooldeal.schooltask.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.school.schooldeal.R;
import com.school.schooldeal.base.BaseRecyclerAdapter;
import com.school.schooldeal.base.BaseViewHolder;

/**
 * Created by 教科书式的机智少年 on 2017/1/20.
 */

public class SchoolTaskDataAdapter extends BaseRecyclerAdapter<SchoolTaskOrderBean>{

    public SchoolTaskDataAdapter(Context context){
        super(context);
    }
    @Override
    protected void bindData(BaseViewHolder holder, SchoolTaskOrderBean item) {

    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SchoolTaskViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recycler_school_task,parent,false));
    }
}
