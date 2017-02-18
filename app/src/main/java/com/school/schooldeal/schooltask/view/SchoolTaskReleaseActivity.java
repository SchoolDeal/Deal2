package com.school.schooldeal.schooltask.view;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.school.schooldeal.R;
import com.school.schooldeal.base.BaseActivity;
import com.school.schooldeal.schooltask.presenter.SchoolTaskReasePresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by 教科书式的机智少年 on 2017/2/9.
 */

public class SchoolTaskReleaseActivity extends BaseActivity implements ImplSchoolTaskRelease,View.OnClickListener {
    private SchoolTaskReasePresenter presenter;
    @BindView(R.id.school_task_release_button)
    Button send;
    @BindView(R.id.school_task_release_toolbar)
    Toolbar toolbar;
    @BindView(R.id.school_task_release_arrow)
    ImageView arrow;
    @BindView(R.id.school_task_release_storename)
    TextView storename;
    @BindView(R.id.school_task_release_refund)
    EditText refund;
    @BindView(R.id.school_task_release_text)
    EditText content;
    @BindView(R.id.school_task_release_remarks)
    EditText remarks;
    private List<String> stores = new ArrayList<String>();

    @Override
    protected void initData() {
        initToolBar();
        presenter = new SchoolTaskReasePresenter(this,context);
        send.setOnClickListener(this);
        arrow.setOnClickListener(this);
        storename.setOnClickListener(this);
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
        switch (v.getId()){
            case R.id.school_task_release_button:

                break;
            case R.id.school_task_release_arrow:
            case R.id.school_task_release_storename:

                break;
            default:
                break;
        }
    }
}
