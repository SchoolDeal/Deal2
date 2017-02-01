package com.school.schooldeal.sign.view;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.rengwuxian.materialedittext.MaterialEditText;
import com.school.schooldeal.R;
import com.school.schooldeal.base.BaseActivity;
import com.school.schooldeal.commen.util.Util;
import com.school.schooldeal.sign.model.RestaurantUser;
import com.school.schooldeal.sign.model.StudentUser;

import java.text.ParseException;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bmob.v3.listener.SaveListener;

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
        if (Util.IS_STUDENT) showStudentSignUpView();
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
        if (Util.IS_STUDENT){
            signUpStudent();
        }else{
            signUpRestaurant();
        }
    }

    private void signUpStudent() {
        String phone = editPhone.getText().toString();
        String email = editEmail.getText().toString();
        String name = editName.getText().toString();
        int sex = Integer.parseInt(editSex.getText().toString());
        String password = editPassword.getText().toString();
        int school = Integer.parseInt(editSchool.getText().toString());
        String schoolNumber = editSchoolNumber.getText().toString();
        int apartment = Integer.parseInt(editApartment.getText().toString());

        StudentUser user = new StudentUser();
        user.setUsername(name);
        user.setPassword(password);
        user.setApartment(apartment);
        user.setSchool(school);
        user.setSchoolNumber(schoolNumber);
        user.setMobilePhoneNumber(phone);
        user.setEmail(email);
        user.setSex(sex);

        user.signUp(context, new SaveListener() {
            @Override
            public void onSuccess() {
                Toast.makeText(context,"sign up success",Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(int i, String s) {
                Toast.makeText(context,"sign false  "+s,Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void signUpRestaurant() {
        String phone = editPhone.getText().toString();
        String name = editName.getText().toString();
        String password = editPassword.getText().toString();

        RestaurantUser user = new RestaurantUser();
        user.setUsername(name);
        user.setPassword(password);
        user.setMobilePhoneNumber(phone);

        user.signUp(context, new SaveListener() {
            @Override
            public void onSuccess() {
                Toast.makeText(context,"sign up success",Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(int i, String s) {
                Toast.makeText(context,"sign false  "+s,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
