package com.school.schooldeal.schooltask.model;

import android.content.Context;

import com.school.schooldeal.commen.util.ToastUtil;
import com.school.schooldeal.model.CommonRequest;
import com.school.schooldeal.model.CommonService;
import com.school.schooldeal.model.Student;
import com.school.schooldeal.schooltask.presenter.SchoolTaskDetailed;
import com.school.schooldeal.sign.model.StudentUser;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.GetListener;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by 教科书式的机智少年 on 2017/2/15.
 */

public class SchoolTaskDetailedModel implements ImplSchoolTaskDetailedModel{
    private Context context;
    private SchoolTaskDetailed presenter;
    public SchoolTaskDetailedModel(Context context,SchoolTaskDetailed presenter){
        this.context = context;
        this.presenter = presenter;
    }
    @Override
    public void getRobMessage(final CommonRequest commonRequest) {
        BmobQuery<CommonRequest> query = new BmobQuery<CommonRequest>();
        query.getObject(context, commonRequest.getObjectId(), new GetListener<CommonRequest>() {
            @Override
            public void onSuccess(CommonRequest request) {
                if (request.getType() == 0){
                    StudentUser user = BmobUser.getCurrentUser(context,StudentUser.class);
                    CommonService commonService = new CommonService();
                    Student student = new Student();
                    student.setObjectId(user.getObjectId());
                    commonService.setRequest(commonRequest);
                    commonService.setStudent(student);
                    commonService.setRemuneration(commonRequest.getRemuneration());
                    commonService.save(context, new SaveListener() {
                        @Override
                        public void onSuccess() {
                            commonRequest.setType(1);
                            commonRequest.update(context);
                            ToastUtil.makeShortToast(context,"抢单成功");
                            presenter.setType();
                        }

                        @Override
                        public void onFailure(int i, String s) {
                            ToastUtil.makeShortToast(context,"抢单失败");
                        }
                    });
                }else {
                    ToastUtil.makeShortToast(context,"糟糕，单子已经被抢走了");
                    presenter.setType();
                }
            }

            @Override
            public void onFailure(int i, String s) {
                ToastUtil.makeShortToast(context,"抢单失败");
            }
        });
    }
}
