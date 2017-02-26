package com.school.schooldeal.base;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by 教科书式的机智少年 on 2017/2/25.
 */

public class MyViewPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments;
    private List<String> names;
    public MyViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public MyViewPagerAdapter(FragmentManager fm, List<Fragment> fragments,List<String> names) {
        super(fm);
        this.fragments = fragments;
        this.names = names;
    }



    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return names.get(position);
    }
}
