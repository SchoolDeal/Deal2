package com.school.schooldeal.sign.presenter;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.school.schooldeal.MainActivity;
import com.school.schooldeal.sign.model.StudentUser;
import com.school.schooldeal.sign.view.ImplSignIn;
import com.school.schooldeal.sign.view.SignInAcitivty;
import com.school.schooldeal.sign.view.SignUpAcitivity;

import cn.bmob.v3.listener.SaveListener;

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
        String name = signIn.getUserName();
        String password = signIn.getUserPassword();
        StudentUser studentUser = new StudentUser();
        studentUser.setUsername(name);
        studentUser.setPassword(password);
        studentUser.login(context, new SaveListener() {
            @Override
            public void onSuccess() {
                Intent intent = MainActivity.getIntentToMainActivity(context);
                context.startActivity(intent);
                signIn.finishActivity();
            }

            @Override
            public void onFailure(int i, String s) {
                Toast.makeText(context,s,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
