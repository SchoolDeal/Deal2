package com.school.schooldeal.sign.presenter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.school.schooldeal.MainActivity;
import com.school.schooldeal.application.MyApplication;
import com.school.schooldeal.commen.util.ToastUtil;
import com.school.schooldeal.sign.model.StudentUser;
import com.school.schooldeal.sign.view.ImplSignIn;
import com.school.schooldeal.sign.view.SignInAcitivty;
import com.school.schooldeal.sign.view.SignUpAcitivity;

import cn.bmob.v3.listener.SaveListener;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;

/**
 * Created by U-nookia on 2017/1/18.
 */

public class SignInPresenter {
    private ImplSignIn signIn;
    private Context context;

    public SignInPresenter(Context context,ImplSignIn signIn) {
        this.signIn = signIn;
        this.context = context;
    }

    public void signUp(){
        Intent intent = SignUpAcitivity.getIntentToSignUpActivity(context);
        context.startActivity(intent);
    }

    public void signIn() {
        final String name = signIn.getUserName();
        String password = signIn.getUserPassword();
        StudentUser studentUser = new StudentUser();
        studentUser.setUsername(name);
        studentUser.setPassword(password);
        studentUser.login(context, new SaveListener() {
            @Override
            public void onSuccess() {
                String token = getTokenFromUserName(name);
                connectRongServer(token);
            }

            @Override
            public void onFailure(int i, String s) {
                Toast.makeText(context,s,Toast.LENGTH_SHORT).show();
            }
        });
    }

    //通过userName获取token，无服务器暂时使用测试账号的固定token
    private String getTokenFromUserName(String name) {
        return "oIN3M/NuCbUpOYV8uNlJuQUxfaeHzBs8mkpTCLL2wIVx4AHnUomQmEzi99+PZmy71NmYxkfsPi7nd2RA570Xe9WFmdqUizNO";
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
                    ToastUtil.makeShortToast(context,"获取token出错");
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
