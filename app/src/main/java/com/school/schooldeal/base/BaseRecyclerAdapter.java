package com.school.schooldeal.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by U-nookia on 2016/12/22.
 */

public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {

    private List<T> lists;
    private Context context;

    public BaseRecyclerAdapter(Context context) {
        this.context = context;
        lists = new ArrayList<>();
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        bindData(holder,lists.get(position));
    }

    public Context getContext(){
        return context;
    }

    public void setData(List<T> lists){
        this.lists = lists;
        notifyDataSetChanged();
    }

    protected abstract void bindData(BaseViewHolder holder,T item);
}
