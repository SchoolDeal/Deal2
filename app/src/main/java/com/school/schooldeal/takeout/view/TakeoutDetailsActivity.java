package com.school.schooldeal.takeout.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.school.schooldeal.R;
import com.school.schooldeal.base.BaseActivity;
import com.school.schooldeal.model.TakeawayRequest;
import com.school.schooldeal.takeout.model.bean.TakeOutOrderBean;
import com.school.schooldeal.takeout.presenter.TakeOutDetailsPresenter;

import butterknife.BindView;

public class TakeoutDetailsActivity extends BaseActivity implements ImplTakeoutDetailsActivity {

    @BindView(R.id.toolBar_takeoutDetails)
    Toolbar mToolBarTakeoutDetails;
    @BindView(R.id.restaurant_img)
    ImageView mRestaurantImg;
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
    @BindView(R.id.capture_detail)
    Button mCaptureDetail;

    private TakeOutDetailsPresenter mPresenter = new TakeOutDetailsPresenter(this, this);
    private String requestID = "";

    /**
     * 用于启动本Activity
     * @param context
     * @param requestID 外卖请求的ID
     */
    public static void actionStart(Context context, String requestID){
        Intent intent = new Intent(context, TakeoutDetailsActivity.class);
        intent.putExtra("requestID",requestID);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        Intent intent = getIntent();
//        this.requestID = intent.getStringExtra("requestID");
    }

    @Override
    protected void initData() {
        initToolbar();
        Intent intent = getIntent();
        requestID = intent.getStringExtra("requestID");
        Log.d("deal", "requestID: "+requestID);
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
     * @param takeawayRequest 数据bean
     */
    @Override
    public void showDetails(TakeawayRequest takeawayRequest) {
        mDestinationTakeoutDetails.setText(takeawayRequest.getApartment().getApartmentName()+takeawayRequest.getBedroom());
        mBusinessTakeoutDetails.setText(takeawayRequest.getRestaurant().getName());
        mBizAddressTakeoutDetails.setText(takeawayRequest.getRestaurant().getAddress());
        mBizPhoneNumTakeoutDetails.setText(takeawayRequest.getRestaurant().getMobilePhoneNumber());
        mAmountTakeout.setText(takeawayRequest.getAmount().toString()+"份");
        mMoneyTakeoutDetails.setText("￥"+String.valueOf(takeawayRequest.getRemuneration()));
    }

    /**
     * 初始化Toolbar
     */
    private void initToolbar(){
        mToolBarTakeoutDetails.setTitle("订单详情");
        mToolBarTakeoutDetails.setTitleTextColor(getResources().getColor(R.color.white));
    }

}
