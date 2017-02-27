package com.school.schooldeal.mine.view;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.school.schooldeal.R;
import com.school.schooldeal.base.BaseActivity;
import com.school.schooldeal.base.MyViewPagerAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

/**
 * Created by 教科书式的机智少年 on 2017/2/27.
 */

public class MineOverReqestActivity extends BaseActivity {
    @BindView(R.id.minereceived_toolbar)
    Toolbar toolbar;
    @BindView(R.id.minereceived_viewpager)
    ViewPager viewPager;
    private TabLayout tabLayout;
    @Override
    protected void initData() {
        tabLayout = (TabLayout)findViewById(R.id.minereceived_tab);
        initToolBar();
        initviewPager();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_minereceived;
    }

    public void initviewPager(){
        tabLayout.addTab(tabLayout.newTab().setText("外卖"));
        tabLayout.addTab(tabLayout.newTab().setText("校园"));
        List<String> namelist = Arrays.asList("外卖","校园");
        List<Fragment> fragmentList = new ArrayList<Fragment>();
        fragmentList.add(new MineOverStoreFragment());
        fragmentList.add(new MineOverSchoolFragment());
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        viewPager.setAdapter(new MyViewPagerAdapter(getSupportFragmentManager(),fragmentList,namelist));
        tabLayout.setupWithViewPager(viewPager);
    }

    public void initToolBar(){
        toolbar.setTitle("我完成的订单");
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
    }
}
