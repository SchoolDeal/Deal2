package com.school.schooldeal.mine.presenter;

import android.content.Context;

import com.school.schooldeal.mine.model.MineOverSchoolModel;
import com.school.schooldeal.mine.view.MineOverSchoolFragment;
import com.school.schooldeal.model.CommonService;

import java.util.List;

/**
 * Created by 教科书式的机智少年 on 2017/2/27.
 */

public class MineOverSchoolPresenter {
    private Context context;
    private MineOverSchoolFragment view;
    private MineOverSchoolModel model;
    public MineOverSchoolPresenter(Context context, MineOverSchoolFragment view){
        this.context = context;
        this.view = view;
        model = new MineOverSchoolModel(context,this);
    }

    public void getData(){
        model.getData();
    }
    public void setData(List<CommonService> list){
        view.setAdapter(list);
    }
    public void setnewData(List<CommonService> list){
        view.setnewData(list);
    }
    public void getnewData(){
        model.getnewData();
    }
    public void stopRefresh(){
        view.stopRefresh();
    }
}
