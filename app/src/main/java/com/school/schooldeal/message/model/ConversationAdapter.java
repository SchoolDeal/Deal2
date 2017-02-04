package com.school.schooldeal.message.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.school.schooldeal.R;
import com.school.schooldeal.base.BaseRecyclerAdapter;
import com.school.schooldeal.base.BaseViewHolder;
import com.school.schooldeal.commen.util.Util;
import com.school.schooldeal.model.Message;

import cn.bmob.newim.bean.BmobIMMessage;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by U-nookia on 2017/1/23.
 */

public class ConversationAdapter extends BaseRecyclerAdapter<BmobIMMessage> {

    public ConversationAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getViewType(BmobIMMessage message) {
        return 0;
    }

    @Override
    protected void bindData(BaseViewHolder holder, BmobIMMessage item) {

    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType== Util.CONVERSATION_LEFT) return new ConversationViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_conversation_left,parent,false));
        else return new ConversationViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_conversation_right,parent,false));
    }
}
