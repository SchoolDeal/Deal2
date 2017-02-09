package com.school.schooldeal.takeout.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by U-nookia on 2016/12/19.
 * 外卖界面的数据处理类，用于获取数据集合，对数据集合做操作
 */

public class TakeOutModel implements ImplTakeOutModel{

    private List<TakeOutOrderBean> orders;

    public TakeOutModel() {
        orders = new ArrayList<>();
    }


    @Override
    public List<TakeOutOrderBean> getOrders() {
        orders.add(new TakeOutOrderBean(1));
        orders.add(new TakeOutOrderBean(2));
        orders.add(new TakeOutOrderBean(3));
        orders.add(new TakeOutOrderBean(4));
        orders.add(new TakeOutOrderBean(5));
        orders.add(new TakeOutOrderBean(6));
        orders.add(new TakeOutOrderBean(7));
        orders.add(new TakeOutOrderBean(8));
        orders.add(new TakeOutOrderBean(1));
        orders.add(new TakeOutOrderBean(2));
        orders.add(new TakeOutOrderBean(3));
        orders.add(new TakeOutOrderBean(4));
        orders.add(new TakeOutOrderBean(5));
        orders.add(new TakeOutOrderBean(6));
        orders.add(new TakeOutOrderBean(7));
        orders.add(new TakeOutOrderBean(8));

        return orders;
    }
}
