package com.school.schooldeal.mine.presenter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.school.schooldeal.mine.model.ImplMineReceivedStoreModel;
import com.school.schooldeal.mine.model.MineReceivedStoreModel;
import com.school.schooldeal.mine.view.ImplMineOverStoreFragment;
import com.school.schooldeal.mine.view.ImplMineReceivedStoreFragment;
import com.school.schooldeal.takeout.model.TakeOutDataAdapter;
import com.school.schooldeal.takeout.model.bean.TakeOutOrderBean;

import java.util.List;

/**
 * Created by GavynZhang on 2017/2/26 20:44.
 */

public class MineReceivedStorePresenter {

    public static final int RECEIVED = 0;
    public static final int OVER = 1;

    private Context mContext;
    private ImplMineReceivedStoreFragment mReceivedStoreFragment;
    private ImplMineOverStoreFragment mMineOverStoreFragment;
    private ImplMineReceivedStoreModel mReceivedStoreModel;
    private TakeOutDataAdapter mDataAdapter;

    private int status;

    public MineReceivedStorePresenter(Context context, ImplMineReceivedStoreFragment receivedStoreFragment) {
        status = RECEIVED;
        mContext = context;
        mReceivedStoreFragment = receivedStoreFragment;
        mReceivedStoreModel = new MineReceivedStoreModel(context, this, RECEIVED);
        mDataAdapter = new TakeOutDataAdapter(context);
    }

    public MineReceivedStorePresenter(Context context, ImplMineOverStoreFragment mineOverStoreFragment) {
        status = OVER;
        mContext = context;
        mMineOverStoreFragment = mineOverStoreFragment;
        mReceivedStoreModel = new MineReceivedStoreModel(context, this, OVER);
        mDataAdapter = new TakeOutDataAdapter(context);
    }

    public void initAdapter(){
        if (status == RECEIVED)
            mReceivedStoreModel.loadService();
        if (status == OVER)
            mReceivedStoreModel.loadService();
    }

    public void loadOrdersSuccess(List<TakeOutOrderBean> orders){
        if (orders != null) {
            mDataAdapter.setData(orders);
            mReceivedStoreFragment.setAdapter(mDataAdapter);
            mReceivedStoreFragment.loadSuccess();
        }else{
            mReceivedStoreFragment.loadDataEmpty();
        }
    }

    public void loadOverOrdersSuccess(List<TakeOutOrderBean> orders){
        if (orders != null) {
            mDataAdapter.setData(orders);
            mMineOverStoreFragment.setAdapter(mDataAdapter);
            mMineOverStoreFragment.loadSuccess();
        }else{
            mMineOverStoreFragment.loadDataEmpty();
        }
    }

}
