package com.school.schooldeal;

import android.content.Context;

import com.school.schooldeal.sign.model.RestaurantUser;
import com.school.schooldeal.sign.model.StudentUser;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by U-nookia on 2017/2/24.
 */

public class MainActivityModel implements ImplMainModel {

    private List<BmobUser> userList;
    private static MainActivityModel model;
    private static Context mContext;

    private MainActivityModel() {
        userList = new ArrayList<>();
    }

    public static MainActivityModel getModelInstance(Context context){
        mContext = context;
        if (model==null){
            model = new MainActivityModel();
            return model;
        }else return model;
    }

    @Override
    public void getUserListFromBmob(){
        addStudentsAndRestaurants(userList);
    }

    @Override
    public List<BmobUser> getUserList(){
        return userList;
    }

    @Override
    public BmobUser queryUser(String id){
        for (BmobUser user : userList){
            if (user.getObjectId().equals(id)) return user;
        }
        return null;
    }

    private void addStudentsAndRestaurants(final List<BmobUser> users) {
        BmobQuery<StudentUser> studentUsersQuery = new BmobQuery<>();
        studentUsersQuery.addWhereNotEqualTo("username","");
        studentUsersQuery.findObjects(mContext, new FindListener<StudentUser>() {
            @Override
            public void onSuccess(List<StudentUser> list) {
                users.addAll(list);
                addRestaurants(users);
            }

            @Override
            public void onError(int i, String s) {

            }
        });
    }

    private void addRestaurants(final List<BmobUser> users) {
        BmobQuery<RestaurantUser> studentUsersQuery = new BmobQuery<>();
        studentUsersQuery.addWhereNotEqualTo("username","");
        studentUsersQuery.findObjects(mContext, new FindListener<RestaurantUser>() {
            @Override
            public void onSuccess(List<RestaurantUser> list) {
                users.addAll(list);
            }

            @Override
            public void onError(int i, String s) {

            }
        });
    }
}
