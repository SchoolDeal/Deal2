package com.school.schooldeal.takeout.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.school.schooldeal.R;
import com.school.schooldeal.base.BaseRecyclerAdapter;
import com.school.schooldeal.base.BaseViewHolder;
import com.school.schooldeal.takeout.TakeawayStatusConsts;
import com.school.schooldeal.takeout.model.bean.TakeOutOrderBean;

/**
 * Created by GavynZhang on 2017/2/22 1:04.
 */

public class TakeoutListAdapter extends BaseRecyclerAdapter<TakeOutOrderBean>{

    public TakeoutListAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getViewType(TakeOutOrderBean takeOutOrderBean) {
        return 0;
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
            capture.setBackgroundColor(getContext().getResources().getColor(R.color.md_grey_400));
        }else if (item.getStatus() == TakeawayStatusConsts.CANCELLED){
            capture.setClickable(false);
            capture.setText("已取消");
            capture.setBackgroundColor(getContext().getResources().getColor(R.color.md_grey_400));
        }else if (item.getStatus() == TakeawayStatusConsts.COMPLETED){
            capture.setClickable(false);
            capture.setText("已完成");
            capture.setBackgroundColor(getContext().getResources().getColor(R.color.md_grey_400));
        }
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }
}
