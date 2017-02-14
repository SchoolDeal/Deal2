package com.school.schooldeal.mine.model;

import com.school.schooldeal.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by U-nookia on 2017/2/14.
 * 我的界面头像下面各个item的生成
 */

public class MineItems {
    public static List<MineRecyclerItemBean> getItems() {
        List<MineRecyclerItemBean> beans = new ArrayList<>();
        MineRecyclerItemBean orderRelease =
                new MineRecyclerItemBean(R.mipmap.release,"发布订单");
        MineRecyclerItemBean orderReceive =
                new MineRecyclerItemBean(R.mipmap.receive,"接收订单");
        MineRecyclerItemBean orderfinish =
                new MineRecyclerItemBean(R.mipmap.finish,"完成订单");
        MineRecyclerItemBean fedBack =
                new MineRecyclerItemBean(R.mipmap.fedback,"反馈错误");
        MineRecyclerItemBean aboutUs =
                new MineRecyclerItemBean(R.mipmap.aboutus,"关于我们");
        MineRecyclerItemBean versionUp =
                new MineRecyclerItemBean(R.mipmap.versionup,"版本更新");
        beans.add(orderRelease);
        beans.add(orderReceive);
        beans.add(orderfinish);
        beans.add(fedBack);
        beans.add(aboutUs);
        beans.add(versionUp);
        return beans;
    }
}
