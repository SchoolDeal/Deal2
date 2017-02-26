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

public class TakeOutDataAdapter extends BaseRecyclerAdapter<TakeOutOrderBean> implements ImplCaptureRequest{

    private static final String className = "TODataAdapter";

    private OnTakeoutItemClickListener mOnTakeoutItemClickListener;
    private GenerateService mGenerateService = new GenerateService(getContext(), this);

    private int captureItemPosition;


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

        if (!Util.IS_STUDENT) {
            capture.setClickable(false);
            if (item.getStatus() == TakeawayStatusConsts.NOT_BEING_TAKEN){
                capture.setText("未被抢");
                capture.setBackgroundColor(getContext().getResources().getColor(R.color.md_amber_400));
            } else if (item.getStatus() == TakeawayStatusConsts.HAS_BEING_TAKEN) {
                capture.setText("已被抢");
                capture.setBackgroundColor(getContext().getResources().getColor(R.color.md_grey_300));
            } else if (item.getStatus() == TakeawayStatusConsts.CANCELLED) {
                capture.setText("已取消");
                capture.setBackgroundColor(getContext().getResources().getColor(R.color.md_grey_300));
            } else if (item.getStatus() == TakeawayStatusConsts.COMPLETED) {
                capture.setText("已完成");
                capture.setBackgroundColor(getContext().getResources().getColor(R.color.md_grey_300));
            }
        }
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TakeOutViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recycler_take_out,parent,false));
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, final int position) {
        super.onBindViewHolder(holder, position);
        //final int a = position;
        holder.getView(R.id.capture).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //mOnTakeoutItemClickListener.onItemClick(v, getLists().get(a));
                captureItemPosition = position;
                if (Util.IS_STUDENT) {
                    //mGenerateService.generateService(getLists().get(captureItemPosition), GenerateService.ADAPTER);
                    mGenerateService.queryTheRequestStatus(getLists().get(captureItemPosition));
                }
            }
        });
        holder.getView(R.id.card_takeout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //mOnTakeoutItemClickListener.onItemClick(v, getLists().get(a));
                Log.d(className, "position: "+position+" id: "+getLists().get(position).getId());
                TakeoutDetailsActivity.actionStart(getContext(), getLists().get(position).getId());
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

    /**
     * 抢单成功的回调
     */
    @Override
    public void captureRequestSuccess() {
        //通知list进行移除操作
        getLists().remove(captureItemPosition);
        notifyItemRemoved(captureItemPosition);
        notifyItemRangeChanged(captureItemPosition, getItemCount());
    }

    /**
     * 请求已被抢的回调
     */
    @Override
    public void requestHasBeenCaptured() {
        ToastUtil.makeShortToast(getContext(), "抱歉，该订单已被抢。");
        getLists().remove(captureItemPosition);
        notifyItemRemoved(captureItemPosition);
        notifyItemRangeChanged(captureItemPosition, getItemCount());
    }

    /**
     * 请求未被抢的回调
     */
    @Override
    public void requestIsNotCaptured() {
        mGenerateService.generateService(getLists().get(captureItemPosition), GenerateService.ADAPTER);
    }

    public interface OnTakeoutItemClickListener{
        void onItemClick(View view, TakeOutOrderBean orderBean);
    }
}
