package com.school.schooldeal.mine.view;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.school.schooldeal.R;
import com.school.schooldeal.base.BaseActivity;
import com.school.schooldeal.model.CommonRequest;

import butterknife.BindView;

/**
 * Created by 教科书式的机智少年 on 2017/2/27.
 */

public class MineSendDetailedActivity extends BaseActivity {
    @BindView(R.id.toolBar_school_task_detailed)
    Toolbar toolBarSchoolTaskDetailed;
    @BindView(R.id.school_task_detailed_destination)
    TextView schoolTaskDetailedDestination;
    @BindView(R.id.school_task_detailed_task)
    TextView schoolTaskDetailedTask;
    @BindView(R.id.school_task_detailed_money)
    TextView schoolTaskDetailedMoney;
    @BindView(R.id.school_task_detailed_phone)
    TextView schoolTaskDetailedPhone;
    @BindView(R.id.school_task_detailed_button)
    Button schoolTaskDetailedButton;
    @BindView(R.id.school_task_detailed_img)
    ImageView schoolTaskDetailedImg;
    private CommonRequest request;
    @Override
    protected void initData() {
        request = (CommonRequest) getIntent().getSerializableExtra("data");
        toolBarSchoolTaskDetailed.setTitle("我的发单");
        toolBarSchoolTaskDetailed.setTitleTextColor(getResources().getColor(R.color.white));
        schoolTaskDetailedDestination.setText(request.getStore().getStoreName());
        schoolTaskDetailedMoney.setText(request.getRemuneration()+"元");
        schoolTaskDetailedTask.setText(request.getRequestContent());
        schoolTaskDetailedPhone.setText(request.getStudent().getPhoneNum());
        if (request.getType()!= 2){
            schoolTaskDetailedButton.setText("完成");
        }else {
            schoolTaskDetailedButton.setText("已完成");
            schoolTaskDetailedButton.setClickable(false);
        }
        switch (request.getStoreType()){
            case 1:
                schoolTaskDetailedImg.setImageResource(R.mipmap.express_min);
                break;
            case 2:
                schoolTaskDetailedImg.setImageResource(R.mipmap.food_min);
                break;
            case 3:
                schoolTaskDetailedImg.setImageResource(R.mipmap.shopping_min);
                break;
        }
        schoolTaskDetailedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                request.setType(2);
                request.update(context);
                finish();
            }
        });
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_schooltask_detailed;
    }
}
