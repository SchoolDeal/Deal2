package com.school.schooldeal.sign.model;

import android.content.Context;

import com.school.schooldeal.commen.util.ToastUtil;
import com.school.schooldeal.model.Apartment;
import com.school.schooldeal.model.School;
import com.school.schooldeal.sign.presenter.ListToDialog;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by U-nookia on 2017/2/18.
 */

public class SignUpModel implements ImplSignUpModel{
    private Context context;
    private ListToDialog ListToDialog;

    public SignUpModel(Context context,ListToDialog ListToDialog) {
        this.context = context;
        this.ListToDialog = ListToDialog;
    }

    @Override
    public void getSchoolListFromBmob(){
        BmobQuery<School> querySchool = new BmobQuery<>();
        querySchool.addWhereNotEqualTo("schoolName","");
        querySchool.findObjects(context, new FindListener<School>() {
            @Override
            public void onSuccess(List<School> list) {
                ListToDialog.setSchoolListToDialog(list);
            }

            @Override
            public void onError(int i, String s) {
                ToastUtil.makeShortToast(context,"未获取到学校数据");
                ListToDialog.getSchoolOrApartMsgError(s);
            }
        });
    }

    @Override
    public void getApartmentListFromBmob(School school) {
        BmobQuery<Apartment> queryApartment = new BmobQuery<>();
        queryApartment.addWhereEqualTo("school",school);
        queryApartment.findObjects(context, new FindListener<Apartment>() {
            @Override
            public void onSuccess(List<Apartment> list) {
                ListToDialog.setApartmentListToDialog(list);
            }

            @Override
            public void onError(int i, String s) {
                ToastUtil.makeShortToast(context,"未获取到公寓数据");
                ListToDialog.getSchoolOrApartMsgError(s);
            }
        });
    }
}
