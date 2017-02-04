package com.school.schooldeal.message.model;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.school.schooldeal.R;
import com.school.schooldeal.base.BaseRecyclerAdapter;
import com.school.schooldeal.base.BaseViewHolder;
import com.school.schooldeal.commen.util.ToastUtil;
import com.school.schooldeal.message.presenter.MessagePresenter;
import com.school.schooldeal.message.view.ConversationActivity;
import com.school.schooldeal.model.ChatInfo;

import cn.bmob.newim.BmobIM;
import cn.bmob.newim.bean.BmobIMConversation;
import cn.bmob.newim.bean.BmobIMUserInfo;
import cn.bmob.newim.listener.ConversationListener;
import cn.bmob.v3.exception.BmobException;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by U-nookia on 2017/1/22.
 */

public class MessageAdapter extends BaseRecyclerAdapter<ChatInfo> {

    public MessageAdapter(Context context) {
        super(context);

    }

    @Override
    protected int getViewType(ChatInfo chatInfo) {
        return 0;
    }

    @Override
    protected void bindData(BaseViewHolder holder, final ChatInfo item) {

    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MessageViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recycler_message,parent,false));
    }
}
