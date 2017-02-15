package com.school.schooldeal.commen.util;

import android.content.Context;

import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;

/**
 * Created by 教科书式的机智少年 on 2017/2/4.
 * 更新版定位工具类，调用startlocated之前需要在之前加上
 * if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && this.checkSelfPermission(ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED
 &&this.checkSelfPermission(ACCESS_FINE_LOCATION)!=PackageManager.PERMISSION_GRANTED){
 requestPermissions(new String[]{ACCESS_COARSE_LOCATION,ACCESS_FINE_LOCATION},0);
 }
 用以申请定位权限
 */

/*
* 启动定位的监听器需要自己写，示例如下
* 可以通过实现AMapLocationListener接口，也可以通过创造接口类对象的方法实现
* @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (aMapLocation!= null){
            if (aMapLocation.getErrorCode() == 0){
                //这里写想要做的事
            }else {
                Log.e("AmapError","location Error, ErrCode:"
                        + aMapLocation.getErrorCode() + ", errInfo:"
                        + aMapLocation.getErrorInfo());
            }
        }
    }
    startLocated(this);
* */

public class Located{
    private  AMapLocationClient locationClient;
    private  AMapLocationListener locationListener;
    private  AMapLocationClientOption locationClientOption;
    private Context context;
    public Located(Context context){
        this.context = context;
        locationClient = new AMapLocationClient(context.getApplicationContext());
        locationClientOption = new AMapLocationClientOption();
        locationClientOption.setOnceLocationLatest(true);  //设置只定位一次
        locationClient.setLocationOption(locationClientOption);
    }
    public void starLocated(AMapLocationListener aMapLocationListener){
        locationClient.setLocationListener(aMapLocationListener);
        locationClient.startLocation();
    }
    public void stopLocated(){
        locationClient.stopLocation();
    }
    public void destoryLocated(){
        locationClient.onDestroy();
    }
}


/*这是处理定位结果的方法
amapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
amapLocation.getLatitude();//获取纬度
amapLocation.getLongitude();//获取经度
amapLocation.getAccuracy();//获取精度信息
amapLocation.getAddress();//地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
amapLocation.getCountry();//国家信息
amapLocation.getProvince();//省信息
amapLocation.getCity();//城市信息
amapLocation.getDistrict();//城区信息
amapLocation.getStreet();//街道信息
amapLocation.getStreetNum();//街道门牌号信息
amapLocation.getCityCode();//城市编码
amapLocation.getAdCode();//地区编码
amapLocation.getAoiName();//获取当前定位点的AOI信息
amapLocation.getBuildingId();//获取当前室内定位的建筑物Id
amapLocation.getFloor();//获取当前室内定位的楼层
amapLocation.getGpsStatus();//获取GPS的当前状态*/
