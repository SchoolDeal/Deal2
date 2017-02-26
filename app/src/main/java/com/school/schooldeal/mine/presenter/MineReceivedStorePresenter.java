package com.school.schooldeal.mine.presenter;

import android.content.Context;

import com.school.schooldeal.mine.model.ImplMineReceivedStoreModel;
import com.school.schooldeal.mine.model.MineReceivedStoreModel;
import com.school.schooldeal.mine.view.ImplMineReceivedStoreFragment;
import com.school.schooldeal.takeout.model.TakeOutDataAdapter;
import com.school.schooldeal.takeout.model.bean.TakeOutOrderBean;

import java.util.List;

/**
 * Created by GavynZhang on 2017/2/26 20:44.
 */

public class MineReceivedStorePresenter {

    private Context mContext;
    private ImplMineReceivedStoreFragment mReceivedStoreFragment;
    private ImplMineReceivedStoreModel mReceivedStoreModel;
    private TakeOutDataAdapter mDataAdapter;

    public MineReceivedStorePresenter(Context context, ImplMineReceivedStoreFragment receivedStoreFragment) {
        mContext = context;
        mReceivedStoreFragment = receivedStoreFragment;
        mReceivedStoreModel = new MineReceivedStoreModel(context, this);
        mDataAdapter = new TakeOutDataAdapter(context);
    }

    public void initAdapter(){
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


}
