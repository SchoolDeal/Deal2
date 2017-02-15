package com.school.schooldeal.mine.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.school.schooldeal.R;
import com.school.schooldeal.base.BaseRecyclerAdapter;
import com.school.schooldeal.base.BaseViewHolder;

/**
 * Created by U-nookia on 2017/2/14.
 */

public class MineAdapter extends BaseRecyclerAdapter<MineRecyclerItemBean> {

    public MineAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getViewType(MineRecyclerItemBean mineRecyclerItemBean) {
        return 0;
    }

    @Override
    protected void bindData(BaseViewHolder holder, MineRecyclerItemBean item) {
        ImageView img = holder.getView(R.id.item_img);
        TextView item_content = holder.getView(R.id.item_content);
        img.setImageResource(item.getImgRes());
        item_content.setText(item.getItem());
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MineViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_mine,parent,false));
    }
}
