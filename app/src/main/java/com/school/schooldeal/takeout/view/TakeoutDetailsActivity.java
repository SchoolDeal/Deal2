package com.school.schooldeal.takeout.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.school.schooldeal.R;
import com.school.schooldeal.base.BaseActivity;
import com.school.schooldeal.commen.util.ToastUtil;
import com.school.schooldeal.commen.util.Util;
import com.school.schooldeal.model.TakeawayRequest;
import com.school.schooldeal.model.TakeawayService;
import com.school.schooldeal.takeout.TakeawayStatusConsts;
import com.school.schooldeal.takeout.model.bean.TakeOutOrderBean;
import com.school.schooldeal.takeout.presenter.TakeOutDetailsPresenter;

import butterknife.BindView;
import butterknife.OnClick;

public class TakeoutDetailsActivity extends BaseActivity implements ImplTakeoutDetailsActivity {

    public static final String className = "TODetailsActivity";

    @BindView(R.id.toolBar_takeoutDetails)
    Toolbar mToolBarTakeoutDetails;
    @BindView(R.id.destination_takeoutDetails)
    TextView mDestinationTakeoutDetails;
    @BindView(R.id.business_takeoutDetails)
    TextView mBusinessTakeoutDetails;
    @BindView(R.id.bizAddress_takeoutDetails)
    TextView mBizAddressTakeoutDetails;
    @BindView(R.id.bizPhoneNum_takeoutDetails)
    TextView mBizPhoneNumTakeoutDetails;
    @BindView(R.id.money_takeoutDetails)
    TextView mMoneyTakeoutDetails;
    @BindView(R.id.amount_takeout)
    TextView mAmountTakeout;
    @BindView(R.id.restaurant_img)
    ImageView mRestaurantImg;
    @BindView(R.id.capture_detail)
    Button mCaptureDetail;
    @BindView(R.id.studentName_takeoutDetails)
    TextView mStudentNameTakeoutDetails;
    @BindView(R.id.studentPhoneNum_takeoutDetails)
    TextView mStudentPhoneNumTakeoutDetails;
    @BindView(R.id.RlForBusinessShow_takeoutDetails)
    RelativeLayout mRlForBusinessShowTakeoutDetails;

    private TakeOutDetailsPresenter mPresenter = new TakeOutDetailsPresenter(this, this);
    private String requestID = "";

    /**
     * 用于启动本Activity
     *
     * @param context
     * @param requestID 外卖请求的ID
     */
    public static void actionStart(Context context, String requestID) {
        Intent intent = new Intent(context, TakeoutDetailsActivity.class);
        intent.putExtra("requestID", requestID);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        Intent intent = getIntent();
//        this.requestID = intent.getStringExtra("requestID");

        if (Util.IS_STUDENT){
            mRlForBusinessShowTakeoutDetails.setVisibility(View.GONE);
        }
    }

    @Override
    protected void initData() {
        initToolbar();
        Intent intent = getIntent();
        requestID = intent.getStringExtra("requestID");
        Log.d("deal", "requestID: " + requestID);
        if (!"".equals(requestID))
            mPresenter.loadTakeawayDetails(requestID);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_takeout_details;
    }

    @Override
    public void showDetailsData(TakeOutOrderBean takeOutOrderBean) {

    }

    @Override
    public void showRestaurantPicture(Bitmap picture) {
        mRestaurantImg.setImageBitmap(picture);
    }

    /**
     * presenter加载成功的回调
     *
     * @param takeawayRequest 数据bean
     */
    @Override
    public void showDetails(TakeawayRequest takeawayRequest) {
        Log.d(className, "TakeawayRequest: " + takeawayRequest.toString());
        mDestinationTakeoutDetails.setText(takeawayRequest.getApartment().getApartmentName() + takeawayRequest.getBedroom() + "寝室");
        mBusinessTakeoutDetails.setText(takeawayRequest.getRestaurant().getName());
        mBizAddressTakeoutDetails.setText(takeawayRequest.getRestaurant().getAddress());
        mBizPhoneNumTakeoutDetails.setText(takeawayRequest.getRestaurant().getMobilePhoneNumber());
        mAmountTakeout.setText(takeawayRequest.getAmount().toString() + "份");
        mMoneyTakeoutDetails.setText("￥" + String.valueOf(takeawayRequest.getRemuneration()));
        if (!Util.IS_STUDENT) {
            mCaptureDetail.setClickable(false);
            mCaptureDetail.setBackgroundColor(getResources().getColor(R.color.md_amber_400));
            if (takeawayRequest.getStatus() == TakeawayStatusConsts.NOT_BEING_TAKEN) {
                mCaptureDetail.setText("暂未被抢");
                mRlForBusinessShowTakeoutDetails.setVisibility(View.GONE);
            }
            else if (takeawayRequest.getStatus() == TakeawayStatusConsts.HAS_BEING_TAKEN) {
                mCaptureDetail.setText("已被抢");
                mPresenter.loadStudentInfo();
            } else if (takeawayRequest.getStatus() == TakeawayStatusConsts.COMPLETED) {
                mCaptureDetail.setText("已完成");
            }

        }
    }

    @Override
    public void captureRequestSuccess() {
        finish();
    }

    /**
     * 在本方法中展示学生相关信息，仅当前登录用户为餐馆以及该单已被学生抢走时调用
     *
     * @param service
     */
    @Override
    public void showStudentInfo(TakeawayService service) {
        if (!"".equals(service.getStudent().getUsername()))
            mStudentNameTakeoutDetails.setText(service.getStudent().getUsername());
        if (!"".equals(service.getStudent().getMobilePhoneNumber()))
            mStudentPhoneNumTakeoutDetails.setText(service.getStudent().getMobilePhoneNumber());
    }

    /**
     * 该单已被抢的回调
     */
    @Override
    public void requestHasBeenCaptured() {
        ToastUtil.makeShortToast(context, "对不起，该订单已被抢");
        finish();
    }

    /**
     * 初始化Toolbar
     */
    private void initToolbar() {
        mToolBarTakeoutDetails.setTitle("订单详情");
        mToolBarTakeoutDetails.setTitleTextColor(getResources().getColor(R.color.white));
        if (Util.IS_STUDENT) {
            mToolBarTakeoutDetails.setBackgroundColor(getResources().getColor(R.color.barBackColor));
        } else {
            mToolBarTakeoutDetails.setBackgroundColor(getResources().getColor(R.color.md_teal_400));
        }
    }

    @OnClick({R.id.restaurant_img, R.id.capture_detail})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.restaurant_img:
                break;
            case R.id.capture_detail:
                mPresenter.captureRequest();
                break;
        }
    }
}
