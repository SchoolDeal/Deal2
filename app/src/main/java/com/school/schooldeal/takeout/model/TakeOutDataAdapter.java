package com.school.schooldeal.takeout.model;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.school.schooldeal.R;
import com.school.schooldeal.base.BaseRecyclerAdapter;
import com.school.schooldeal.base.BaseViewHolder;
import com.school.schooldeal.commen.util.ToastUtil;
import com.school.schooldeal.commen.util.Util;
import com.school.schooldeal.takeout.TakeawayStatusConsts;
import com.school.schooldeal.takeout.model.bean.TakeOutOrderBean;
import com.school.schooldeal.takeout.view.TakeoutDetailsActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by U-nookia on 2016/12/23.
 * 外卖界面的recyclerView数据适配器
 */

public class TakeOutDataAdapter extends BaseRecyclerAdapter<TakeOutOrderBean>{

    private static final String className = "TODataAdapter";

    private OnTakeoutItemClickListener mOnTakeoutItemClickListener;


    //private List<String> requestIDs = new ArrayList<>();

    public TakeOutDataAdapter(Context context) {
        super(context);
    }

    @Override
    protected void bindData(BaseViewHolder holder, TakeOutOrderBean item) {
        TextView amount = holder.getView(R.id.amount_take_out_order);
        TextView destination = holder.getView(R.id.destination_take_out_order);
        TextView money = holder.getView(R.id.money_take_out_order);
        TextView restaurantAddress = holder.getView(R.id.address_take_out_order);
        TextView restaurantName = holder.getView(R.id.business_take_out_order);
        Button capture = holder.getView(R.id.capture);
        amount.setText(item.getAmount()+"");
        destination.setText(item.getDestination());
        money.setText(item.getMoney().toString());
        restaurantAddress.setText(item.getRestaurantAddress());
        restaurantName.setText(item.getRestaurantName());

        if (item.getStatus() == TakeawayStatusConsts.HAS_BEING_TAKEN){
            capture.setText("已被抢");
            capture.setClickable(false);
            capture.setBackgroundColor(getContext().getResources().getColor(R.color.md_grey_300));
        }else if (item.getStatus() == TakeawayStatusConsts.CANCELLED){
            capture.setClickable(false);
            capture.setText("已取消");
            capture.setBackgroundColor(getContext().getResources().getColor(R.color.md_grey_300));
        }else if (item.getStatus() == TakeawayStatusConsts.COMPLETED){
            capture.setClickable(false);
            capture.setText("已完成");
            capture.setBackgroundColor(getContext().getResources().getColor(R.color.md_grey_300));
        }
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TakeOutViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recycler_take_out,parent,false));
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        final int a = position;
        holder.getView(R.id.capture).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //mOnTakeoutItemClickListener.onItemClick(v, getLists().get(a));
                if (Util.IS_STUDENT) {
                    GenerateService.generateService(getContext(), getLists().get(a));
                    ToastUtil.makeShortToast(getContext(), "抢单成功");
                    //通知list进行移除操作
                    getLists().remove(a);
                    notifyItemRemoved(a);
                    notifyItemRangeChanged(a, getItemCount());
                }
            }
        });
        holder.getView(R.id.card_takeout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //mOnTakeoutItemClickListener.onItemClick(v, getLists().get(a));
                Log.d(className, "position: "+a+" id: "+getLists().get(a).getId());
                TakeoutDetailsActivity.actionStart(getContext(), getLists().get(a).getId());
            }
        });
    }

    @Override
    protected int getViewType(TakeOutOrderBean takeOutOrderBean) {
        return 0;
    }

    public void setOnTakeoutItemClickListener(OnTakeoutItemClickListener onTakeoutItemClickListener) {
        mOnTakeoutItemClickListener = onTakeoutItemClickListener;
    }

    public interface OnTakeoutItemClickListener{
        void onItemClick(View view, TakeOutOrderBean orderBean);
    }
}
