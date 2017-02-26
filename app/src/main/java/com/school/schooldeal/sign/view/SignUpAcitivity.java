package com.school.schooldeal.sign.view;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.afollestad.materialdialogs.MaterialDialog;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.rengwuxian.materialedittext.validation.RegexpValidator;
import com.school.schooldeal.R;
import com.school.schooldeal.base.BaseActivity;
import com.school.schooldeal.commen.util.ConstUtils;
import com.school.schooldeal.commen.util.Located;
import com.school.schooldeal.commen.util.ToastUtil;
import com.school.schooldeal.commen.util.Util;
import com.school.schooldeal.sign.presenter.SignUpPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;

/**
 * Created by U-nookia on 2017/1/18.
 */

public class SignUpAcitivity extends BaseActivity
        implements View.OnFocusChangeListener, AMapLocationListener, ImplSignUp {

    @BindView(R.id.edit_phone)
    MaterialEditText editPhone;
    @BindView(R.id.edit_email)
    MaterialEditText editEmail;
    @BindView(R.id.edit_name)
    MaterialEditText editName;
    @BindView(R.id.edit_sex)
    MaterialEditText editSex;
    @BindView(R.id.edit_password)
    MaterialEditText editPassword;
    @BindView(R.id.edit_school)
    MaterialEditText editSchool;
    @BindView(R.id.edit_school_number)
    MaterialEditText editSchoolNumber;
    @BindView(R.id.edit_apartment)
    MaterialEditText editApartment;
    @BindView(R.id.signUp)
    Button signUp;
    @BindView(R.id.edit_bedroom)
    MaterialEditText mEditBedroom;
    @BindView(R.id.edit_address)
    MaterialEditText editAddress;

    private SignUpPresenter presenter;
    private Located located;

    public static Intent getIntentToSignUpActivity(Context context) {
        Intent intent = new Intent(context, SignUpAcitivity.class);
        return intent;
    }

    @Override
    protected void initData() {
        presenter = new SignUpPresenter(context, this);
        presenter.checkUserIsStudentOrNot();
        if (!Util.IS_STUDENT) startLocated();
    }

    private void startLocated() {
        located = new Located(context);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && this.checkSelfPermission(ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && this.checkSelfPermission(ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{ACCESS_COARSE_LOCATION, ACCESS_FINE_LOCATION}, 0);
        }
        located.starLocated(this);
    }

    @Override
    public void setEdit() {
        editSex.setOnFocusChangeListener(this);
        editSchool.setOnFocusChangeListener(this);
        editApartment.setOnFocusChangeListener(this);
    }

    @Override
    public void showRestaurantSignUpView() {
        editEmail.setVisibility(View.GONE);
        editSex.setVisibility(View.GONE);
        editSchool.setVisibility(View.GONE);
        editSchoolNumber.setVisibility(View.GONE);
        editApartment.setVisibility(View.GONE);
        mEditBedroom.setVisibility(View.GONE);
        editAddress.setVisibility(View.VISIBLE);
    }

    @Override
    public String getAddress(){
        return editAddress.getText().toString();
    }

    @Override
    public String getPhone() {
        return editPhone.getText().toString();
    }

    @Override
    public String getEmail() {
        return editEmail.getText().toString();
    }

    @Override
    public String getName() {
        return editName.getText().toString();
    }

    @Override
    public String getPassword() {
        return editPassword.getText().toString();
    }

    @Override
    public String getSchoolNumber() {
        return editSchoolNumber.getText().toString();
    }

    @Override
    public void finishView() {
        finish();
    }

    @Override
    public void setSex(CharSequence text) {
        editSex.setText(text);
    }

    @Override
    public void setSchool(CharSequence text) {
        editSchool.setText(text);
    }

    @Override
    public void setApartment(CharSequence text) {
        editApartment.setText(text);
    }

    @Override
    public void setBedroom(CharSequence text) {
        mEditBedroom.setText(text);
    }

    @Override
    public void setAddress(String address) {
        editAddress.setText(address);
    }

    @Override
    public String getBedroom(){
        return mEditBedroom.getText().toString();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_signup;
    }

    @OnClick(R.id.signUp)
    public void onClick() {
        if (checkUserName()) {
            if (Util.IS_STUDENT) {
                presenter.signUpStudent();
            } else {
                presenter.signUpRestaurant();
            }
        }
    }

    private boolean checkUserName() {
        if (!editName.validateWith(
                new RegexpValidator("不符合长度要求或姓名中含有非法字符", ConstUtils.REGEX_USERNAME))) {
            return false;
        }
        return true;
    }

    @OnClick(R.id.edit_sex)
    public void clickSex() {
        presenter.chooseSex();
    }

    @OnClick(R.id.edit_school)
    public void clickSchool() {
        presenter.clickSchoolEdit();
    }

    @OnClick(R.id.edit_apartment)
    public void clickApartment() {
        presenter.clickApartmentEdit();
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        switch (v.getId()) {
            case R.id.edit_sex:
                if (hasFocus) clickSex();
                break;
            case R.id.edit_school:
                if (hasFocus) clickSchool();
                break;
            case R.id.edit_apartment:
                if (hasFocus) clickApartment();
                break;
        }
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (aMapLocation != null) {
            if (aMapLocation.getErrorCode() == 0) {
                ToastUtil.makeShortToast(context, "located");
                presenter.setAddress(aMapLocation.getAddress());
                presenter.setLongtitude(aMapLocation.getLongitude());
                presenter.setLatitude(aMapLocation.getLatitude());
            } else {
                Log.e("AmapError", "location Error, ErrCode:"
                        + aMapLocation.getErrorCode() + ", errInfo:"
                        + aMapLocation.getErrorInfo());
                ToastUtil.makeShortToast(context, aMapLocation.getErrorInfo());
            }
        }
    }
}
