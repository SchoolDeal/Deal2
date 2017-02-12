package com.school.schooldeal.sign.presenter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.school.schooldeal.MainActivity;
import com.school.schooldeal.application.MyApplication;
import com.school.schooldeal.commen.util.ToastUtil;
import com.school.schooldeal.commen.util.Util;
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

import cn.bmob.v3.listener.SaveListener;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;

/**
 * Created by U-nookia on 2017/1/18.
 */

public class SignInPresenter {
    private ImplSignIn signIn;
    private Context context;
    private String line = null;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    public SignInPresenter(Context context,ImplSignIn signIn) {
        this.signIn = signIn;
        this.context = context;
        sp = context.getSharedPreferences("config",Context.MODE_PRIVATE);
        editor = sp.edit();
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
                /*String id = studentUser.getObjectId();
                getToken(id,name,"");
                connectRongServer(line);*/
                context.startActivity(MainActivity.getIntentToMainActivity(context));
                signIn.finishActivity();
            }

            @Override
            public void onFailure(int i, String s) {
                Toast.makeText(context,s,Toast.LENGTH_SHORT).show();
            }
        });
    }

    //获取token
    private void getToken(final String id, final String name, final String url) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Socket socket = new Socket("119.29.58.206",10086);
                    socket.setKeepAlive(true);
                    Log.d("aaaaaaaaaaa","connect success");
                    InputStream input = socket.getInputStream();
                    OutputStream output = socket.getOutputStream();
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(output));
                    writer.write(id+"\n"+name+"\n"+url+"\n"+"0\n");
                    writer.flush();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    while ((line = reader.readLine())!=null){

                    }
                    Log.d("aaaaaaaaaaa","read finish"+line);
                    editor.putString("loginToken",line);
                    editor.apply();
                    input.close();
                    output.close();
                    writer.close();
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void connectRongServer(String token) {
        if (context.getApplicationInfo().packageName.equals(MyApplication.
                getCurProcessName(context.getApplicationContext()))) {
            RongIM.connect(token, new RongIMClient.ConnectCallback() {
                /**
                 * Token 错误。可以从下面两点检查 1.  Token 是否过期，如果过期您需要向 App Server 重新请求一个新的 Token
                 *                  2.  token 对应的 appKey 和工程里设置的 appKey 是否一致
                 */
                @Override
                public void onTokenIncorrect() {
                    ToastUtil.makeShortToast(context,"token出错");
                }

                /**
                 * 连接融云成功
                 * @param userid 当前 token 对应的用户 id
                 */
                @Override
                public void onSuccess(String userid) {
                    Log.d("LoginActivity", "--onSuccess" + userid);
                    context.startActivity(MainActivity.getIntentToMainActivity(context));
                    signIn.finishActivity();
                }

                /**
                 * 连接融云失败
                 * @param errorCode 错误码，可到官网 查看错误码对应的注释
                 */
                @Override
                public void onError(RongIMClient.ErrorCode errorCode) {
                    ToastUtil.makeShortToast(context,"连接服务器失败，错误码"+errorCode);
                }
            });
        }
    }
}
