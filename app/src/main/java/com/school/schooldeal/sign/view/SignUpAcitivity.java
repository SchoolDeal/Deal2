package com.school.schooldeal.sign.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.school.schooldeal.R;
import com.school.schooldeal.base.BaseActivity;
import com.school.schooldeal.commen.util.ToastUtil;
import com.wrapp.floatlabelededittext.FloatLabeledEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by U-nookia on 2017/1/18.
 */

public class SignUpAcitivity extends BaseActivity {
    @BindView(R.id.name_edit)
    EditText nameEdit;
    @BindView(R.id.name_float_edit)
    FloatLabeledEditText nameFloatEdit;
    @BindView(R.id.password_edit)
    EditText passwordEdit;
    @BindView(R.id.password_float_edit)
    FloatLabeledEditText passwordFloatEdit;
    @BindView(R.id.email_edit)
    EditText emailEdit;
    @BindView(R.id.email_float_edit)
    FloatLabeledEditText emailFloatEdit;
    @BindView(R.id.signUp)
    Button signUp;

    public static Intent getIntentToSignUpActivity(Context context) {
        Intent intent = new Intent(context,SignUpAcitivity.class);
        return intent;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_signup;
    }

    @OnClick(R.id.signUp)
    public void onClick() {
        String name = nameEdit.getText().toString();
        String password = passwordEdit.getText().toString();
        String email = emailEdit.getText().toString();
        ToastUtil.makeShortToast(this,name+password+email);
        finish();
    }
}
