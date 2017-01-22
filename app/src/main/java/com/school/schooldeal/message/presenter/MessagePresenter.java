package com.school.schooldeal.message.presenter;

import android.content.Context;

import com.school.schooldeal.message.model.ImplMessageModel;
import com.school.schooldeal.message.model.MessageAdapter;
import com.school.schooldeal.message.model.MessageModel;
import com.school.schooldeal.message.view.ImplMessageFragment;

/**
 * Created by U-nookia on 2017/1/21.
 */

public class MessagePresenter{
    private ImplMessageModel messageModel;
    private ImplMessageFragment messageFragment;
    private MessageAdapter adapter;

    public MessagePresenter(Context context,ImplMessageFragment messageFragment) {
        this.messageFragment = messageFragment;
        messageModel = new MessageModel();
        adapter = new MessageAdapter(context);
    }

    public void initAdapter() {
        adapter.setData(MessageModel.getMessageList());
        messageFragment.setAdapter(adapter);
    }
}
