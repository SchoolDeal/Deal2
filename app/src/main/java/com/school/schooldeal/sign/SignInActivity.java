package com.school.schooldeal.sign;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.school.schooldeal.MainActivity;
import com.school.schooldeal.R;
import com.school.schooldeal.base.BaseActivity;
import com.school.schooldeal.commen.util.ToastUtil;
import com.wrapp.floatlabelededittext.FloatLabeledEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by U-nookia on 2016/12/19.
 * 登录activity
 */

public class SignInActivity extends BaseActivity {

    @BindView(R.id.name_edit)
    EditText nameEdit;
    @BindView(R.id.name_float_edit)
    FloatLabeledEditText nameFloatEdit;
    @BindView(R.id.password_edit)
    EditText passwordEdit;
    @BindView(R.id.password_float_edit)
    FloatLabeledEditText passwordFloatEdit;
    @BindView(R.id.signUp)
    Button signUp;
    @BindView(R.id.signIn)
    Button signIn;

    public static Intent getIntentToSignInActivity(Context context) {
        Intent intent = new Intent(context, SignInActivity.class);
        return intent;
    }


    @Override
    protected void initData() {

    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_signin;
    }

    @OnClick(R.id.signIn)
    public void onClickSignIn() {
        String name = nameEdit.getText().toString();
        String password = passwordEdit.getText().toString();
        ToastUtil.makeShortToast(this,name+password);
        Intent intent = MainActivity.getIntentToMainActivity(this);
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.signUp)
    public void onClickSignUp() {
        Intent intent = SignUpAcitivity.getIntentToSignUpActivity(this);
        startActivity(intent);
    }
}
