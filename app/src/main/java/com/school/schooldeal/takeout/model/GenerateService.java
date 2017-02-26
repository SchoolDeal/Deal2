package com.school.schooldeal.takeout.model;

import android.content.Context;
import android.util.Log;

import com.school.schooldeal.commen.util.ToastUtil;
import com.school.schooldeal.model.TakeawayRequest;
import com.school.schooldeal.model.TakeawayService;
import com.school.schooldeal.sign.model.StudentUser;
import com.school.schooldeal.takeout.TakeawayStatusConsts;
import com.school.schooldeal.takeout.model.bean.TakeOutOrderBean;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.GetListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by GavynZhang on 2017/2/20 16:01.
 */

public class GenerateService {

    public static final String className = "GenerateService";
    public static final int ADAPTER = 0;
    public static final int DETAIL = 1;

    private ImplCaptureRequest mCaptureRequest;

    private int status;
    private Context context;

    public GenerateService(Context context, ImplCaptureRequest captureRequest) {
        mCaptureRequest = captureRequest;
        this.context = context;
    }

    /**
     * 用于生成TakeawayService
     * @param takeOutOrderBean 相关数据Bean
     */
    public void generateService(TakeOutOrderBean takeOutOrderBean, int status){
        this.status = status;
        StudentUser studentUser = BmobUser.getCurrentUser(context, StudentUser.class);
        TakeawayService service = new TakeawayService();
        service.setStudent(studentUser);

        TakeawayRequest request = new TakeawayRequest();
        request.setObjectId(takeOutOrderBean.getId());
        service.setRequest(request);

        service.setFinish(false);
        service.setRemuneration(takeOutOrderBean.getMoney());
        service.setNumber(takeOutOrderBean.getAmount());
        service.save(context, new SaveListener() {
            @Override
            public void onSuccess() {
                ToastUtil.makeShortToast(context, "抢单成功");
                mCaptureRequest.captureRequestSuccess();
            }

            @Override
            public void onFailure(int i, String s) {
                Log.d(className, "Save to bmob Failure, code: "+i+" message: "+s);
            }
        });

        upDateRequest(context, takeOutOrderBean.getId());
    }

    /**
     * 用于修改TakeawayRequest的状态
     * @param context 上下文
     * @param objectID TakeawayRequest的ObjectID
     */
    private void upDateRequest(Context context, String objectID){
        TakeawayRequest takeawayRequest = new TakeawayRequest();
        takeawayRequest.setStatus(TakeawayStatusConsts.HAS_BEING_TAKEN);
        takeawayRequest.setObjectId(objectID);
        takeawayRequest.update(context, new UpdateListener() {
            @Override
            public void onSuccess() {
                Log.d(className, "Set TakeawayRequest has being taken success! ");
            }

            @Override
            public void onFailure(int i, String s) {
                Log.e(className, "Set TakeawayRequest error: "+i+" ,message: "+s);
            }
        });
    }

    /**
     * 查询外卖单是否已被抢下
     * @param takeOutOrderBean
     */
    public void queryTheRequestStatus(TakeOutOrderBean takeOutOrderBean){
        Log.d(className, "Query takeawayRequest: ObjectID: "+takeOutOrderBean.getId());
        BmobQuery<TakeawayRequest> requestBmobQuery = new BmobQuery<>();
        requestBmobQuery.getObject(context, takeOutOrderBean.getId(), new GetListener<TakeawayRequest>() {
            @Override
            public void onSuccess(TakeawayRequest takeawayRequest) {
                if (takeawayRequest.getStatus() == TakeawayStatusConsts.HAS_BEING_TAKEN
                        || takeawayRequest.getStatus() == TakeawayStatusConsts.COMPLETED){
                    mCaptureRequest.requestHasBeenCaptured();
                }else{
                    mCaptureRequest.requestIsNotCaptured();
                }
            }

            @Override
            public void onFailure(int i, String s) {
                Log.e(className, "Query the request status fail, error: "+i+" message: "+s);
            }
        });
    }
}
