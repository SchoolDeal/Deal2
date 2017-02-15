package com.school.schooldeal.schooltask.view;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.school.schooldeal.R;
import com.school.schooldeal.base.BaseActivity;
import com.school.schooldeal.commen.util.ToastUtil;
import com.school.schooldeal.model.CommonRequest;
import com.school.schooldeal.schooltask.presenter.SchoolTaskDetailed;

import butterknife.BindView;

/**
 * Created by 教科书式的机智少年 on 2017/2/5.
 */

public class SchoolTaskDetailedActivity extends BaseActivity implements ImplSchoolTaskDetailedActivity,View.OnClickListener{
    @BindView(R.id.toolBar_school_task_detailed)
    Toolbar toolbar;
    @BindView(R.id.school_task_detailed_button)
    Button button;
    @BindView(R.id.school_task_detailed_destination)
    TextView destination;
    @BindView(R.id.school_task_detailed_task)
    TextView content;
    @BindView(R.id.school_task_detailed_money)
    TextView money;
    @BindView(R.id.school_task_detailed_phone)
    TextView phone;
    private CommonRequest commonRequest;
    private SchoolTaskDetailed schoolTaskDetailed;
    @Override
    protected void initData() {
        initToolBar();
        commonRequest = (CommonRequest)getIntent().getSerializableExtra("CommonRequest");
        schoolTaskDetailed = new SchoolTaskDetailed(context,this);
        button.setOnClickListener(this);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_schooltask_detailed;
    }
    private void initToolBar(){
        toolbar.setTitle("详细信息");
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
    }

    @Override
    public void onClick(View v) {
        schoolTaskDetailed.getrob();
    }

    @Override
    public void success() {
        ToastUtil.makeShortToast(context,"抢单成功");
    }

    @Override
    public void failed() {
        ToastUtil.makeShortToast(context,"抢单失败");
    }

    private void setData(){
        destination.setText(commonRequest.getStudent().getUsername());
        content.setText(commonRequest.getRequestContent());
        money.setText(commonRequest.getRemuneration()+"元");
        phone.setText(commonRequest.getStudent().getPhoneNum());
    }
}
