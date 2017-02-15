package com.school.schooldeal.schooltask.presenter;

import android.content.Context;

import com.school.schooldeal.schooltask.view.ImplSchoolTaskRelease;

/**
 * Created by 教科书式的机智少年 on 2017/2/15.
 */

public class SchoolTaskReasePresenter {
    private ImplSchoolTaskRelease release;
    private Context context;
    public SchoolTaskReasePresenter(ImplSchoolTaskRelease release, Context context){
        this.release = release;
        this.context = context;
    }
    public void sendMessage(String destination, String name, String phone, final String refund, String text){
        /*CommonRequest commonRequest = new CommonRequest();
        commonRequest.setRequestID("1111");
        commonRequest.setRequestContent(destination);
        commonRequest.setRequestContent(text);
        commonRequest.setRemuneration(Float.parseFloat(refund));
        Student student = new Student();
        student.setObjectId("04db9a2aaf");
        commonRequest.setStudent(student);
        commonRequest.save(context, new SaveListener() {
            @Override
            public void onSuccess() {
                Log.e("data","success");
            }

            @Override
            public void onFailure(int i, String s) {
                Log.e("data","failed");
            }
        });*/

        /*String bq = "select * from CommonRequest where student in (select * from _User where name = '渝州铁板烧') ";
        new BmobQuery<CommonRequest>().doSQLQuery(context, bq, new SQLQueryListener<CommonRequest>() {
            @Override
            public void done(BmobQueryResult<CommonRequest> bmobQueryResult, BmobException e) {
                if (e==null){
                    List<CommonRequest> list = (List<CommonRequest>)bmobQueryResult.getResults();
                    if (list!= null && list.size()>0){
                        for (int i = 0;i<list.size();i++){
                            Log.e("data",list.get(i).getRequestContent());
                        }
                    }else {
                        Log.e("data","数据为空");
                    }
                }else {
                    Log.e("data","failed");
                    Log.e("smile", "错误码："+e.getErrorCode()+"，错误描述："+e.getMessage());
                }
            }
        });*/

        /*String bql = "select * from CommonRequest where store in (select * from Store where storeType = 1)";
        new BmobQuery<CommonRequest>().doSQLQuery(context, bql, new SQLQueryListener<CommonRequest>() {
            @Override
            public void done(BmobQueryResult<CommonRequest> bmobQueryResult, BmobException e) {
                if (e==null){
                    List<CommonRequest> list = (List<CommonRequest>)bmobQueryResult.getResults();
                    if (list!= null && list.size()>0){
                        for (int i = 0;i<list.size();i++){
                            Log.e("data",list.get(i).getRequestContent());
                        }
                    }else {
                        Log.e("data","数据为空");
                    }
                }else {
                    Log.e("data","failed");
                    Log.e("smile", "错误码："+e.getErrorCode()+"，错误描述："+e.getMessage());
                }
            }
        });*/

    }
}
