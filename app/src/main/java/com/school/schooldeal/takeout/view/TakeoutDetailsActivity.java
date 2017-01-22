package com.school.schooldeal.takeout.view;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.school.schooldeal.R;
import com.school.schooldeal.base.BaseActivity;
import com.school.schooldeal.takeout.model.TakeOutOrderBean;

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


    @Override
    protected void initData() {
        initToolbar();
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

    private void initToolbar(){
        mToolBarTakeoutDetails.setTitle("订单详情");
        mToolBarTakeoutDetails.setTitleTextColor(getResources().getColor(R.color.white));
    }

}
