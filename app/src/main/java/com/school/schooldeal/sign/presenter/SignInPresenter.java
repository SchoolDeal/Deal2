package com.school.schooldeal.sign.presenter;

import android.content.Context;
import android.content.Intent;

import com.school.schooldeal.sign.model.User;
import com.school.schooldeal.sign.view.ImplSignIn;
import com.school.schooldeal.sign.view.SignUpAcitivity;

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
        User user = new User();
        user.setUsername(name);
        user.setPassword(password);

    }
}
