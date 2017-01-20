package com.school.schooldeal.schooltask.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 教科书式的机智少年 on 2017/1/20.
 */

public class SchoolTaskModel implements ImplSchoolTaskModel {
    private List<SchoolTaskOrderBean> orders;
    public SchoolTaskModel(){
        orders = new ArrayList<>();
    }
    @Override
    public List<SchoolTaskOrderBean> getOrders() {
        orders.add(new SchoolTaskOrderBean(0));
        orders.add(new SchoolTaskOrderBean(0));
        orders.add(new SchoolTaskOrderBean(0));
        orders.add(new SchoolTaskOrderBean(0));
        orders.add(new SchoolTaskOrderBean(0));
        orders.add(new SchoolTaskOrderBean(0));
        orders.add(new SchoolTaskOrderBean(0));
        orders.add(new SchoolTaskOrderBean(0));
        orders.add(new SchoolTaskOrderBean(0));
        orders.add(new SchoolTaskOrderBean(0));
        orders.add(new SchoolTaskOrderBean(0));

        return orders;
    }
}
