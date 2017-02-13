package com.school.schooldeal.commen.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by U-nookia on 2016/12/23.
 * Toast工具包
 */

public class ToastUtil {

    public static Toast toast = null;

    public static void makeShortToast(Context context,String message){
        if (toast!=null){
            toast.cancel();
        }
        toast = Toast.makeText(context,message,Toast.LENGTH_SHORT);
        toast.show();
    }

    public static void makeLongToast(Context context,String message){
        if (toast!=null){
            toast.cancel();
        }
        toast = Toast.makeText(context,message,Toast.LENGTH_LONG);
        toast.show();
    }

    public static void makeShortToast(Context context,int message){
        if (toast!=null){
            toast.cancel();
        }
        toast = Toast.makeText(context,message,Toast.LENGTH_SHORT);
        toast.show();
    }

    public static void makeLongToast(Context context,int message){
        if (toast!=null){
            toast.cancel();
        }
        toast = Toast.makeText(context,message,Toast.LENGTH_LONG);
        toast.show();
    }
}
