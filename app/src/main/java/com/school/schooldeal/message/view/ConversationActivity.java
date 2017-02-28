package com.school.schooldeal.message.view;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.TextView;

import com.school.schooldeal.R;
import com.school.schooldeal.base.BaseActivity;

import butterknife.BindView;


/**
 * Created by U-nookia on 2017/1/23.
 */

public class ConversationActivity extends BaseActivity{

    @BindView(R.id.title)
    TextView mTextView;

    public static Intent getIntentToConversationActivity(Context context) {
        Intent intent = new Intent(context, ConversationActivity.class);
        return intent;
    }

    @Override
    protected void initData() {
        mTextView.setText(getIntent().getData().getQueryParameter("title"));
        Log.e("type", "type is:" + getIntent().getData().getPath());
    }

    /*private void initRecycler() {
        conversation.setLayoutManager(new LinearLayoutManager(context));
    }*/

    /*private void getTitleAndContent() {
        Intent intent = getIntent();
        *//*BmobIMConversation item = (BmobIMConversation) intent.getSerializableExtra("c");
        initToolBar(item);
        initMsgContent(item);
        conversation.scrollToPosition(item.getMessages().size()-1);*//*
    }

    private void initMsgContent(BmobIMConversation item) {
        presenter.initAdapter(item);
    }

    private void initToolBar(BmobIMConversation item) {
        toolBar.setTitle(item.getConversationTitle());
    }*/

    @Override
    protected int getContentViewId() {
        return R.layout.conversation;
    }

}
