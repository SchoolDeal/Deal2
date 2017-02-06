package com.school.schooldeal.schooltask.model;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.school.schooldeal.R;
import com.school.schooldeal.base.BaseViewHolder;

/**
 * Created by 教科书式的机智少年 on 2017/1/20.
 */

public class SchoolTaskViewHolder extends BaseViewHolder implements View.OnClickListener{
    private SchoolTaskDataAdapter.OnSchoolTaskItemClickListener onItemClickListener = null;
    private Context context;
    private Button rob;
    private CardView root;

    public SchoolTaskViewHolder(View itemView, SchoolTaskDataAdapter.OnSchoolTaskItemClickListener onItemClickListener, Context context) {
        super(itemView);
        this.onItemClickListener = onItemClickListener;
        this.context = context;
        root = (CardView)itemView.findViewById(R.id.school_task_card_all);
        rob = (Button)itemView.findViewById(R.id.rob_school_task_item);
        root.setOnClickListener(this);
        rob.setOnClickListener(this);
    }

    @Override
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
    }
}
