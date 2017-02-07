package com.school.schooldeal;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.ashokvarma.bottomnavigation.BadgeItem;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.school.schooldeal.application.MyApplication;
import com.school.schooldeal.application.MyFragmentPagerAdapter;
import com.school.schooldeal.base.BaseActivity;
import com.school.schooldeal.base.BaseFragment;
import com.school.schooldeal.commen.util.ToastUtil;
import com.school.schooldeal.commen.util.Util;
import com.school.schooldeal.message.model.ConversationListAdapterEx;
import com.school.schooldeal.message.server.HomeWatcherReceiver;
import com.school.schooldeal.message.view.MessageFragment;
import com.school.schooldeal.mine.view.MineFragment;
import com.school.schooldeal.schooltask.view.SchoolTaskFragment;
import com.school.schooldeal.sign.model.RestaurantUser;
import com.school.schooldeal.sign.model.StudentUser;
import com.school.schooldeal.sign.view.SignInAcitivty;
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
import io.rong.imkit.RongContext;
import io.rong.imkit.RongIM;
import io.rong.imkit.fragment.ConversationListFragment;
import io.rong.imkit.manager.IUnReadMessageObserver;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.message.ContactNotificationMessage;

public class MainActivity extends BaseActivity implements
        BottomNavigationBar.OnTabSelectedListener
        , ViewPager.OnPageChangeListener, IUnReadMessageObserver,
        Toolbar.OnMenuItemClickListener{

    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.activity_main)
    CoordinatorLayout activityMain;
    @BindView(R.id.bottom)
    BottomNavigationBar bottom;
    @BindView(R.id.toolBar)
    Toolbar toolbar;
    private BadgeItem no_read_message;
    private ConversationListFragment mConversationListFragment = null;
    private boolean isDebug;
    private Conversation.ConversationType[] mConversationsTypes = null;
    private MaterialDialog dialog;

    private List<Fragment> fragments;
    private String[] titles = {"take out", "school task", "message", "mine"};

    public static Intent getIntentToMainActivity(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }

    @Override
    protected void initData() {
        isDebug = getSharedPreferences("config", MODE_PRIVATE).getBoolean("isDebug", false);
        initDialog();
        initFragments();
        initViewPager();
        initPushMessage();
        initBedgeItem();
        initBottomNavigationBar();    //初始化底部导航栏
        initToolBar();                //初始化toolbar
        initPushService();            //启动推送服务
    }

    private void initDialog() {
        dialog = new MaterialDialog.Builder(context)
                .title("请稍候")
                .content("正在刷新")
                .build();
    }

    private void initPushMessage() {
        final Conversation.ConversationType[] conversationTypes = {
                Conversation.ConversationType.PRIVATE,
                Conversation.ConversationType.GROUP, Conversation.ConversationType.SYSTEM,
                Conversation.ConversationType.PUBLIC_SERVICE, Conversation.ConversationType.APP_PUBLIC_SERVICE
        };

        RongIM.getInstance().addUnReadMessageCountChangedObserver(this, conversationTypes);
        getConversationPush();// 获取 push 的 id 和 target
        getPushMessage();
    }

    private void getPushMessage() {
        Intent intent = getIntent();
        if (intent != null && intent.getData() != null && intent.getData().getScheme().equals("rong")) {
            String path = intent.getData().getPath();
            if (path.contains("push_message")) {
                SharedPreferences sharedPreferences = getSharedPreferences("config", MODE_PRIVATE);
                String cacheToken = sharedPreferences.getString("loginToken", "");
                if (TextUtils.isEmpty(cacheToken)) {
                    startActivity(new Intent(MainActivity.this, SignInAcitivty.class));
                } else {
                    if (!RongIM.getInstance().getCurrentConnectionStatus().equals(RongIMClient.ConnectionStatusListener.ConnectionStatus.CONNECTED)) {
                        dialog.show();
                        RongIM.connect(cacheToken, new RongIMClient.ConnectCallback() {
                            @Override
                            public void onTokenIncorrect() {
                                dialog.dismiss();
                                ToastUtil.makeShortToast(context,"token出现错误");
                            }

                            @Override
                            public void onSuccess(String s) {
                                dialog.dismiss();
                            }

                            @Override
                            public void onError(RongIMClient.ErrorCode e) {
                                dialog.dismiss();
                                ToastUtil.makeShortToast(context,"出现错误"+e);
                            }
                        });
                    }
                }
            }
        }
    }

    private void getConversationPush() {
        if (getIntent() != null && getIntent().hasExtra("PUSH_CONVERSATIONTYPE") && getIntent().hasExtra("PUSH_TARGETID")) {
            final String conversationType = getIntent().getStringExtra("PUSH_CONVERSATIONTYPE");
            final String targetId = getIntent().getStringExtra("PUSH_TARGETID");

            RongIM.getInstance().getConversation(Conversation.ConversationType.valueOf(conversationType), targetId, new RongIMClient.ResultCallback<Conversation>() {
                @Override
                public void onSuccess(Conversation conversation) {
                    if (conversation != null) {
                        if (conversation.getLatestMessage() instanceof ContactNotificationMessage) { //好友消息的push
                            //startActivity(new Intent(MainActivity.this, NewFriendListActivity.class));
                        } else {
                            Uri uri = Uri.parse("rong://" + getApplicationInfo().packageName).buildUpon().appendPath("conversation")
                                    .appendPath(conversationType).appendQueryParameter("targetId", targetId).build();
                            Intent intent = new Intent(Intent.ACTION_VIEW);
                            intent.setData(uri);
                            startActivity(intent);
                        }
                    }
                }

                @Override
                public void onError(RongIMClient.ErrorCode e) {

                }
            });
        }
    }

    private void initBedgeItem() {
        no_read_message = new BadgeItem()
                .setBorderWidth(2)
                .setBackgroundColor(Color.RED)
                .setText(2+"")
                .setHideOnSelect(false);
    }

    private void initPushService() {
        BmobPush.startWork(this);
    }

    private void initToolBar() {
        toolbar.setTitle(titles[0]);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        toolbar.inflateMenu(R.menu.menu_main);
        toolbar.setOnMenuItemClickListener(this);
        toolbar.getMenu().getItem(0).setVisible(false);
    }

    private void initBottomNavigationBar() {
        bottom.setActiveColor(R.color.barBackColor);
        bottom.addItem(new BottomNavigationItem(R.mipmap.locationgt, titles[0]))
                .addItem(new BottomNavigationItem(R.mipmap.phonegt, titles[1]))
                .addItem(new BottomNavigationItem(R.mipmap.textgt, titles[2]).setBadgeItem(no_read_message))
                .addItem(new BottomNavigationItem(R.mipmap.number, titles[3]))
                .initialise();
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
    }

    private Fragment initConversationList() {
        if (mConversationListFragment == null) {
            ConversationListFragment listFragment = new ConversationListFragment();
            listFragment.setAdapter(new ConversationListAdapterEx(RongContext.getInstance()));
            Uri uri;
            if (isDebug) {
                uri = Uri.parse("rong://" + getApplicationInfo().packageName).buildUpon()
                        .appendPath("conversationlist")
                        .appendQueryParameter(Conversation.ConversationType.PRIVATE.getName(), "false") //设置私聊会话是否聚合显示
                        .appendQueryParameter(Conversation.ConversationType.GROUP.getName(), "true")//群组
                        .appendQueryParameter(Conversation.ConversationType.PUBLIC_SERVICE.getName(), "false")//公共服务号
                        .appendQueryParameter(Conversation.ConversationType.APP_PUBLIC_SERVICE.getName(), "false")//订阅号
                        .appendQueryParameter(Conversation.ConversationType.SYSTEM.getName(), "true")//系统
                        .appendQueryParameter(Conversation.ConversationType.DISCUSSION.getName(), "true")
                        .build();
                mConversationsTypes = new Conversation.ConversationType[]{Conversation.ConversationType.PRIVATE,
                        Conversation.ConversationType.GROUP,
                        Conversation.ConversationType.PUBLIC_SERVICE,
                        Conversation.ConversationType.APP_PUBLIC_SERVICE,
                        Conversation.ConversationType.SYSTEM,
                        Conversation.ConversationType.DISCUSSION
                };

            } else {
                uri = Uri.parse("rong://" + getApplicationInfo().packageName).buildUpon()
                        .appendPath("conversationlist")
                        .appendQueryParameter(Conversation.ConversationType.PRIVATE.getName(), "false") //设置私聊会话是否聚合显示
                        .appendQueryParameter(Conversation.ConversationType.GROUP.getName(), "false")//群组
                        .appendQueryParameter(Conversation.ConversationType.PUBLIC_SERVICE.getName(), "false")//公共服务号
                        .appendQueryParameter(Conversation.ConversationType.APP_PUBLIC_SERVICE.getName(), "false")//订阅号
                        .appendQueryParameter(Conversation.ConversationType.SYSTEM.getName(), "true")//系统
                        .build();
                mConversationsTypes = new Conversation.ConversationType[]{Conversation.ConversationType.PRIVATE,
                        Conversation.ConversationType.GROUP,
                        Conversation.ConversationType.PUBLIC_SERVICE,
                        Conversation.ConversationType.APP_PUBLIC_SERVICE,
                        Conversation.ConversationType.SYSTEM
                };
            }
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
        toolbar.setTitle(titles[position]);
        if (position==2){
            toolbar.getMenu().getItem(0).setVisible(true);
        }else {
            toolbar.getMenu().getItem(0).setVisible(false);
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
            no_read_message.setText(count+"");
        } else {
            if (no_read_message.isHidden()) no_read_message.show();
            no_read_message.setText(99+"+");
        }
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        String msg = "";
        switch (menuItem.getItemId()) {
            case R.id.action_find:
                msg += "Click find";
                onCountChanged(0);
                startChat();
                break;
        }
        if(!msg.equals("")) {
            Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    private void startChat() {
        String other,current;
        current = BmobUser.getCurrentUser(context).getUsername();
        if (current.equals("hhh")){
            other = "ea9c412700";
        }else {
            other = "bd01ae9f81";
        }

        RongIM.getInstance().
                startPrivateChat(MainActivity.this,
                        other, current.equals("hhh")?"10086":"hhh");
    }
}
