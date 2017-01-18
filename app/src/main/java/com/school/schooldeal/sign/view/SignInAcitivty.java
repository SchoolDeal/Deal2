package com.school.schooldeal.sign.view;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.school.schooldeal.MainActivity;
import com.school.schooldeal.R;
import com.school.schooldeal.base.BaseActivity;
import com.school.schooldeal.commen.util.ToastUtil;
import com.school.schooldeal.sign.presenter.SignInPresenter;
import com.wrapp.floatlabelededittext.FloatLabeledEditText;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by U-nookia on 2017/1/18.
 */

public class SignInAcitivty extends BaseActivity implements ImplSignIn{


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

    private SignInPresenter presenter;

    public static Intent getIntentToSignInActivity(Context context) {
        Intent intent = new Intent(context, SignInAcitivty.class);
        return intent;
    }

    @Override
    protected void initData() {
        presenter = new SignInPresenter(this,this);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_signin;
    }


    @OnClick({R.id.signUp, R.id.signIn})
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.signUp:
                presenter.signUp();
                break;
            case R.id.signIn:
                presenter.signIn();
                String name = nameEdit.getText().toString();
                String password = passwordEdit.getText().toString();
                ToastUtil.makeShortToast(this,name+password);
                intent = MainActivity.getIntentToMainActivity(this);
                startActivity(intent);
                finish();
                break;
        }
    }

    @Override
    public String getUserName() {
        return nameEdit.getText().toString();
    }

    @Override
    public String getUserPassword() {
        return passwordEdit.getText().toString();
    }
}
