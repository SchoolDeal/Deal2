package com.school.schooldeal.takeout.view;

import android.graphics.Bitmap;

import com.school.schooldeal.model.TakeawayRequest;
import com.school.schooldeal.model.TakeawayService;
import com.school.schooldeal.takeout.model.bean.TakeOutOrderBean;

/**
 * Created by GavynZhang on 2017/1/23 0:08.
 */

public interface ImplTakeoutDetailsActivity {
    void showDetailsData(TakeOutOrderBean takeOutOrderBean);
    void showRestaurantPicture(Bitmap picture);
    void showDetails(TakeawayRequest takeawayRequest);
    void captureRequestSuccess();
    void showStudentInfo(TakeawayService service);
    void requestHasBeenCaptured();
}
