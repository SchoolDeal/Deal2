package com.school.schooldeal.takeout.view;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.school.schooldeal.R;
import com.school.schooldeal.base.BaseActivity;
import com.school.schooldeal.commen.util.ToastUtil;
import com.school.schooldeal.takeout.model.bean.TakeoutGenerateBean;
import com.school.schooldeal.takeout.presenter.TakeoutGeneratePresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TakeoutGenerateActivity extends BaseActivity implements ImplTakeoutGenerateActivity {

    private static final String className = "TOGActivity";

    @BindView(R.id.toolBar_takeoutGenerate)
    Toolbar mToolBarTakeoutGenerate;
    @BindView(R.id.school_takeoutGenerate)
    TextView mSchoolTakeoutGenerate;
    //    @BindView(R.id.schoolDropBtn_takeoutGenerate)
//    ImageButton mSchoolDropBtnTakeoutGenerate;
    @BindView(R.id.apartment_takeoutGenerate)
    TextView mApartmentTakeoutGenerate;
    //    @BindView(R.id.apartmentDropBtn_takeoutGenerate)
//    ImageButton mApartmentDropBtnTakeoutGenerate;
    @BindView(R.id.bedroomEdit_takeoutGenerate)
    EditText mBedroomEditTakeoutGenerate;
    @BindView(R.id.studentNameEdit_takeoutGenerate)
    EditText mStudentNameEditTakeoutGenerate;
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
    @BindView(R.id.schoolRl_takeoutGenerate)
    RelativeLayout mSchoolRlTakeoutGenerate;
    @BindView(R.id.apartmentRl_takeoutGenerate)
    RelativeLayout mApartmentRlTakeoutGenerate;

    TakeoutGeneratePresenter mPresenter = new TakeoutGeneratePresenter(this, context);


    //学校是否已选择
    private boolean schoolSelected = false;
    //公寓是否已选择
    private boolean apartmentSelected = false;

    MaterialDialog loadingDialog;

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

    /**
     * 保存到Bmob成功的回调
     */
    @Override
    public void saveSuccess() {
        finish();
    }

    /**
     * 加载学校信息回调
     *
     * @param schools 请求结果
     */
    @Override
    public void loadSchoolSuccess(List<String> schools) {
        loadingDialog.dismiss();
        MaterialDialog dialog = new MaterialDialog.Builder(this)
                .title("选择学校")
                //学校选择回调，设置文本以及传递选择内容至presenter
                .itemsCallback(new MaterialDialog.ListCallback() {
                    @Override
                    public void onSelection(MaterialDialog dialog, View itemView, int position, CharSequence text) {
                        //设置显示的文本内容
                        mSchoolTakeoutGenerate.setText(text);
                        //通知presenter学校的选择结果
                        mPresenter.setSelectedSchool(position);
                        //已选学校
                        schoolSelected = true;
                    }
                })
                .items(schools)
                .show();
    }

    /**
     * 加载公寓信息的回调
     *
     * @param apartments 请求结果
     */
    @Override
    public void loadApartmentSuccess(List<String> apartments) {
        loadingDialog.dismiss();
        MaterialDialog dialog = new MaterialDialog.Builder(this)
                .title("选择公寓")
                .items(apartments)
                .itemsCallback(new MaterialDialog.ListCallback() {
                    @Override
                    public void onSelection(MaterialDialog dialog, View itemView, int position, CharSequence text) {
                        mApartmentTakeoutGenerate.setText(text);
                        //通知presenter选择的结果
                        mPresenter.setSelectedApartment(position);
                        //已选公寓
                        apartmentSelected = true;
                    }
                })
                .show();
    }


    @OnClick({R.id.schoolRl_takeoutGenerate,
            R.id.apartmentRl_takeoutGenerate,
            R.id.generateRequest_takeoutGenerate})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.schoolRl_takeoutGenerate:
                //加载学校信息，完成后回调弹出学校的选择Dialog
                showLoading("选择学校", "加载中");
                mPresenter.loadSchool();
                break;
            case R.id.apartmentRl_takeoutGenerate:
                //如果已选择学校（包括缓存），则请求公寓数据
                Log.d(className, "click: R.id.apartmentDropBtn_takeoutGenerate:");
                if (schoolSelected) {
                    Log.d(className, "schoolSelected ");
                    showLoading("选择公寓", "加载中");
                    mPresenter.loadApartment();
                } else {
                    ToastUtil.makeShortToast(this, "请先选择学校");
                }
                break;
            case R.id.generateRequest_takeoutGenerate:
                generateTakeoutRequest();
                break;
        }
    }

    private void generateTakeoutRequest() {
        if (!schoolSelected
                || !apartmentSelected
                || "".equals(mBedroomEditTakeoutGenerate.getText().toString())
                || "".equals(mStudentNameEditTakeoutGenerate.getText().toString())
                || "".equals(mStudentPhoneEditTakeoutGenerate.getText().toString())
                || "".equals(mAmountEditTakeoutGenerate.getText().toString())
                || "".equals(mRemunerationEditTakeoutGenerate.getText().toString())) {
            ToastUtil.makeShortToast(this, "还没填完整哦！！");
        } else if (Float.valueOf(mRemunerationEditTakeoutGenerate.getText().toString()) >= 100) {
            ToastUtil.makeShortToast(context, "每份报酬不要超过100元");
        } else if (Integer.valueOf(mAmountEditTakeoutGenerate.getText().toString()) > 20) {
            ToastUtil.makeShortToast(context, "每次不能超过20份");
        } else {
            TakeoutGenerateBean generateBean = new TakeoutGenerateBean(
                    mStudentPhoneEditTakeoutGenerate.getText().toString(),
                    mStudentNameEditTakeoutGenerate.getText().toString(),
                    mBedroomEditTakeoutGenerate.getText().toString(),
                    Integer.parseInt(mAmountEditTakeoutGenerate.getText().toString()),
                    Float.parseFloat(mRemunerationEditTakeoutGenerate.getText().toString()));
            if (!"".equals(mRemarksEditTakeoutGenerate.getText().toString()))
                generateBean.setRemarks(mRemarksEditTakeoutGenerate.getText().toString());
            mPresenter.generateTakeawayServiceRequest(generateBean);
        }
    }

    private void showLoading(String title, String content) {
        loadingDialog = new MaterialDialog
                .Builder(this)
                .title(title)
                .content(content)
                .progress(true, 0)
                .show();
    }

}
