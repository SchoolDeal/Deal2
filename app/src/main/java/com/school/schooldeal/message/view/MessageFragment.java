package com.school.schooldeal.message.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.school.schooldeal.R;
import com.school.schooldeal.base.BaseFragment;

import butterknife.ButterKnife;

/**
 * Created by U-nookia on 2016/12/20.
 */

public class MessageFragment extends BaseFragment {

    @Override
    protected void initData() {

    }

    @Override
    protected int getResourceId() {
        return R.layout.fragment_message;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }
}
