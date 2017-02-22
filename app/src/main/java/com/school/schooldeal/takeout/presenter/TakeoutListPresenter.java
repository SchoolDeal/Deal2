package com.school.schooldeal.takeout.presenter;

import android.content.Context;
import android.util.Log;

import com.school.schooldeal.takeout.model.TakeOutDataAdapter;
import com.school.schooldeal.takeout.model.bean.TakeOutOrderBean;
import com.school.schooldeal.takeout.model.impl.ImplTakeoutListModel;
import com.school.schooldeal.takeout.model.model.TakeoutListModel;
import com.school.schooldeal.takeout.view.ImplTakeoutListActivity;
import com.school.schooldeal.takeout.view.TakeoutListActivity;

import java.util.List;

/**
 * Created by GavynZhang on 2017/2/21 17:49.
 */

public class TakeoutListPresenter {

    public static final String className = "TOListPresenter";

    private Context mContext;
    private TakeOutDataAdapter mDataAdapter;
    private ImplTakeoutListModel mTakeoutListModel;
    private ImplTakeoutListActivity mTakeoutListActivity;

    public TakeoutListPresenter(Context context, ImplTakeoutListActivity takeoutListActivity){
        this.mContext = context;
        mDataAdapter = new TakeOutDataAdapter(mContext);
        mTakeoutListModel = new TakeoutListModel(context,this);
        this.mTakeoutListActivity = takeoutListActivity;
    }

    public void loadData(int status){
        if (status == TakeoutListActivity.PUBLISHED){
            mTakeoutListModel.loadMinePublished();
        }else if (status == TakeoutListActivity.FINISHED){
            mTakeoutListModel.loadMineFinished();
        }else if (status == TakeoutListActivity.RECEIVED){
            mTakeoutListModel.loadMineReceived();
        }
    }

    public void loadDataSuccess(List<TakeOutOrderBean> orders){
        mDataAdapter.setData(orders);
        mTakeoutListActivity.setAdapter(mDataAdapter);
    }

    public void loadDataError(int i, String s){
        Log.e(className, " Load data error: "+i+" message: "+s);
    }
}
