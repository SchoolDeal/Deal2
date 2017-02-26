package com.school.schooldeal.mine.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.school.schooldeal.R;
import com.school.schooldeal.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by U-nookia on 2017/2/26.
 */

public class AboutUsActivity extends BaseActivity {

    @BindView(R.id.toolBar)
    Toolbar toolBar;

    public static Intent getIntent(Context context) {
        Intent intent = new Intent(context, AboutUsActivity.class);
        return intent;
    }

    @Override
    protected void initData() {
        toolBar.setTitle("关于我们");
        toolBar.setTitleTextColor(Color.WHITE);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_aboutus;
    }

}
