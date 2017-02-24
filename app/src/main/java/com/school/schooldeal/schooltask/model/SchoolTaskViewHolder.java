package com.school.schooldeal.schooltask.model;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.school.schooldeal.R;
import com.school.schooldeal.base.BaseViewHolder;

/**
 * Created by 教科书式的机智少年 on 2017/1/20.
 */

public class SchoolTaskViewHolder extends BaseViewHolder{
    //private SchoolTaskDataAdapter.OnSchoolTaskItemClickListener onItemClickListener = null;
    private Context context;
    private Button rob;
    private CardView root;
    private TextView location;
    private TextView describe;
    private TextView remuneration;
    private TextView storename;

    public SchoolTaskViewHolder(View itemView,Context context) {
        super(itemView);
        this.context = context;
        root = (CardView)itemView.findViewById(R.id.school_task_card_all);
        rob = (Button)itemView.findViewById(R.id.rob_school_task_item);
        location = (TextView)itemView.findViewById(R.id.destination_school_task_order);
        describe = (TextView)itemView.findViewById(R.id.describe_school_task_item);
        remuneration = (TextView)itemView.findViewById(R.id.remuneration_school_task_item);
        storename = (TextView)itemView.findViewById(R.id.start_school_task_item);
        /*root.setOnClickListener(this);
        rob.setOnClickListener(this);*/
    }

    /*@Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.school_task_card_all:
                Log.e("data","1");
                if (onItemClickListener != null){
                    onItemClickListener.onItemClick(root);
                }
                break;
            case R.id.rob_school_task_item:
                Log.e("data","2");
                Toast.makeText(context, "抢单成功", Toast.LENGTH_SHORT).show();
                break;
        }
    }*/

    public TextView getLocation() {
        return location;
    }

    public void setLocation(TextView location) {
        this.location = location;
    }

    public TextView getDescribe() {
        return describe;
    }

    public void setDescribe(TextView describe) {
        this.describe = describe;
    }

    public TextView getRemuneration() {
        return remuneration;
    }

    public void setRemuneration(TextView remuneration) {
        this.remuneration = remuneration;
    }

    public Button getRob() {
        return rob;
    }

    public void setRob(Button rob) {
        this.rob = rob;
    }

    public CardView getRoot() {
        return root;
    }

    public void setRoot(CardView root) {
        this.root = root;
    }

    public TextView getStorename() {
        return storename;
    }

    public void setStorename(TextView storename) {
        this.storename = storename;
    }
}
