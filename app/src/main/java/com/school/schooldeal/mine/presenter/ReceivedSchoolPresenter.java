package com.school.schooldeal.mine.presenter;

import android.content.Context;

import com.school.schooldeal.mine.model.ReceivedSchoolModel;
import com.school.schooldeal.mine.view.MineReceivedSchoolFragment;
import com.school.schooldeal.model.CommonService;

import java.util.List;

/**
 * Created by 教科书式的机智少年 on 2017/2/26.
 */

public class ReceivedSchoolPresenter {
    private MineReceivedSchoolFragment view;
    private Context context;
    private ReceivedSchoolModel model;
    public ReceivedSchoolPresenter(MineReceivedSchoolFragment view,Context context){
        this.view = view;
        this.context = context;
        model = new ReceivedSchoolModel(context,this);
    }
    public void setAdapter(List<CommonService> list){
        view.setAdapter(list);
    }

    public void getnewData(List<CommonService> list){
        view.setnewData(list);
    }

    public void stopRefresh(){
        view.stopRefresh();
    }

    public void setnewData(){
        model.getnewData();
    }

    public void getData(){
        model.getData();
    }
}
