package com.school.schooldeal.sign.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.rengwuxian.materialedittext.MaterialEditText;
import com.school.schooldeal.R;
import com.school.schooldeal.base.BaseActivity;
import com.school.schooldeal.commen.util.SignUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by U-nookia on 2017/1/18.
 */

public class SignUpAcitivity extends BaseActivity {

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

    public static Intent getIntentToSignUpActivity(Context context) {
        Intent intent = new Intent(context, SignUpAcitivity.class);
        return intent;
    }

    @Override
    protected void initData() {
        checkUserIsStudentOrNot();
    }

    private void checkUserIsStudentOrNot() {
        if (SignUtil.IS_STUDENT) showStudentSignUpView();
        else showRestaurantSignUpView();
    }

    private void showRestaurantSignUpView() {
        editEmail.setVisibility(View.GONE);
        editSex.setVisibility(View.GONE);
        editSchool.setVisibility(View.GONE);
        editSchoolNumber.setVisibility(View.GONE);
        editApartment.setVisibility(View.GONE);
    }

    private void showStudentSignUpView() {

    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_signup;
    }

    @OnClick(R.id.signUp)
    public void onClick() {

    }
}
