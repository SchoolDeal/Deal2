package com.school.schooldeal.sign.view;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.school.schooldeal.MainActivity;
import com.school.schooldeal.R;
import com.school.schooldeal.application.MyApplication;
import com.school.schooldeal.base.BaseActivity;
import com.school.schooldeal.commen.util.ToastUtil;
import com.school.schooldeal.commen.util.Util;
import com.school.schooldeal.sign.presenter.SignInPresenter;

import butterknife.BindView;
import butterknife.OnClick;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;

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
    private MaterialDialog dialog;

    public static Intent getIntentToSignInActivity(Context context) {
        Intent intent = new Intent(context, SignInAcitivty.class);
        return intent;
    }

    @Override
    protected void initData() {
        presenter = new SignInPresenter(this, this);
        presenter.checkIfFirstTimeLogin();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_signin;
    }


    @OnClick({R.id.signUp, R.id.signIn,R.id.restaurant,R.id.student})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.signUp:
                presenter.signUp();
                break;
            case R.id.signIn:
                presenter.signIn();
                break;
            case R.id.restaurant:
                choice_view.setVisibility(View.GONE);
                sign_view.setVisibility(View.VISIBLE);
                Util.IS_STUDENT = false;
                presenter.putBoolean(Util.IS_STUDENT);
                break;
            case R.id.student:
                choice_view.setVisibility(View.GONE);
                sign_view.setVisibility(View.VISIBLE);
                Util.IS_STUDENT = true;
                presenter.putBoolean(Util.IS_STUDENT);
                break;
        }
    }

    @Override
    public void finishActivity(){
        finish();
    }

    @Override
    public String getUserName() {
        return nameEdit.getText().toString();
    }

    @Override
    public String getUserPassword() {
        return passwordEdit.getText().toString();
    }

    @Override
    public void connectRongServer(final String token) {
        Log.d("bbbb",token);
        if (getApplicationInfo().packageName.equals(MyApplication.getCurProcessName(getApplicationContext()))) {
            RongIM.connect(token, new RongIMClient.ConnectCallback() {
                @Override
                public void onTokenIncorrect() {
                    ToastUtil.makeShortToast(context,"token出错,正在重新获取token");
                    Log.d("bbbb","token false,getting token again");
                    presenter.getToken();
                }
                @Override
                public void onSuccess(String userid) {
                    Log.d("bbbb",userid);
                    dismissDialog();
                    presenter.putTokenToSharedPreferences(token);
                    ToastUtil.makeShortToast(context,"connect success");
                    startActivity(MainActivity.getIntentToMainActivity(context));
                    finishActivity();
                }
                @Override
                public void onError(RongIMClient.ErrorCode errorCode) {
                    ToastUtil.makeShortToast(context,"connect false:"+errorCode);
                    dismissDialog();
                    
                    Log.d("bbbb","tokenerror");
                }
            });
        }
    }

    @Override
    public void showDialog(String title,String content){
        dialog = new MaterialDialog.Builder(context)
                .title(title)
                .content(content)
                .progress(true,0)
                .build();
        dialog.show();
    }

    @Override
    public void dismissDialog() {
        dialog.dismiss();
    }

    @Override
    public void clearView() {
        student.setVisibility(View.GONE);
        restaurant.setVisibility(View.GONE);
    }

    @Override
    public void showView(){
        student.setVisibility(View.VISIBLE);
        restaurant.setVisibility(View.VISIBLE);
    }
}
