package com.school.schooldeal.sign.view;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.rengwuxian.materialedittext.validation.RegexpValidator;
import com.school.schooldeal.MainActivity;
import com.school.schooldeal.R;
import com.school.schooldeal.base.BaseActivity;
import com.school.schooldeal.commen.util.ConstUtils;
import com.school.schooldeal.commen.util.Located;
import com.school.schooldeal.commen.util.ToastUtil;
import com.school.schooldeal.commen.util.Util;
import com.school.schooldeal.sign.model.RestaurantUser;
import com.school.schooldeal.sign.model.StudentUser;

import java.text.ParseException;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bmob.v3.datatype.BmobGeoPoint;
import cn.bmob.v3.listener.SaveListener;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;

/**
 * Created by U-nookia on 2017/1/18.
 */

public class SignUpAcitivity extends BaseActivity
        implements View.OnFocusChangeListener,AMapLocationListener{

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

    //private MaterialDialog dialog;
    private int sex,school,apartment;
    private double longtitude,latitude;
    private String address;
    private Located located;

    public static Intent getIntentToSignUpActivity(Context context) {
        Intent intent = new Intent(context, SignUpAcitivity.class);
        return intent;
    }

    @Override
    protected void initData() {
        checkUserIsStudentOrNot();
    }

    private void checkUserIsStudentOrNot() {
        if (!Util.IS_STUDENT) showRestaurantSignUpView();
        else setEdit();
    }

    private void setEdit() {
        editSex.setOnFocusChangeListener(this);
        editSchool.setOnFocusChangeListener(this);
        editApartment.setOnFocusChangeListener(this);
    }

    private void showRestaurantSignUpView() {
        editEmail.setVisibility(View.GONE);
        editSex.setVisibility(View.GONE);
        editSchool.setVisibility(View.GONE);
        editSchoolNumber.setVisibility(View.GONE);
        editApartment.setVisibility(View.GONE);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_signup;
    }

    @OnClick(R.id.signUp)
    public void onClick() {
        if (checkUserName()){
            if (Util.IS_STUDENT){
                signUpStudent();
            }else{
                located = new Located(context);
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && this.checkSelfPermission(ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED
                        &&this.checkSelfPermission(ACCESS_FINE_LOCATION)!=PackageManager.PERMISSION_GRANTED){
                    requestPermissions(new String[]{ACCESS_COARSE_LOCATION,ACCESS_FINE_LOCATION},0);
                }
                located.starLocated(this);
                signUpRestaurant();
            }
        }
    }

    private boolean checkUserName() {
        if (!editName.validateWith(
                new RegexpValidator("姓名中含有非法字符", ConstUtils.REGEX_USERNAME))){
            return false;
        }
        return true;
    }

    private void signUpStudent() {
        String phone = editPhone.getText().toString();
        String email = editEmail.getText().toString();
        String name = editName.getText().toString();
        String password = editPassword.getText().toString();
        String schoolNumber = editSchoolNumber.getText().toString();

        if (name.equals("")||password.equals("")||schoolNumber.equals("")||phone.equals("")||email.equals("")||apartment==0||school==0||sex==0){
            ToastUtil.makeShortToast(context, "出现输入错误");
            return;
        }else {
            StudentUser user = new StudentUser(context,name,password,apartment,school,schoolNumber,
                    phone,email,sex);
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

    private void signUpRestaurant() {
        String phone = editPhone.getText().toString();
        String name = editName.getText().toString();
        String password = editPassword.getText().toString();

        RestaurantUser user = new RestaurantUser();
        user.setUsername(name);
        user.setPassword(password);
        user.setMobilePhoneNumber(phone);
        user.setAddress(address);
        user.setName("渝州铁板烧");
        user.setLatitude(latitude);
        user.setLongitude(longtitude);
        user.setPosition(new BmobGeoPoint(longtitude, latitude));
        //测试使用
        //user.setPosition(new BmobGeoPoint(106.23384, 29.27353666666667));


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

    @OnClick(R.id.edit_sex)
    public void clickSex(){
        new MaterialDialog.Builder(context)
                .items(R.array.sex)
                .itemsCallback(new MaterialDialog.ListCallback() {
                    @Override
                    public void onSelection(MaterialDialog dialog, View itemView, int position, CharSequence text) {
                        sex = position+1;
                        editSex.setText(text);
                    }
                }).build().show();
    }

    @OnClick(R.id.edit_school)
    public void clickSchool(){
        new MaterialDialog.Builder(context)
                .items(R.array.school)
                .itemsCallback(new MaterialDialog.ListCallback() {
                    @Override
                    public void onSelection(MaterialDialog dialog, View itemView, int position, CharSequence text) {
                        school = position+1;
                        editSchool.setText(text);
                    }
                }).build().show();
    }

    @OnClick(R.id.edit_apartment)
    public void clickApartment(){
        String[] apartments = Util.getApartmentArray(context,school);
        new MaterialDialog.Builder(context)
                .items(apartments)
                .itemsCallback(new MaterialDialog.ListCallback() {
                    @Override
                    public void onSelection(MaterialDialog dialog, View itemView, int position, CharSequence text) {
                        apartment = position+1;
                        editApartment.setText(text);
                    }
                }).build().show();
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        switch (v.getId()){
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
        if (aMapLocation!= null){
            if (aMapLocation.getErrorCode() == 0){
                //这里写想要做的事
                ToastUtil.makeShortToast(context,"located");
                address = aMapLocation.getAddress();
                longtitude = aMapLocation.getLongitude();
                latitude = aMapLocation.getLatitude();
            }else {
                Log.e("AmapError","location Error, ErrCode:"
                        + aMapLocation.getErrorCode() + ", errInfo:"
                        + aMapLocation.getErrorInfo());
            }
        }
    }
}
