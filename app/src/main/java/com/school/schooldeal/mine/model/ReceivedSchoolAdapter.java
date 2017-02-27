package com.school.schooldeal.mine.model;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.school.schooldeal.R;
import com.school.schooldeal.model.CommonRequest;
import com.school.schooldeal.model.CommonService;
import com.school.schooldeal.model.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 教科书式的机智少年 on 2017/2/26.
 */

public class ReceivedSchoolAdapter extends RecyclerView.Adapter<ReceivedSchoolViewHolder> {
    private List<CommonService> lists = new ArrayList<CommonService>();
    private Context context;

    public ReceivedSchoolAdapter(Context context){
        this.context = context;
    }
    @Override
    public ReceivedSchoolViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ReceivedSchoolViewHolder(LayoutInflater.from(parent.getContext()).
                inflate(R.layout.item_minereceive_school,parent,false));
    }

    @Override
    public void onBindViewHolder(ReceivedSchoolViewHolder holder, int position) {
        CommonService service = lists.get(position);
        CommonRequest request = service.getRequest();
        Student student = service.getStudent();
        if (request.getType() != 2){
            holder.getOver().setImageResource(R.mipmap.noover);
            holder.getOverHint().setText("未完成");
        }else {
            holder.getOver().setImageResource(R.mipmap.over);
            holder.getOverHint().setText("已完成");
        }
        switch (request.getStoreType()){
            case 1:
                holder.getImage().setImageResource(R.mipmap.express);
                holder.getTask().setText("取快递");
                break;
            case 2:
                holder.getImage().setImageResource(R.mipmap.foodtwo);
                holder.getTask().setText("带饭");
                break;
            case 3:
                holder.getImage().setImageResource(R.mipmap.shopping);
                holder.getTask().setText("买东西");
                break;
        }
        holder.getBedroom().setText(student.getBedroom());
        holder.getContent().setText(request.getRequestContent());
        holder.getRefund().setText(request.getRemuneration()+"元");
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    public void setData(List<CommonService> lists){
        this.lists = lists;
        notifyDataSetChanged();
    }
}
