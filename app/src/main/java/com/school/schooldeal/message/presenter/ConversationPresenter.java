package com.school.schooldeal.message.presenter;

import android.content.Context;

import com.school.schooldeal.message.model.ConversationAdapter;
import com.school.schooldeal.message.view.ImplConversationActivity;
import com.school.schooldeal.model.ChatInfo;

import cn.bmob.newim.bean.BmobIMConversation;

/**
 * Created by U-nookia on 2017/1/23.
 */

public class ConversationPresenter {
    private ImplConversationActivity conversationActivity;
    private Context context;
    private ConversationAdapter adapter;

    public ConversationPresenter(Context context, ImplConversationActivity conversationActivity) {
        this.context = context;
        this.conversationActivity = conversationActivity;
        adapter = new ConversationAdapter(context);
    }

    public void initAdapter(BmobIMConversation item) {
        adapter.setData(item.getMessages());
        conversationActivity.setAdapter(adapter);
    }
}
