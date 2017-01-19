package com.school.schooldeal.application;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.school.schooldeal.base.BaseFragment;

import java.util.List;

/**
 * Created by U-nookia on 2016/12/20.
 * viewpager的adapter
 */

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

    private List<BaseFragment> fragments;

    public MyFragmentPagerAdapter(FragmentManager fm,List<BaseFragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
