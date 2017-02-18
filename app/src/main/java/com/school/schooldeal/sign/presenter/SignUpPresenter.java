package com.school.schooldeal.sign.presenter;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.school.schooldeal.R;
import com.school.schooldeal.commen.util.ToastUtil;
import com.school.schooldeal.commen.util.Util;
import com.school.schooldeal.model.Apartment;
import com.school.schooldeal.model.School;
import com.school.schooldeal.sign.model.ImplSignUpModel;
import com.school.schooldeal.sign.model.RestaurantUser;
import com.school.schooldeal.sign.model.SignUpModel;
import com.school.schooldeal.sign.model.StudentUser;
import com.school.schooldeal.sign.view.ImplSignUp;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.datatype.BmobGeoPoint;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by U-nookia on 2017/1/18.
 */

public class SignUpPresenter implements ListToDialog{
    private ImplSignUp signUp;
    private Context context;
    private int sex;
    private School school;
    private Apartment apartment;
    private double longtitude,latitude;
    private String address;
    private boolean schoolSelected;
    private ImplSignUpModel signUpModel;
    private List<School> schools;
    private List<Apartment> apartments;

    public SignUpPresenter(Context context, ImplSignUp signUp) {
        this.context = context;
        this.signUp = signUp;
        if (Util.IS_STUDENT){
            signUpModel = new SignUpModel(context,this);
            schools = new ArrayList<>();
            apartments = new ArrayList<>();
        }
    }

    public void checkUserIsStudentOrNot() {
        if (!Util.IS_STUDENT) signUp.showRestaurantSignUpView();
        else signUp.setEdit();
    }

    public void signUpStudent() {
        String phone = signUp.getPhone();
        String email = signUp.getEmail();
        String name = signUp.getName();
        String password = signUp.getPassword();
        String schoolNumber = signUp.getSchoolNumber();

        if (name.equals("")||password.equals("")||schoolNumber.equals("")||phone.equals("")||email.equals("")||apartment==null||school==null||sex==0){
            ToastUtil.makeShortToast(context, "出现输入错误");
            return;
        }else {
            StudentUser user = new StudentUser(context,name,password,apartment,school,schoolNumber,
                    phone,email,sex);
            user.setStudent(true);
            user.signUp(context, new SaveListener() {
                @Override
                public void onSuccess() {
                    Toast.makeText(context,"sign up success",Toast.LENGTH_SHORT).show();
                    signUp.finishView();
                }
                @Override
                public void onFailure(int i, String s) {
                    Toast.makeText(context,"sign false  "+s,Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public void signUpRestaurant() {
        String phone = signUp.getPhone();
        String name = signUp.getName();
        String password = signUp.getPassword();

        RestaurantUser user = new RestaurantUser(name,password,phone
                ,address,"渝州铁板烧",latitude,longtitude
                ,new BmobGeoPoint(longtitude, latitude));
        user.setStudent(false);

        user.signUp(context, new SaveListener() {
            @Override
            public void onSuccess() {
                Toast.makeText(context,"sign up success",Toast.LENGTH_SHORT).show();
                signUp.finishView();
            }
            @Override
            public void onFailure(int i, String s) {
                Toast.makeText(context,"sign false  "+s,Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void chooseSex() {
        new MaterialDialog.Builder(context)
                .items(R.array.sex)
                .itemsCallback(new MaterialDialog.ListCallback() {
                    @Override
                    public void onSelection(MaterialDialog dialog, View itemView, int position, CharSequence text) {
                        sex = position+1;
                        signUp.setSex(text);
                    }
                }).build().show();
    }

    private void chooseSchool(final List<String> schoolList) {
        new MaterialDialog.Builder(context)
                .items(schoolList)
                .itemsCallback(new MaterialDialog.ListCallback() {
                    @Override
                    public void onSelection(MaterialDialog dialog, View itemView, int position, CharSequence text) {
                        signUp.setSchool(text);
                        setSchool(position);
                        schoolSelected = true;
                    }
                }).build().show();
    }

    public void chooseApartMent(final List<String> apartmentList) {
        new MaterialDialog.Builder(context)
                .items(apartmentList)
                .itemsCallback(new MaterialDialog.ListCallback() {
                    @Override
                    public void onSelection(MaterialDialog dialog, View itemView, int position, CharSequence text) {
                        setApartment(position);
                        signUp.setApartment(text);
                    }
                }).build().show();
    }


    public void setAddress(String address) {
        this.address = address;
    }

    public void setLongtitude(double longitude) {
        this.longtitude = longitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void clickSchoolEdit() {
        signUpModel.getSchoolListFromBmob();
    }

    @Override
    public void setSchoolListToDialog(List<School> schoolList) {
        schools = schoolList;
        List<String> schoolNames = new ArrayList<>();
        for (School school:schoolList){
            schoolNames.add(school.getSchoolName());
        }
        chooseSchool(schoolNames);
    }

    private void setSchool(int position) {
        school = schools.get(position);
    }

    private void setApartment(int position){
        apartment = apartments.get(position);
    }

    @Override
    public void setApartmentListToDialog(List<Apartment> list) {
        apartments = list;
        List<String> apartmentNames = new ArrayList<>();
        for (Apartment apartment:list){
            apartmentNames.add(apartment.getApartmentName());
        }
        chooseApartMent(apartmentNames);
    }

    public void clickApartmentEdit() {
        if (!schoolSelected){
            ToastUtil.makeShortToast(context,"请先选择学校");
            return;
        }
        signUpModel.getApartmentListFromBmob(school);
    }
}
