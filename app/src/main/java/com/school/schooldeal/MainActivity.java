package com.school.schooldeal;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.school.schooldeal.application.MyFragmentPagerAdapter;
import com.school.schooldeal.base.BaseActivity;
import com.school.schooldeal.base.BaseFragment;
import com.school.schooldeal.commen.util.ToastUtil;
import com.school.schooldeal.commen.util.Util;
import com.school.schooldeal.message.view.MessageFragment;
import com.school.schooldeal.mine.view.MineFragment;
import com.school.schooldeal.schooltask.view.SchoolTaskFragment;
import com.school.schooldeal.sign.model.RestaurantUser;
import com.school.schooldeal.sign.model.StudentUser;
import com.school.schooldeal.takeout.view.TakeOutFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.bmob.newim.BmobIM;
import cn.bmob.newim.core.ConnectionStatus;
import cn.bmob.newim.listener.ConnectListener;
import cn.bmob.newim.listener.ConnectStatusChangeListener;
import cn.bmob.push.BmobPush;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;

public class MainActivity extends BaseActivity implements
        BottomNavigationBar.OnTabSelectedListener
        ,ViewPager.OnPageChangeListener{

    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.activity_main)
    CoordinatorLayout activityMain;
    @BindView(R.id.bottom)
    BottomNavigationBar bottom;
    @BindView(R.id.toolBar)
    Toolbar toolbar;

    private List<BaseFragment> fragments;
    private String[] titles = {"take out","school task","message","mine"};

    public static Intent getIntentToMainActivity(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }

    @Override
    protected void initData(){
        initFragments();
        initViewPager();
        initBottomNavigationBar();    //初始化底部导航栏
        initToolBar();                //初始化toolbar
        initPushService();            //启动推送服务
        connectServe();               //连接bmob服务器，用于即时通讯
        setConnectStatusChangeListener();
    }

    private void setConnectStatusChangeListener() {
        BmobIM.getInstance().setOnConnectStatusChangeListener(new ConnectStatusChangeListener() {
            @Override
            public void onChange(ConnectionStatus status) {
                ToastUtil.makeShortToast(context,"change:"+status);
            }
        });
    }

    private void connectServe() {
        if (Util.IS_STUDENT){
            StudentUser user = BmobUser.getCurrentUser(context,StudentUser.class);
            BmobIM.connect(user.getObjectId(), new ConnectListener() {
                @Override
                public void done(String uid, BmobException e) {
                    if (e == null) {
                        ToastUtil.makeShortToast(context,"connect success");
                    } else {
                        ToastUtil.makeShortToast(context,"connect false :"+e);
                        Log.e(TAG,e.toString());
                    }
                }
            });
        }else {
            RestaurantUser user = BmobUser.getCurrentUser(context,RestaurantUser.class);
            BmobIM.connect(user.getObjectId(), new ConnectListener() {
                @Override
                public void done(String uid, BmobException e) {
                    if (e == null) {
                        ToastUtil.makeShortToast(context,"connect success");
                    } else {
                        ToastUtil.makeShortToast(context,"connect false :"+e);
                    }
                }
            });
        }
    }

    private void initPushService() {
        BmobPush.startWork(this);
    }

    private void initToolBar() {
        toolbar.setTitle(titles[0]);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
    }

    private void initBottomNavigationBar() {
        bottom.setActiveColor(R.color.barBackColor);
        bottom.addItem(new BottomNavigationItem(R.mipmap.small,titles[0]))
                .addItem(new BottomNavigationItem(R.mipmap.small,titles[1]))
                .addItem(new BottomNavigationItem(R.mipmap.small,titles[2]))
                .addItem(new BottomNavigationItem(R.mipmap.small,titles[3]))
                .initialise();
        bottom.setTabSelectedListener(this);
        bottom.setAutoHideEnabled(true);
    }


    private void initViewPager() {
        viewPager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager()
                ,fragments));
        viewPager.setCurrentItem(0);
        viewPager.setOnPageChangeListener(this);
    }

    private void initFragments() {
        fragments = new ArrayList<>();
        fragments.add(new TakeOutFragment());
        fragments.add(new SchoolTaskFragment());
        fragments.add(new MessageFragment());
        fragments.add(new MineFragment());
    }

    /*
    实现base的方法，返回当前activity的view
     */
    @Override
    public int getContentViewId() {
        return R.layout.activity_main;
    }

    /*
    以下三个为bottomNavigationBar的相关回调方法
     */
    @Override
    public void onTabSelected(int position) {
        viewPager.setCurrentItem(position);
        toolbar.setTitle(titles[position]);
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }

    /*
    下面三个为viewPager回调的方法
     */
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        bottom.selectTab(position);
        toolbar.setTitle(titles[position]);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    protected void onDestroy() {
        BmobIM.getInstance().disConnect();
        super.onDestroy();
    }
}
