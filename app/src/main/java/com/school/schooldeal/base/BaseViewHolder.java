package com.school.schooldeal.base;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;

/**
 * Created by U-nookia on 2016/12/22.
 */

public abstract class BaseViewHolder extends RecyclerView.ViewHolder {

    private SparseArray<View> views;
    private View itemView;

    public BaseViewHolder(View itemView) {
        super(itemView);
        this.itemView = itemView;
        this.views = new SparseArray<>();
    }

    //通过传入的viewId和viewHolder拿到的view获取到某个子view的抽象方法
    public <T extends View> T getView(int viewId){
        View view = views.get(viewId);
        if (view==null){
            view = itemView.findViewById(viewId);
            views.put(viewId,view);
        }
        return (T)view;
    }
}
