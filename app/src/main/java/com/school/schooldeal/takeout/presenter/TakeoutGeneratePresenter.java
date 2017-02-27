package com.school.schooldeal.takeout.presenter;

import android.content.Context;
import android.util.Log;

import com.school.schooldeal.commen.util.TimeUtils;
import com.school.schooldeal.model.Apartment;
import com.school.schooldeal.model.School;
import com.school.schooldeal.model.TakeawayRequest;
import com.school.schooldeal.sign.model.RestaurantUser;
import com.school.schooldeal.takeout.TakeawayStatusConsts;
import com.school.schooldeal.takeout.model.impl.ImplTakeoutGenerateModel;
import com.school.schooldeal.takeout.model.bean.TakeoutGenerateBean;
import com.school.schooldeal.takeout.model.model.TakeoutGenerateModel;
import com.school.schooldeal.takeout.view.ImplTakeoutGenerateActivity;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;

/**
 * Created by GavynZhang on 2017/2/4 23:19.
 */

public class TakeoutGeneratePresenter {
    public static final String className = "TGPresenter";

    private ImplTakeoutGenerateActivity mGenerateActivity;
    private ImplTakeoutGenerateModel mGenerateModel;
    private TakeawayRequest mTakeawayRequest;
    private Context mContext;

    //暂存学校加载结果
    private List<School> mSchools;
    //暂存公寓加载结果
    private List<Apartment> mApartments;

//    //选择的学校的ID
//    private String selectedSchoolID;
//    //选择的公寓的ID
//    private String selectedApartmentID;

    //TMP
    private School mSchool;
    private Apartment mApartment;

    public TakeoutGeneratePresenter(ImplTakeoutGenerateActivity generateActivity, Context context) {
        this.mContext = context;
        this.mGenerateActivity = generateActivity;
        mGenerateModel = new TakeoutGenerateModel(this, context);
    }

    /**
     * 此处需要修改
     *
     * @param generateBean 从View获取的填写数据
     */
    public void generateTakeawayServiceRequest(TakeoutGenerateBean generateBean){
        Log.d(className, "On Presenter, generateTakeawayServiceRequest");

        mTakeawayRequest = new TakeawayRequest();
        mTakeawayRequest.setRemuneration(generateBean.getRemuneration());
        mTakeawayRequest.setGeneratedTime(TimeUtils.getCurTimeString());
        if (!"".equals(generateBean.getRemarks()))
            mTakeawayRequest.setRemarks(generateBean.getRemarks());
        mTakeawayRequest.setAmount(generateBean.getAmount());
        mTakeawayRequest.setRestaurant(BmobUser.getCurrentUser(mContext, RestaurantUser.class));
        mTakeawayRequest.setConsigneeName(generateBean.getConsigneeName());
        mTakeawayRequest.setBedroom(generateBean.getBedroom());
        //收货学生的电话
        mTakeawayRequest.setConsigneePhoneNum(generateBean.getStuPhoneNum());
        //设置状态
        mTakeawayRequest.setStatus(TakeawayStatusConsts.NOT_BEING_TAKEN);
        if (! (mSchool == null || mApartment == null)) {
            mTakeawayRequest.setSchool(mSchool);
            mTakeawayRequest.setApartment(mApartment);
        }
        mGenerateModel.saveToBmob(mTakeawayRequest);
    }

    /**
     * Model保存数据成功的回调
     * @param objectID 保存到Bmob的对象的ID
     */
    public void saveToBmobSuccess(String objectID){
        mGenerateActivity.saveSuccess();
    }

    /**
     * 从model里面加载学校数据
     */
    public void loadSchool(){
        mGenerateModel.loadSchoolData();
    }

    /**
     * Model加载学校数据成功的回调
     *
     * @param schools: 学校数据
     */
    public void loadSchoolSuccess(List<School> schools){
        this.mSchools = schools;
        List<String> schoolNames = new ArrayList<>();
        for (School school : schools){
            schoolNames.add(school.getSchoolName());
        }
        mGenerateActivity.loadSchoolSuccess(schoolNames);
    }

    /**
     * 加载学校数据失败
     * @param i error code
     * @param s error string
     */
    public void loadSchoolFail(int i, String s){
        Log.e(className,"Load school data error code: " + i + " __"+s);
    }

    /**
     * 设置已选择的学校
     * @param position index of selected school
     */
    public void setSelectedSchool(int position){
        Log.d(className,"Selected school: "+position+"  "+mSchools.get(position).toString());
        //设置ID
        //selectedSchoolID = mSchools.get(position).getObjectId();
        mSchool = mSchools.get(position);
    }

    /**
     * 加载公寓数据
     */
    public void loadApartment(){
        //if ("".equals(selectedSchoolID)){
        if (mSchool == null){
            Log.e(className, "尚未选择学校");
        }else{
            Log.d(className, "selectedSchool");
            mGenerateModel.loadApartmentData(mSchool);
        }
    }

    /**
     * Model加载Apartment成功的回调
     *
     * @param apartments 加载出的公寓数据
     */
    public void loadApartmentSuccess(List<Apartment> apartments){
        Log.d(className,"loadApartmentSuccess");
        this.mApartments = apartments;
        List<String> apartmentNames = new ArrayList<>();
        for (Apartment apartment : apartments){
            apartmentNames.add(apartment.getApartmentName());
        }
        mGenerateActivity.loadApartmentSuccess(apartmentNames);
    }

    /**
     * Model 加载Apartment失败的回调
     *
     * @param i error code
     * @param s error string
     */
    public void loadApartmentFail(int i, String s){
        Log.e(className,"Load apartment data error code: " + i + " __"+s);
    }

    /**
     * 设置已选择的公寓
     * @param position index of selected apartment
     */
    public void setSelectedApartment(int position){
        Log.d(className,"Selected apartment: "+position+"  "+mApartments.get(position).toString());
        //selectedApartmentID = mApartments.get(position).getObjectId();
        mApartment = mApartments.get(position);
    }

    public void saveRequestToBmobFail(int i, String s){
        mGenerateActivity.saveFail();
    }

}
