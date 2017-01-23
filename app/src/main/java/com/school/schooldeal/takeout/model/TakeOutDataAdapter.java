package com.school.schooldeal.takeout.model;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.school.schooldeal.R;
import com.school.schooldeal.base.BaseRecyclerAdapter;
import com.school.schooldeal.base.BaseViewHolder;
import com.school.schooldeal.takeout.view.TakeoutDetailsActivity;

/**
 * Created by U-nookia on 2016/12/23.
 * 外卖界面的recyclerView数据适配器
 */

public class TakeOutDataAdapter extends BaseRecyclerAdapter<TakeOutOrderBean> implements View.OnClickListener{

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

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        holder.getView(R.id.card_takeout).setOnClickListener(this);
        holder.getView(R.id.capture).setOnClickListener(this);
    }

    @Override
    protected int getViewType(TakeOutOrderBean takeOutOrderBean) {
        return 0;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.card_takeout:
                Intent intent = new Intent(getContext(), TakeoutDetailsActivity.class);
                getContext().startActivity(intent);
                break;
            case R.id.capture:
                Toast.makeText(getContext(), "抢单成功", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
