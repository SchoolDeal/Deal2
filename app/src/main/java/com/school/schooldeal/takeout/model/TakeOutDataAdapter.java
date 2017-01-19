package com.school.schooldeal.takeout.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.school.schooldeal.R;
import com.school.schooldeal.base.BaseRecyclerAdapter;
import com.school.schooldeal.base.BaseViewHolder;
import com.school.schooldeal.takeout.model.TakeOutOrderBean;
import com.school.schooldeal.takeout.model.TakeOutViewHolder;

/**
 * Created by U-nookia on 2016/12/23.
 * 外卖界面的recyclerView数据适配器
 */

public class TakeOutDataAdapter extends BaseRecyclerAdapter<TakeOutOrderBean>{

    public TakeOutDataAdapter(Context context) {
        super(context);
    }

    @Override
    protected void bindData(BaseViewHolder holder, TakeOutOrderBean item) {
        TextView textView = holder.getView(R.id.amount_take_out_order);
        textView.setText(item.getAmount()+"");
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TakeOutViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recycler_take_out,parent,false));
    }
}
