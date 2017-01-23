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
 * 泛型里传入该适配器中数据集合的类型
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

    @Override
    public int getItemViewType(int position) {
        return getViewType(lists.get(position));
    }

    protected abstract int getViewType(T t);

    public Context getContext(){
        return context;
    }

    //用于设置集合中的数据
    public void setData(List<T> lists){
        this.lists = lists;
        notifyDataSetChanged();
    }

    //用于绑定数据和视图的抽象方法
    protected abstract void bindData(BaseViewHolder holder,T item);
}
