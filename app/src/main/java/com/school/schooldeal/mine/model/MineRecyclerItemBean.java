package com.school.schooldeal.mine.model;

import android.widget.TextView;

/**
 * Created by U-nookia on 2017/2/14.
 */

public class MineRecyclerItemBean {
    private int imgRes;
    private String item;

    public MineRecyclerItemBean(int imgRes, String item) {
        this.imgRes = imgRes;
        this.item = item;
    }

    public int getImgRes() {
        return imgRes;
    }

    public void setImgRes(int imgRes) {
        this.imgRes = imgRes;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }
}
