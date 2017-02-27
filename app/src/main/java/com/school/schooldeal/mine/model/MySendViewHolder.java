package com.school.schooldeal.mine.model;

import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.school.schooldeal.R;
import com.school.schooldeal.base.BaseViewHolder;

/**
 * Created by 教科书式的机智少年 on 2017/2/27.
 */

public class MySendViewHolder extends BaseViewHolder {
    private ImageView image;
    private ImageView over;
    private TextView overHint;
    private TextView task;
    private TextView refund;
    private TextView detailed;
    private TextView storename;
    private CardView cardView;
    public MySendViewHolder(View itemView) {
        super(itemView);
        image = (ImageView) itemView.findViewById(R.id.item_minesend_school_image);
        over = (ImageView) itemView.findViewById(R.id.item_minesend_school_over);
        overHint = (TextView) itemView.findViewById(R.id.item_minesend_school_overHint);
        task = (TextView) itemView.findViewById(R.id.item_minesend_school_task);
        refund = (TextView) itemView.findViewById(R.id.item_minesend_school_refund);
        detailed = (TextView) itemView.findViewById(R.id.item_minesend_school_detailed);
        storename = (TextView)itemView.findViewById(R.id.item_minesend_school_store);
        cardView = (CardView)itemView.findViewById(R.id.item_minesend_school);
    }

    public ImageView getImage() {
        return image;
    }

    public void setImage(ImageView image) {
        this.image = image;
    }

    public ImageView getOver() {
        return over;
    }

    public void setOver(ImageView over) {
        this.over = over;
    }

    public TextView getOverHint() {
        return overHint;
    }

    public void setOverHint(TextView overHint) {
        this.overHint = overHint;
    }

    public TextView getTask() {
        return task;
    }

    public void setTask(TextView task) {
        this.task = task;
    }

    public TextView getRefund() {
        return refund;
    }

    public void setRefund(TextView refund) {
        this.refund = refund;
    }

    public TextView getDetailed() {
        return detailed;
    }

    public void setDetailed(TextView detailed) {
        this.detailed = detailed;
    }

    public TextView getStorename() {
        return storename;
    }

    public void setStorename(TextView storename) {
        this.storename = storename;
    }

    public CardView getCardView() {
        return cardView;
    }

    public void setCardView(CardView cardView) {
        this.cardView = cardView;
    }
}
