package com.school.schooldeal.takeout.view;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;

import com.school.schooldeal.R;
import com.school.schooldeal.base.BaseActivity;
import com.school.schooldeal.commen.util.ToastUtil;
import com.school.schooldeal.takeout.model.TakeoutGenerateBean;
import com.school.schooldeal.takeout.presenter.TakeoutGeneratePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TakeoutGenerateActivity extends BaseActivity implements ImplTakeoutGenerateActivity {

    @BindView(R.id.toolBar_takeoutGenerate)
    Toolbar mToolBarTakeoutGenerate;
    @BindView(R.id.destinationEdit_takeoutGenerate)
    EditText mDestinationEditTakeoutGenerate;
    @BindView(R.id.studentPhoneEdit_takeoutGenerate)
    EditText mStudentPhoneEditTakeoutGenerate;
    @BindView(R.id.amountEdit_takeoutGenerate)
    EditText mAmountEditTakeoutGenerate;
    @BindView(R.id.remunerationEdit_takeoutGenerate)
    EditText mRemunerationEditTakeoutGenerate;
    @BindView(R.id.remarksEdit_takeoutGenerate)
    EditText mRemarksEditTakeoutGenerate;
    @BindView(R.id.generateRequest_takeoutGenerate)
    Button mGenerateRequestTakeoutGenerate;
    @BindView(R.id.studentNameEdit_takeoutGenerate)
    EditText mStudentNameEditTakeoutGenerate;

    TakeoutGeneratePresenter mPresenter = new TakeoutGeneratePresenter(this, context);


    @Override
    protected void initData() {
        initToolbar();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_takeout_generate;
    }

    private void initToolbar() {
        mToolBarTakeoutGenerate.setTitle("新送货请求");
        mToolBarTakeoutGenerate.setTitleTextColor(getResources().getColor(R.color.white));
    }

    @OnClick(R.id.generateRequest_takeoutGenerate)
    public void onClick() {
        if (mDestinationEditTakeoutGenerate.getText().toString().equals("") ||
                mStudentPhoneEditTakeoutGenerate.getText().toString().equals("")||
                mAmountEditTakeoutGenerate.getText().toString().equals("") ||
                mRemunerationEditTakeoutGenerate.getText().toString().equals("")||
                mStudentNameEditTakeoutGenerate.getText().toString().equals("")) {

            ToastUtil.makeShortToast(TakeoutGenerateActivity.this, "还没填完整哦！！");

        } else {
            //生成外卖服务请求单
            TakeoutGenerateBean generateBean = new TakeoutGenerateBean();
            generateBean.setDestination(mDestinationEditTakeoutGenerate.getText().toString());
            generateBean.setStuPhoneNum(mStudentPhoneEditTakeoutGenerate.getText().toString());
            generateBean.setAmount(Integer.parseInt(mAmountEditTakeoutGenerate.getText().toString()));
            generateBean.setRemuneration(Float.parseFloat(mRemunerationEditTakeoutGenerate.getText().toString()));
            generateBean.setStudentName(mStudentNameEditTakeoutGenerate.getText().toString());
            if (!mRemarksEditTakeoutGenerate.getText().toString().equals(""))
                generateBean.setRemarks(mRemarksEditTakeoutGenerate.getText().toString());
            mPresenter.generateTakeawayServiceRequest(generateBean);
        }
    }

}
