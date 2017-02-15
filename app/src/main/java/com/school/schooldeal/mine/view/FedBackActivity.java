package com.school.schooldeal.mine.view;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;

import com.school.schooldeal.R;
import com.school.schooldeal.base.BaseActivity;
import com.school.schooldeal.commen.util.ToastUtil;
import com.school.schooldeal.commen.util.Util;
import com.school.schooldeal.mine.model.FedBackBean;
import com.school.schooldeal.sign.model.RestaurantUser;
import com.school.schooldeal.sign.model.StudentUser;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by U-nookia on 2017/2/15.
 * 反馈错误的activity
 */

public class FedBackActivity extends BaseActivity {


    @BindView(R.id.fed_back_toolBar)
    Toolbar fedBackToolBar;
    @BindView(R.id.fed_back)
    EditText fedBack;
    @BindView(R.id.fed_back_bt)
    Button fedBackBt;

    public static Intent getIntentToFedBackActivity(Context mcontext) {
        Intent intent = new Intent(mcontext, FedBackActivity.class);
        return intent;
    }

    @Override
    protected void initData() {
        initToolbar();
    }

    private void initToolbar() {
        fedBackToolBar.setTitle(R.string.fed_back);
        fedBackToolBar.setTitleTextColor(getResources().getColor(R.color.white));
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_fedback;
    }

    @OnClick(R.id.fed_back_bt)
    public void onClick() {
        String system = "android"+Build.VERSION.RELEASE+"||android sdk=="+Build.VERSION.SDK+"|| "+Build.MODEL;
        String content = fedBack.getText().toString();
        if (content.equals("")) ToastUtil.makeShortToast(context,R.string.tip_for_empty);
        else commitMsg(system,content);
    }

    private void commitMsg(String system, String content) {
        if (Util.IS_STUDENT){
            StudentUser user = BmobUser.getCurrentUser(context,StudentUser.class);
            FedBackBean bean = new FedBackBean(content,system,user);
            bean.save(context, new SaveListener() {
                @Override
                public void onSuccess() {
                    ToastUtil.makeShortToast(context,R.string.commit_success);
                    finish();
                }

                @Override
                public void onFailure(int i, String s) {
                    ToastUtil.makeShortToast(context,s);
                }
            });
        }else {
            RestaurantUser user = BmobUser.getCurrentUser(context,RestaurantUser.class);
            FedBackBean bean = new FedBackBean(content,system,user);
            bean.save(context, new SaveListener() {
                @Override
                public void onSuccess() {
                    ToastUtil.makeShortToast(context,R.string.commit_success);
                    finish();
                }

                @Override
                public void onFailure(int i, String s) {
                    ToastUtil.makeShortToast(context,s);
                }
            });
        }
    }
}
