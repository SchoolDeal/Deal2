package com.school.schooldeal.message.view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;

import com.school.schooldeal.R;
import com.school.schooldeal.base.BaseActivity;
import com.school.schooldeal.model.ChatInfo;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by U-nookia on 2017/1/23.
 */

public class ConversationActivity extends BaseActivity {

    @BindView(R.id.toolBar)
    Toolbar toolBar;
    @BindView(R.id.conversation)
    RecyclerView conversation;
    @BindView(R.id.input)
    EditText input;
    @BindView(R.id.send)
    Button send;

    public static Intent getIntentToConversationActivity(Context context) {
        Intent intent = new Intent(context, ConversationActivity.class);
        return intent;
    }

    @Override
    protected void initData() {
        getTitleAndContent();
    }

    private void getTitleAndContent() {
        Intent intent = getIntent();
        ChatInfo item = (ChatInfo) intent.getSerializableExtra("message");
        initToolBar(item);
        initMsgContent(item);
    }

    private void initMsgContent(ChatInfo item) {

    }

    private void initToolBar(ChatInfo item) {
        toolBar.setTitle(item.getSentStudent().getName());
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_conversation;
    }

    @OnClick(R.id.send)
    public void onClick() {

    }
}
