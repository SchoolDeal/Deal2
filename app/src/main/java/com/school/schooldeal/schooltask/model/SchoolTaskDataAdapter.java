package com.school.schooldeal.schooltask.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.school.schooldeal.R;
import com.school.schooldeal.base.BaseRecyclerAdapter;
import com.school.schooldeal.base.BaseViewHolder;

/**
 * Created by 教科书式的机智少年 on 2017/1/20.
 */

public class SchoolTaskDataAdapter extends BaseRecyclerAdapter<SchoolTaskOrderBean>{
    private OnSchoolTaskItemClickListener onItemClickListener = null;
    private Context context;

    public SchoolTaskDataAdapter(Context context){
        super(context);
        this.context = context;
    }

    @Override
    protected int getViewType(SchoolTaskOrderBean schoolTaskOrderBean) {
        return 0;
    }

    @Override
    protected void bindData(BaseViewHolder holder, SchoolTaskOrderBean item) {

    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SchoolTaskViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recycler_school_task,parent,false),onItemClickListener,context);
    }
    public void setOnItemClickListener(OnSchoolTaskItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnSchoolTaskItemClickListener{
        void onItemClick(View view);
    }
}

