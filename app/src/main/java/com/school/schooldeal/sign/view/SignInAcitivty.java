package com.school.schooldeal.sign.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rengwuxian.materialedittext.MaterialEditText;
import com.school.schooldeal.MainActivity;
import com.school.schooldeal.R;
import com.school.schooldeal.base.BaseActivity;
import com.school.schooldeal.commen.util.SignUtil;
import com.school.schooldeal.commen.util.ToastUtil;
import com.school.schooldeal.sign.presenter.SignInPresenter;
import com.wrapp.floatlabelededittext.FloatLabeledEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by U-nookia on 2017/1/18.
 */

public class SignInAcitivty extends BaseActivity implements ImplSignIn {


    @BindView(R.id.edit_name)
    MaterialEditText nameEdit;
    @BindView(R.id.edit_password)
    MaterialEditText passwordEdit;
    @BindView(R.id.signUp)
    Button signUp;
    @BindView(R.id.signIn)
    Button signIn;
    @BindView(R.id.restaurant)
    TextView restaurant;
    @BindView(R.id.student)
    TextView student;
    @BindView(R.id.sign_view)
    RelativeLayout sign_view;
    @BindView(R.id.choice)
    LinearLayout choice_view;


    private SignInPresenter presenter;

    public static Intent getIntentToSignInActivity(Context context) {
        Intent intent = new Intent(context, SignInAcitivty.class);
        return intent;
    }

    @Override
    protected void initData() {
        presenter = new SignInPresenter(this, this);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_signin;
    }


    @OnClick({R.id.signUp, R.id.signIn,R.id.restaurant,R.id.student})
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
                ToastUtil.makeShortToast(this, name + password);
                intent = MainActivity.getIntentToMainActivity(this);
                startActivity(intent);
                finish();
                break;
            case R.id.restaurant:
                choice_view.setVisibility(View.GONE);
                sign_view.setVisibility(View.VISIBLE);
                SignUtil.IS_STUDENT = false;
                break;
            case R.id.student:
                choice_view.setVisibility(View.GONE);
                sign_view.setVisibility(View.VISIBLE);
                SignUtil.IS_STUDENT = true;
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
