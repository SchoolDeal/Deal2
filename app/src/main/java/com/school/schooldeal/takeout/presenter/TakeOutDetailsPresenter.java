package com.school.schooldeal.takeout.presenter;

import android.content.Context;

import com.school.schooldeal.model.TakeawayRequest;
import com.school.schooldeal.model.TakeawayService;
import com.school.schooldeal.takeout.model.GenerateService;
import com.school.schooldeal.takeout.model.ImplCaptureRequest;
import com.school.schooldeal.takeout.model.bean.TakeOutOrderBean;
import com.school.schooldeal.takeout.model.impl.ImplTakeoutDetailsModel;
import com.school.schooldeal.takeout.model.model.TakeoutDetailsModel;
import com.school.schooldeal.takeout.view.ImplTakeoutDetailsActivity;

/**
 * Created by GavynZhang on 2017/1/23 0:52.
 */

public class TakeOutDetailsPresenter implements ImplCaptureRequest{
    private Context mContext;
    private ImplTakeoutDetailsActivity mDetailsActivity;
    private ImplTakeoutDetailsModel mDetailsModel;
    private TakeOutOrderBean mTakeOutOrderBean;
    private GenerateService mGenerateService;

    public TakeOutDetailsPresenter(Context context, ImplTakeoutDetailsActivity takeoutDetailsActivity){
        this.mContext = context;
        this.mDetailsActivity = takeoutDetailsActivity;
        mDetailsModel = new TakeoutDetailsModel(context,this);
        mGenerateService = new GenerateService(mContext,this);
    }

    public void loadRestaurantPicture(){

    }

    /**
     * 用于Activity加载数据调用
     * @param requestID TakeawayRequest 表的ObjectID
     */
    public void loadTakeawayDetails(String requestID){
        mDetailsModel.loadTakeawayDetails(requestID);
    }

    /**
     * @param takeawayRequest model加载成功的回调
     */
    public void loadTakeawayDetailsSuccess(TakeawayRequest takeawayRequest){
        mDetailsActivity.showDetails(takeawayRequest);

        mTakeOutOrderBean = new TakeOutOrderBean(
                takeawayRequest.getObjectId(),
                takeawayRequest.getAmount(),
                takeawayRequest.getApartment().getApartmentName() + takeawayRequest.getBedroom()+"寝室",
                takeawayRequest.getRestaurant().getName(),
                takeawayRequest.getRestaurant().getAddress(),
                takeawayRequest.getRemuneration());
        mTakeOutOrderBean.setId(takeawayRequest.getObjectId());
        mTakeOutOrderBean.setStatus(takeawayRequest.getStatus());
    }

    public void captureRequest(){
        mGenerateService.generateService(mTakeOutOrderBean, GenerateService.DETAIL);
    }

    @Override
    public void captureRequestSuccess() {
        mDetailsActivity.captureRequestSuccess();
    }

    /**
     * 用于加载学生信息
     */
    public void loadStudentInfo(){
        mDetailsModel.loadStudentInfo();
    }

    /**
     * 请求学生以及服务相关信息
     * @param takeawayService 请求的数据
     */
    public void loadStudentInfoSuccess(TakeawayService takeawayService){
        mDetailsActivity.showStudentInfo(takeawayService);
    }
}
