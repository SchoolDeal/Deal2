package com.school.schooldeal.sign.presenter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.school.schooldeal.ConnectLisenter;
import com.school.schooldeal.MainActivity;
import com.school.schooldeal.ServerConnectManager;
import com.school.schooldeal.application.MyApplication;
import com.school.schooldeal.commen.util.ToastUtil;
import com.school.schooldeal.commen.util.Util;
import com.school.schooldeal.sign.model.RestaurantUser;
import com.school.schooldeal.sign.model.StudentUser;
import com.school.schooldeal.sign.view.ImplSignIn;
import com.school.schooldeal.sign.view.SignInAcitivty;
import com.school.schooldeal.sign.view.SignUpAcitivity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.SaveListener;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;

/**
 * Created by U-nookia on 2017/1/18.
 */

public class SignInPresenter implements ConnectLisenter{
    private ImplSignIn signIn;
    private Context context;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private ServerConnectManager manager;

    public SignInPresenter(Context context,ImplSignIn signIn) {
        this.signIn = signIn;
        this.context = context;
        sp = context.getSharedPreferences("config",Context.MODE_PRIVATE);
        editor = sp.edit();
        manager = new ServerConnectManager();
        manager.setServerConnectManager(this);
    }

    public void signUp(){
        Intent intent = SignUpAcitivity.getIntentToSignUpActivity(context);
        context.startActivity(intent);
    }

    public void signIn() {
        final String name = signIn.getUserName();
        String password = signIn.getUserPassword();
        final StudentUser studentUser = new StudentUser();
        studentUser.setUsername(name);
        studentUser.setPassword(password);
        studentUser.login(context, new SaveListener() {
            @Override
            public void onSuccess() {
                getToken();
            }

            @Override
            public void onFailure(int i, String s) {
                Toast.makeText(context,s,Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getToken(){
        if (Util.IS_STUDENT) {
            StudentUser user = BmobUser.getCurrentUser(context,StudentUser.class);
            String id = user.getObjectId();
            String name = user.getUsername();
            String url = Util.img_hhh;
            manager.getToken(id,name,url);
        }else {
            RestaurantUser user = BmobUser.getCurrentUser(context,RestaurantUser.class);
            String id = user.getObjectId();
            String name = user.getUsername();
            String url = Util.img_10086;
            manager.getToken(id,name,url);
        }
    }

    private void connectRongServer(final String token) {
        Log.d("bbb",token);
        signIn.connectRongServer(token);
    }

    public void putTokenToSharedPreferences(String token){
        editor.putString("loginToken",token);
        editor.apply();
    }

    @Override
    public void connect(String token) {
        connectRongServer(token);
    }
}
