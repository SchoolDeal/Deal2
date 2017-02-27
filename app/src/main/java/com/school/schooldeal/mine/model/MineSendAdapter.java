package com.school.schooldeal.mine.model;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.school.schooldeal.R;
import com.school.schooldeal.model.CommonRequest;
import com.school.schooldeal.model.Store;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 教科书式的机智少年 on 2017/2/27.
 */

public class MineSendAdapter extends RecyclerView.Adapter<MySendViewHolder> {
    private List<CommonRequest> lists = new ArrayList<CommonRequest>();
    private Context context;

    public MineSendAdapter(Context context){
        this.context = context;
    }
    @Override
    public MySendViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MySendViewHolder(LayoutInflater.from(parent.getContext()).
                inflate(R.layout.item_minesend_school,parent,false));
    }

    @Override
    public void onBindViewHolder(MySendViewHolder holder, int position) {
        CommonRequest commonRequest = lists.get(position);
        Store store = commonRequest.getStore();
        if (commonRequest.getType() != 2){
            holder.getOver().setImageResource(R.mipmap.noover);
            holder.getOverHint().setText("未完成");
        }else {
            holder.getOver().setImageResource(R.mipmap.over);
            holder.getOverHint().setText("已完成");
        }
        if (commonRequest.getStoreType() == 1){
            holder.getImage().setImageResource(R.mipmap.shopping);
            holder.getTask().setText("购物");
        }if (commonRequest.getStoreType() == 2){
            holder.getImage().setImageResource(R.mipmap.foodtwo);
            holder.getTask().setText("带饭");
        }if (commonRequest.getStoreType() == 3){
            holder.getImage().setImageResource(R.mipmap.express);
            holder.getTask().setText("代取快递");
        }
        holder.getStorename().setText(store.getStoreName());
        holder.getDetailed().setText(commonRequest.getRequestContent());
        holder.getRefund().setText(commonRequest.getRemuneration()+"元");
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    public void setData(List<CommonRequest> lists){
        this.lists = lists;
        notifyDataSetChanged();
    }
}
