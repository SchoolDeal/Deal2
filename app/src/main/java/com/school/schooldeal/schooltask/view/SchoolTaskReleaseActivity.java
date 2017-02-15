package com.school.schooldeal.schooltask.view;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.school.schooldeal.R;
import com.school.schooldeal.base.BaseActivity;
import com.school.schooldeal.schooltask.presenter.SchoolTaskReasePresenter;

import butterknife.BindView;

/**
 * Created by 教科书式的机智少年 on 2017/2/9.
 */

public class SchoolTaskReleaseActivity extends BaseActivity implements ImplSchoolTaskRelease,View.OnClickListener {
    private SchoolTaskReasePresenter presenter;
    @BindView(R.id.school_task_release_toolbar)
    Toolbar toolbar;
    @BindView(R.id.school_task_release_destination)
    EditText destination;
    @BindView(R.id.school_task_release_name)
    EditText name;
    @BindView(R.id.school_task_release_phone)
    EditText phone;
    @BindView(R.id.school_task_release_refund)
    EditText refund;
    @BindView(R.id.school_task_release_text)
    EditText text;
    @BindView(R.id.school_task_release_button)
    Button send;

    @Override
    protected void initData() {
        initToolBar();
        presenter = new SchoolTaskReasePresenter(this,context);
        send.setOnClickListener(this);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_schooltask_release;
    }

    private void initToolBar(){
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        toolbar.setTitle("发布");
    }

    @Override
    public void onClick(View v) {
        String strDestination;
        String strName;
        String strPhone;
        String strRefund;
        String strText;
        strDestination = destination.getText().toString();
        strName = name.getText().toString();
        strPhone = phone.getText().toString();
        strRefund = refund.getText().toString();
        strText = text.getText().toString();
        /*if (strDestination.isEmpty()&&
                strName.isEmpty()&&
                strPhone.isEmpty()&&
                strRefund.isEmpty()&&
                strText.isEmpty()){
            ToastUtil.makeShortToast(context,"请完善订单信息");
        }
        else {
            presenter.sendMessage(strDestination,strName,strPhone,strRefund,strText);
        }*/
        presenter.sendMessage(strDestination,strName,strPhone,strRefund,strText);
    }
}
