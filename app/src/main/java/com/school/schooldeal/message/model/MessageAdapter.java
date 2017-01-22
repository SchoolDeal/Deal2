package com.school.schooldeal.message.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.school.schooldeal.R;
import com.school.schooldeal.base.BaseRecyclerAdapter;
import com.school.schooldeal.base.BaseViewHolder;
import com.school.schooldeal.model.ChatInfo;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by U-nookia on 2017/1/22.
 */

public class MessageAdapter extends BaseRecyclerAdapter<ChatInfo> {

    public MessageAdapter(Context context) {
        super(context);
    }

    @Override
    protected void bindData(BaseViewHolder holder, ChatInfo item) {
        CircleImageView img = holder.getView(R.id.img_message_item);
        img.setImageResource(R.mipmap.head);
        TextView name = holder.getView(R.id.message_name);
        name.setText(item.getMsgID());
        TextView content = holder.getView(R.id.message_content);
        content.setText(item.getMsgContent());
        TextView time = holder.getView(R.id.message_time);
        time.setText(item.getSentTime());
        TextView msgNum = holder.getView(R.id.message_num);
        if (item.getUnReadMsgNum()==0) msgNum.setVisibility(View.GONE);
        else msgNum.setText(item.getUnReadMsgNum()+"");
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MessageViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recycler_message,parent,false));
    }
}
