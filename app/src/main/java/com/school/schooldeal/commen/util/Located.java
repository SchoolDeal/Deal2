package com.school.schooldeal.commen.util;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;

/**
 * Created by 教科书式的机智少年 on 2017/1/19.
 * 这是个定位工具类，在6.0适配起来有点麻烦，没有弄，以后慢慢完善吧
 * 最好在应用开始时就初始化它，并定义为全局变量。
 */

public class Located {
    private Context context;
    private AMapLocationClient mapLocationClient = null;
    private AMapLocationListener mapLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation aMapLocation) {
            if (aMapLocation != null) {
                if (aMapLocation.getErrorCode() == 0) {
                    Toast.makeText(context, aMapLocation.getCity(), Toast.LENGTH_SHORT).show();
                }else {
                    //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                    Log.e("AmapError","location Error, ErrCode:"
                            + aMapLocation.getErrorCode() + ", errInfo:"
                            + aMapLocation.getErrorInfo());
                }
            }
        }
    };
    private AMapLocationClientOption mapLocationClientOption = new AMapLocationClientOption();;

    public Located(Context context){
        this.context = context;
        mapLocationClient = new AMapLocationClient(this.context);
        mapLocationClient.setLocationListener(mapLocationListener);
        //mapLocationClientOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Battery_Saving);
        mapLocationClientOption.setOnceLocationLatest(true);
        mapLocationClientOption.setNeedAddress(true);
        mapLocationClient.setLocationOption(mapLocationClientOption);
    }
    //调用这个函数进行定位
    public void startLocated(){
        mapLocationClient.startLocation();
    }
    public void stopLocated(){
        mapLocationClient.stopLocation();
    }
    public void destoryLocated(){
        mapLocationClient.onDestroy();
    }
}
