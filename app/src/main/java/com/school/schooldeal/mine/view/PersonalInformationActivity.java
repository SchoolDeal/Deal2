package com.school.schooldeal.mine.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.school.schooldeal.R;
import com.school.schooldeal.base.BaseActivity;
import com.school.schooldeal.commen.util.ToastUtil;
import com.school.schooldeal.commen.util.Util;
import com.school.schooldeal.model.Student;
import com.school.schooldeal.sign.model.RestaurantUser;
import com.school.schooldeal.sign.model.StudentUser;

import java.io.File;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.listener.UpdateListener;
import cn.bmob.v3.listener.UploadFileListener;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by U-nookia on 2017/2/21.
 */

public class PersonalInformationActivity extends BaseActivity {

    @BindView(R.id.toolBar_modify)
    Toolbar toolBarModify;
    @BindView(R.id.img_modify)
    CircleImageView imgModify;
    @BindView(R.id.name_text_modify)
    TextView nameTextModify;
    @BindView(R.id.head_modify)
    RelativeLayout headModify;
    @BindView(R.id.name_modify)
    RelativeLayout nameModify;
    @BindView(R.id.password_modify)
    RelativeLayout passwordModify;
    @BindView(R.id.credit)
    RelativeLayout credit;

    private SharedPreferences.Editor editor;
    private SharedPreferences sp;

    public static Intent getIntentToPersonal(Context context) {
        Intent intent = new Intent(context, PersonalInformationActivity.class);
        return intent;
    }

    @Override
    protected void initData() {
        toolBarModify.setTitle("修改个人信息");
        toolBarModify.setTitleTextColor(Color.WHITE);
        sp = getSharedPreferences("config", MODE_PRIVATE);
        editor = sp.edit();
        String url = sp.getString("head_url", Util.defaultHeadImg);
        setHead(url);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_persional_information;
    }

    @OnClick({R.id.head_modify, R.id.name_modify, R.id.password_modify, R.id.credit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.head_modify:
                //头像
                Intent intent = new Intent(this, ImageGridActivity.class);
                startActivityForResult(intent,100);
                break;
            case R.id.name_modify:
                //昵称
                break;
            case R.id.password_modify:
                //密码
                break;
            case R.id.credit:
                //查看信用分
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            if (data != null && requestCode == 100) {
                ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                setHead(images.get(0).path);
                upLoadImg(images.get(0).path);
            } else {
                Toast.makeText(this, "没有数据", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void upLoadImg(String path) {
        final BmobFile file = new BmobFile(new File(path));
        file.uploadblock(context, new UploadFileListener() {
            @Override
            public void onSuccess() {
                String url = file.getFileUrl(context);
                updateUserImgMsg(url);
            }

            @Override
            public void onFailure(int i, String s) {
                ToastUtil.makeShortToast(context,"头像上传失败:"+s);
            }
        });
    }

    private void updateUserImgMsg(final String fileUrl) {
        if (Util.IS_STUDENT){
            StudentUser newUser = new StudentUser();
            newUser.setImgUrl(fileUrl);
            StudentUser currentUser = BmobUser.getCurrentUser(context,StudentUser.class);
            newUser.update(context, currentUser.getObjectId(), new UpdateListener() {
                @Override
                public void onSuccess() {
                    ToastUtil.makeShortToast(context,"更新用户头像成功");
                    makeImgCache(fileUrl);
                }

                @Override
                public void onFailure(int i, String s) {
                    ToastUtil.makeShortToast(context,"更新用户头像失败:"+s);
                }
            });
        }else {
            RestaurantUser newUser = new RestaurantUser();
            newUser.setImgUrl(fileUrl);
            RestaurantUser currentUser = BmobUser.getCurrentUser(context,RestaurantUser.class);
            newUser.update(context, currentUser.getObjectId(), new UpdateListener() {
                @Override
                public void onSuccess() {
                    ToastUtil.makeShortToast(context,"更新用户头像成功");
                    makeImgCache(fileUrl);
                }

                @Override
                public void onFailure(int i, String s) {
                    ToastUtil.makeShortToast(context,"更新用户头像失败:"+s);
                }
            });
        }
    }

    private void makeImgCache(String url) {
        editor.putString("head_url",url);
        editor.apply();
    }

    public void setHead(String headUrl) {
        Glide.with(context).load(headUrl).into(imgModify);
    }
}
