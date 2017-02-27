package com.school.schooldeal.mine.model;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.school.schooldeal.R;
import com.school.schooldeal.base.BaseViewHolder;

/**
 * Created by 教科书式的机智少年 on 2017/2/26.
 */

public class ReceivedSchoolViewHolder extends BaseViewHolder {
    private ImageView image;
    private ImageView over;
    private TextView overHint;
    private TextView task;
    private TextView bedroom;
    private TextView phoneNum;
    private TextView content;
    private TextView refund;
    public ReceivedSchoolViewHolder(View itemView) {
        super(itemView);
        image = (ImageView)itemView.findViewById(R.id.item_minereceive_school_image);
        over = (ImageView)itemView.findViewById(R.id.item_minereceive_school_over);
        overHint = (TextView)itemView.findViewById(R.id.item_minereceive_school_overHint);
        task = (TextView)itemView.findViewById(R.id.item_minereceive_school_task);
        bedroom = (TextView)itemView.findViewById(R.id.item_minereceive_school_presonName);
        phoneNum = (TextView)itemView.findViewById(R.id.item_minereceive_school_phoneNum);
        content = (TextView)itemView.findViewById(R.id.item_minereceive_school_detailed);
        refund = (TextView)itemView.findViewById(R.id.item_minereceive_school_refund);
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

    public TextView getBedroom() {
        return bedroom;
    }

    public void setBedroom(TextView bedroom) {
        this.bedroom = bedroom;
    }

    public TextView getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(TextView phoneNum) {
        this.phoneNum = phoneNum;
    }

    public TextView getContent() {
        return content;
    }

    public void setContent(TextView content) {
        this.content = content;
    }

    public TextView getRefund() {
        return refund;
    }

    public void setRefund(TextView refund) {
        this.refund = refund;
    }
}
