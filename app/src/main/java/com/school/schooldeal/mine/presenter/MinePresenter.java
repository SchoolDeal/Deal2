package com.school.schooldeal.mine.presenter;

import android.content.Context;

import com.school.schooldeal.mine.model.MineAdapter;
import com.school.schooldeal.mine.model.MineItems;
import com.school.schooldeal.mine.model.MineRecyclerItemBean;
import com.school.schooldeal.mine.view.ImplMineFragment;

import java.util.ArrayList;
import java.util.List;

import io.rong.imkit.RongIM;
import io.rong.imlib.model.CSCustomServiceInfo;

/**
 * Created by U-nookia on 2017/2/14.
 */

public class MinePresenter {
    private ImplMineFragment mineFragment;
    private List<MineRecyclerItemBean> items;
    private Context context;
    private MineAdapter adapter;

    public MinePresenter(Context context,ImplMineFragment mineFragment) {
        this.context = context;
        this.mineFragment = mineFragment;
        items = new ArrayList<>();
        items = MineItems.getItems();
        adapter = new MineAdapter(context);
    }

    public void initAdapter(){
        adapter.setData(items);
        mineFragment.setAdapter(adapter);
    }

    public void reduceItem(int position){
        items.remove(position);
        adapter.notifyDataSetChanged();
    }
}
