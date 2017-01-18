package com.school.schooldeal.takeout.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by U-nookia on 2016/12/19.
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

        return orders;
    }
}
