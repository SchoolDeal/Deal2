package com.school.schooldeal.mine.model;

import com.school.schooldeal.R;
import com.school.schooldeal.commen.util.Util;

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
                new MineRecyclerItemBean(R.mipmap.release, Util.order_release);
        MineRecyclerItemBean orderReceive =
                new MineRecyclerItemBean(R.mipmap.receive,Util.order_receive);
        MineRecyclerItemBean orderfinish =
                new MineRecyclerItemBean(R.mipmap.finish,Util.order_finish);
        MineRecyclerItemBean fedBack =
                new MineRecyclerItemBean(R.mipmap.fedback,Util.fed_back);
        MineRecyclerItemBean customer_service =
                new MineRecyclerItemBean(R.mipmap.customer_service,Util.customer_service);
        MineRecyclerItemBean aboutUs =
                new MineRecyclerItemBean(R.mipmap.aboutus,Util.about_us);
        MineRecyclerItemBean versionUp =
                new MineRecyclerItemBean(R.mipmap.versionup,Util.version_up);
        beans.add(orderRelease);
        beans.add(orderReceive);
        beans.add(orderfinish);
        beans.add(fedBack);
        beans.add(customer_service);
        beans.add(aboutUs);
        beans.add(versionUp);
        return beans;
    }
}
