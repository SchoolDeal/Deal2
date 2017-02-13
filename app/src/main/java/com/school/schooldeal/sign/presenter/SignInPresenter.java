package com.school.schooldeal.sign.presenter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;
import com.school.schooldeal.ConnectLisenter;
import com.school.schooldeal.ServerConnectManager;
import com.school.schooldeal.commen.util.ToastUtil;
import com.school.schooldeal.commen.util.Util;
import com.school.schooldeal.sign.model.RestaurantUser;
import com.school.schooldeal.sign.model.StudentUser;
import com.school.schooldeal.sign.view.ImplSignIn;
import com.school.schooldeal.sign.view.SignUpAcitivity;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.SaveListener;

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
        signIn.showDialog("请稍候","正在登陆用户.......");
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
                signIn.dismissDialog();
                Toast.makeText(context,s,Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getToken(){
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

    public void checkIfFirstTimeLogin() {
        Util.IS_STUDENT = sp.getBoolean("isStudent",false);
        if (Util.IS_STUDENT){
            StudentUser user = BmobUser.getCurrentUser(context,StudentUser.class);
            if (user!=null) notFirstTimeLogin();
        }else if (!Util.IS_STUDENT){
            RestaurantUser user = BmobUser.getCurrentUser(context,RestaurantUser.class);
            if (user!=null) notFirstTimeLogin();
        }
    }

    /**
     * 检测到不是第一次登陆，两个操作
     * 1.将界面清空,显示dialog
     * 2.从缓存中拿到token连接服务器，跳转到main界面
     */
    private void notFirstTimeLogin() {
        clearView();
        signIn.showDialog("请稍候","正在连接用户......");
        getTokenFromCacheAndConnect();
    }

    private void getTokenFromCacheAndConnect() {
        String cacheToken = sp.getString("loginToken", "");
        if (cacheToken==null||cacheToken.equals("")) {
            signIn.dismissDialog();
            signIn.showView();
            ToastUtil.makeLongToast(context,"无本地缓存token，请重新登陆获取token");
        }
        if (TextUtils.isEmpty(cacheToken)) {
            return;
        } else {
            signIn.connectRongServer(cacheToken);
        }
    }

    private void clearView() {
        signIn.clearView();
    }

    public void putBoolean(boolean isStudent) {
        editor.putBoolean("isStudent",isStudent);
        editor.apply();
    }
}
