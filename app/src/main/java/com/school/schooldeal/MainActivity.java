package com.school.schooldeal;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.ashokvarma.bottomnavigation.BadgeItem;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.school.schooldeal.application.MyFragmentPagerAdapter;
import com.school.schooldeal.base.BaseActivity;
import com.school.schooldeal.commen.util.Util;
import com.school.schooldeal.message.model.ConversationListAdapterEx;
import com.school.schooldeal.message.server.HomeWatcherReceiver;
import com.school.schooldeal.mine.view.MineFragment;
import com.school.schooldeal.schooltask.view.SchoolTaskFragment;
import com.school.schooldeal.sign.model.StudentUser;
import com.school.schooldeal.takeout.view.TakeOutFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.push.BmobPush;
import cn.bmob.v3.BmobUser;
import io.rong.imkit.RongContext;
import io.rong.imkit.RongIM;
import io.rong.imkit.fragment.ConversationListFragment;
import io.rong.imkit.manager.IUnReadMessageObserver;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.UserInfo;

public class MainActivity extends BaseActivity implements ImplMainActivity,
        BottomNavigationBar.OnTabSelectedListener
        , ViewPager.OnPageChangeListener, IUnReadMessageObserver,
        RongIM.UserInfoProvider {

    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.activity_main)
    CoordinatorLayout activityMain;
    @BindView(R.id.bottom)
    BottomNavigationBar bottom;
    @BindView(R.id.toolBar)
    Toolbar toolbar;
    @BindView(R.id.find)
    ImageView find;
    @BindView(R.id.title)
    TextView title;
    private BadgeItem no_read_message;
    private ConversationListFragment mConversationListFragment = null;
    private MaterialDialog progressDialog;

    private List<Fragment> fragments;
    private int[] titles = {R.string.take_out_title, R.string.school_task_title, R.string.message_title, R.string.mine_title};
    private int[] titles_restaurant = {R.string.take_out_title, R.string.message_title, R.string.mine_title};

    private MainActivityPresenter presenter;

    public static Intent getIntentToMainActivity(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }

    @Override
    protected void initData() {
        presenter = new MainActivityPresenter(context, this);
        presenter.refreshCurrentUser();
        initFragments();
        initViewPager();
        initPushMessage();
        initBedgeItem();
        initBottomNavigationBar();    //初始化底部导航栏
        initToolBar();                //初始化toolbar
        initPushService();            //启动推送服务
        RongIM.setUserInfoProvider(this, false);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.initUserList();
    }

    private void initPushMessage() {
        final Conversation.ConversationType[] conversationTypes = {
                Conversation.ConversationType.PRIVATE,
                Conversation.ConversationType.GROUP, Conversation.ConversationType.SYSTEM,
                Conversation.ConversationType.PUBLIC_SERVICE, Conversation.ConversationType.APP_PUBLIC_SERVICE
        };

        RongIM.getInstance().addUnReadMessageCountChangedObserver(this, conversationTypes);
        getConversationPush();// 获取push的id和target
        getPushMessage();
    }

    private void getPushMessage() {
        Intent intent = getIntent();
        if (intent != null && intent.getData() != null && intent.getData().getScheme().equals("rong")) {
            String path = intent.getData().getPath();
            if (path.contains("push_message")) {
                presenter.handlePushMessage();
            }
        }
    }

    private void getConversationPush() {
        if (getIntent() != null && getIntent().hasExtra("PUSH_CONVERSATIONTYPE") && getIntent().hasExtra("PUSH_TARGETID")) {
            String conversationType = getIntent().getStringExtra("PUSH_CONVERSATIONTYPE");
            String targetId = getIntent().getStringExtra("PUSH_TARGETID");
            presenter.getConversation(conversationType, targetId);
        }
    }

    private void initBedgeItem() {
        no_read_message = new BadgeItem()
                .setBorderWidth(2)
                .setBackgroundColor(Color.RED)
                .setText(2 + "")
                .setHideOnSelect(false);
    }

    private void initPushService() {
        BmobPush.startWork(this);
    }

    private void initToolBar() {
        /*toolbar.setTitle(titles[0]);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));*/
        title.setText(titles[0]);
        find.setVisibility(View.GONE);
    }

    private void initBottomNavigationBar() {
        bottom.setActiveColor(R.color.barBackColor);
        if (Util.IS_STUDENT) {
            bottom.addItem(new BottomNavigationItem(R.mipmap.locationgt, titles[0]))
                    .addItem(new BottomNavigationItem(R.mipmap.phonegt, titles[1]))
                    .addItem(new BottomNavigationItem(R.mipmap.textgt, titles[2]).setBadgeItem(no_read_message))
                    .addItem(new BottomNavigationItem(R.mipmap.number, titles[3]))
                    .initialise();
        } else {
            toolbar.setBackgroundResource(R.color.md_teal_400);
//            toolbar.setTitleTextColor(0xffffff);
            title.setText(titles[0]);
            bottom.addItem(new BottomNavigationItem(R.mipmap.locationgt, titles[0]))
                    .addItem(new BottomNavigationItem(R.mipmap.textgt, titles[2]).setBadgeItem(no_read_message))
                    .addItem(new BottomNavigationItem(R.mipmap.number, titles[3]))
                    .initialise();
        }
        bottom.setTabSelectedListener(this);
        bottom.setAutoHideEnabled(true);
    }


    private void initViewPager() {
        viewPager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager()
                , fragments));
        viewPager.setCurrentItem(0);
        viewPager.setOnPageChangeListener(this);

    }

    private void initFragments() {
        Fragment conversationList = initConversationList();
        fragments = new ArrayList<>();
        fragments.add(new TakeOutFragment());
        fragments.add(new SchoolTaskFragment());
        fragments.add(conversationList);
        fragments.add(new MineFragment());
        if (!Util.IS_STUDENT) fragments.remove(1);
    }

    private Fragment initConversationList() {
        if (mConversationListFragment == null) {
            ConversationListFragment listFragment = new ConversationListFragment();
            listFragment.setAdapter(new ConversationListAdapterEx(RongContext.getInstance()));
            Uri uri = presenter.setListFragmentUri();
            listFragment.setUri(uri);
            mConversationListFragment = listFragment;
            return listFragment;
        } else {
            return mConversationListFragment;
        }
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
        if (Util.IS_STUDENT) {
//            toolbar.setTitle(titles[position]);
            title.setText(titles[position]);
            if (position == 2) {
                find.setVisibility(View.VISIBLE);
            } else {
                find.setVisibility(View.GONE);
            }
        } else {
//            toolbar.setTitle(titles_restaurant[position]);
            title.setText(titles_restaurant[position]);
            if (position == 1) {
                find.setVisibility(View.VISIBLE);
            } else {
                find.setVisibility(View.GONE);
            }
        }
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
        RongIM.getInstance().removeUnReadMessageCountChangedObserver(this);
        if (mHomeKeyReceiver != null)
            this.unregisterReceiver(mHomeKeyReceiver);
        super.onDestroy();
    }

    private HomeWatcherReceiver mHomeKeyReceiver = null;

    @Override
    public void onCountChanged(int count) {
        if (count == 0) {
            no_read_message.hide();
        } else if (count > 0 && count < 100) {
            if (no_read_message.isHidden()) no_read_message.show();
            no_read_message.setText(count + "");
        } else {
            if (no_read_message.isHidden()) no_read_message.show();
            no_read_message.setText(99 + "+");
        }
    }

    @Override
    public void showProgressDialog(String title, String content) {
        progressDialog = new MaterialDialog.Builder(context)
                .title(title)
                .content(content)
                .progress(true, 0)
                .build();
        progressDialog.show();
    }

    @Override
    public void dismissProgressDialog() {
        progressDialog.dismiss();
    }

    @Override
    public void startChat(String id, String name) {
        presenter.refreshUser(id);
        RongIM.getInstance().startConversation(context, Conversation.ConversationType.PRIVATE,
                id, name);
    }

    @Override
    public UserInfo getUserInfo(String s) {
        for (BmobUser i : presenter.getUserList()) {
            if (i.getObjectId().equals(s)) {
                return new UserInfo(i.getObjectId(), i.getUsername(), Uri.parse(((StudentUser) i).getImgUrl()));
            }
        }
        return null;
    }

    @OnClick(R.id.find)
    public void onClick() {
        presenter.showInputDialog();
    }

}
