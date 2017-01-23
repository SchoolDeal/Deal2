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

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by U-nookia on 2017/1/23.
 */

public class ConversationAdapter extends BaseRecyclerAdapter<Message> {

    public ConversationAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getViewType(Message message) {
        return message.getLeftOrRight();
    }

    @Override
    protected void bindData(BaseViewHolder holder, Message item) {
        if (item.getLeftOrRight()==Util.CONVERSATION_LEFT){
            CircleImageView head = holder.getView(R.id.head_left);
            TextView content = holder.getView(R.id.content_left);
            //head.setImageResource(item.getHead());
            content.setText(item.getContent());
        }else {
            CircleImageView head = holder.getView(R.id.head_right);
            TextView content = holder.getView(R.id.content_right);
            //head.setImageResource(item.getHead());
            content.setText(item.getContent());
        }
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType== Util.CONVERSATION_LEFT) return new ConversationViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_conversation_left,parent,false));
        else return new ConversationViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_conversation_right,parent,false));
    }


}
