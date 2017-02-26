package com.school.schooldeal;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.text.InputType;
import android.text.TextUtils;

import com.afollestad.materialdialogs.MaterialDialog;
import com.school.schooldeal.commen.util.ToastUtil;
import com.school.schooldeal.commen.util.Util;
import com.school.schooldeal.sign.model.StudentUser;
import com.school.schooldeal.sign.view.SignInAcitivty;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.FindListener;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.UserInfo;
import io.rong.message.ContactNotificationMessage;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by U-nookia on 2017/2/24.
 */

public class MainActivityPresenter {
    private Context context;
    private ImplMainActivity mainActivity;
    private ImplMainModel mainModel;
    private boolean isDebug;

    public MainActivityPresenter(Context context, ImplMainActivity mainActivity) {
        this.context = context;
        this.mainActivity = mainActivity;
        mainModel = MainActivityModel.getModelInstance(context);
        isDebug = context.getSharedPreferences("config", MODE_PRIVATE).getBoolean("isDebug", false);
    }


    public void initUserList() {
        mainModel.getUserListFromBmob();
    }

    public List<BmobUser> getUserList(){
        return mainModel.getUserList();
    }

    public void refreshUser(String userId) {
        BmobUser user = mainModel.queryUser(userId);
        String name = user.getUsername();
        String url = ((StudentUser)user).getImgUrl();
        refresh(userId,name,url);
    }

    public void refreshCurrentUser() {
        String id = BmobUser.getCurrentUser(context).getObjectId();
        String name = BmobUser.getCurrentUser(context).getUsername();
        String url = context.getSharedPreferences("config",MODE_PRIVATE).getString("head_url",Util.defaultHeadImg);
        refresh(id,name,url);
    }

    private void refresh(String id, String name, String url) {
        Uri uri = Uri.parse(url);
        UserInfo info = new UserInfo(id,name,uri);
        RongIM.getInstance().refreshUserInfoCache(info);
    }


    public void handlePushMessage() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("config", MODE_PRIVATE);
        String cacheToken = sharedPreferences.getString("loginToken", "");
        if (TextUtils.isEmpty(cacheToken)) {
            context.startActivity(new Intent(context, SignInAcitivty.class));
        } else {
            if (!RongIM.getInstance().getCurrentConnectionStatus().equals(RongIMClient.ConnectionStatusListener.ConnectionStatus.CONNECTED)) {
                mainActivity.showProgressDialog("提示","请稍候");
                RongIM.connect(cacheToken, new RongIMClient.ConnectCallback() {
                    @Override
                    public void onTokenIncorrect() {
                        mainActivity.dismissProgressDialog();
                        ToastUtil.makeShortToast(context,"token出现错误");
                    }

                    @Override
                    public void onSuccess(String s) {
                        mainActivity.dismissProgressDialog();
                    }

                    @Override
                    public void onError(RongIMClient.ErrorCode e) {
                        mainActivity.dismissProgressDialog();
                        ToastUtil.makeShortToast(context,"出现错误"+e);
                    }
                });
            }
        }
    }

    public void getConversation(final String conversationType, final String targetId) {
        RongIM.getInstance().getConversation(Conversation.ConversationType.valueOf(conversationType), targetId, new RongIMClient.ResultCallback<Conversation>() {
            @Override
            public void onSuccess(Conversation conversation) {
                if (conversation != null) {
                    if (conversation.getLatestMessage() instanceof ContactNotificationMessage) { //好友消息的push
                        //startActivity(new Intent(MainActivity.this, NewFriendListActivity.class));
                    } else {
                        Uri uri = Uri.parse("rong://" + context.getApplicationInfo().packageName).buildUpon().appendPath("conversation")
                                .appendPath(conversationType).appendQueryParameter("targetId", targetId).build();
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(uri);
                        context.startActivity(intent);
                    }
                }
            }

            @Override
            public void onError(RongIMClient.ErrorCode e) {

            }
        });
    }

    public Uri setListFragmentUri() {
        Uri uri;
        if (isDebug) {
            uri = Uri.parse("rong://" + context.getApplicationInfo().packageName).buildUpon()
                    .appendPath("conversationlist")
                    .appendQueryParameter(Conversation.ConversationType.PRIVATE.getName(), "false") //设置私聊会话是否聚合显示
                    .appendQueryParameter(Conversation.ConversationType.GROUP.getName(), "true")//群组
                    .appendQueryParameter(Conversation.ConversationType.PUBLIC_SERVICE.getName(), "false")//公共服务号
                    .appendQueryParameter(Conversation.ConversationType.APP_PUBLIC_SERVICE.getName(), "false")//订阅号
                    .appendQueryParameter(Conversation.ConversationType.SYSTEM.getName(), "true")//系统
                    .appendQueryParameter(Conversation.ConversationType.DISCUSSION.getName(), "true")
                    .build();
        } else {
            uri = Uri.parse("rong://" + context.getApplicationInfo().packageName).buildUpon()
                    .appendPath("conversationlist")
                    .appendQueryParameter(Conversation.ConversationType.PRIVATE.getName(), "false") //设置私聊会话是否聚合显示
                    .appendQueryParameter(Conversation.ConversationType.GROUP.getName(), "false")//群组
                    .appendQueryParameter(Conversation.ConversationType.PUBLIC_SERVICE.getName(), "false")//公共服务号
                    .appendQueryParameter(Conversation.ConversationType.APP_PUBLIC_SERVICE.getName(), "false")//订阅号
                    .appendQueryParameter(Conversation.ConversationType.SYSTEM.getName(), "true")//系统
                    .build();
        }
        return uri;
    }

    public void queryUser(CharSequence input) {
        BmobQuery<BmobUser> query = new BmobQuery<>();
        query.addWhereEqualTo("username", input);
        query.findObjects(context, new FindListener<BmobUser>() {
            @Override
            public void onSuccess(List<BmobUser> list) {
                if (null!=list){
                    if (list.size()==1){
                        BmobUser user = list.get(0);
                        String id = user.getObjectId();
                        String name = user.getUsername();
                        mainActivity.startChat(id,name);
                    }else {
                        ToastUtil.makeShortToast(context,"没有该用户");
                    }
                }
                mainActivity.dismissProgressDialog();
            }

            @Override
            public void onError(int i, String s) {
                ToastUtil.makeShortToast(context,"搜索用户出错"+s);
                mainActivity.dismissProgressDialog();
            }
        });
    }

    public void showInputDialog() {
        MaterialDialog inputDialog = new MaterialDialog.Builder(context)
                .title("搜索")
                .content("请输入用户的昵称：")
                .inputType(InputType.TYPE_CLASS_TEXT)
                .input("用户昵称", "", new MaterialDialog.InputCallback() {
                    @Override
                    public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
                        getUserFromInput(input);
                    }
                }).build();
        inputDialog.show();
    }

    /*
    通过输入的用户名获取用户对象
     */
    private void getUserFromInput(CharSequence input) {
        mainActivity.showProgressDialog("提示","正在搜索该用户，请稍候");
        queryUser(input);
    }
}
