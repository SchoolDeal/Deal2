package com.school.schooldeal.mine.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
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
import io.rong.imkit.RongIM;
import io.rong.imlib.model.UserInfo;

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
    private MaterialDialog dialog;

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
        nameTextModify.setText(BmobUser.getCurrentUser(context).getUsername());
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
                showUpdateUserNameDialog();
                //昵称
                break;
            case R.id.password_modify:
                //密码
                showUpdateUserPasswordDialog();
                break;
            case R.id.credit:
                //查看信用分
                ToastUtil.makeShortToast(context,"信用分为0，暂无您的信用分详细信息哦");
                break;
        }
    }

    private void showUpdateUserPasswordDialog() {
        new MaterialDialog.Builder(context)
                .content("输入新的密码")
                .input("请输入新的密码", "", new MaterialDialog.InputCallback() {
                    @Override
                    public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
                        showProgressDialog();
                        updateUserPassword(input);
                    }
                }).build().show();
    }

    private void updateUserPassword(CharSequence input) {
        BmobUser newUser = new BmobUser();
        newUser.setPassword(input.toString());
        BmobUser currentUser = BmobUser.getCurrentUser(context);
        newUser.update(context, currentUser.getObjectId(), new UpdateListener() {
            @Override
            public void onSuccess() {
                ToastUtil.makeShortToast(context,"更新用户密码成功");
                BmobUser.getCurrentUser(context,StudentUser.class).update(context);
                dismissProgressDialog();
            }

            @Override
            public void onFailure(int i, String s) {
                ToastUtil.makeShortToast(context,"更新用户密码失败:"+s);
                dismissProgressDialog();
            }
        });
    }

    private void showUpdateUserNameDialog() {
        new MaterialDialog.Builder(context)
                .content("输入新的用户名")
                .input("请输入新的用户名", nameTextModify.getText().toString(), new MaterialDialog.InputCallback() {
                    @Override
                    public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
                        showProgressDialog();
                        updateUserName(input);
                    }
                }).build().show();
    }

    private void updateUserName(final CharSequence input) {
        BmobUser newUser = new BmobUser();
        newUser.setUsername(input.toString());
        BmobUser currentUser = BmobUser.getCurrentUser(context);
        newUser.update(context, currentUser.getObjectId(), new UpdateListener() {
            @Override
            public void onSuccess() {
                ToastUtil.makeShortToast(context,"更新用户昵称成功");
                setName(input.toString());
                BmobUser.getCurrentUser(context,StudentUser.class).update(context);
                dismissProgressDialog();
            }

            @Override
            public void onFailure(int i, String s) {
                ToastUtil.makeShortToast(context,"更新用户昵称失败:"+s);
                dismissProgressDialog();
            }
        });
    }

    private void setName(String name) {
        nameTextModify.setText(name);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            if (data != null && requestCode == 100) {
                showProgressDialog();
                ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                upLoadImg(images.get(0).path);
            } else {
                Toast.makeText(this, "没有数据", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void showProgressDialog() {
        dialog = new MaterialDialog.Builder(context)
                .title("正在修改")
                .content("请稍候......")
                .progress(true,0)
                .build();
        dialog.show();
    }

    private void dismissProgressDialog(){
        dialog.dismiss();
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
                dismissProgressDialog();
            }
        });
    }

    private void updateUserImgMsg(final String fileUrl) {
        if (Util.IS_STUDENT){
            StudentUser newUser = new StudentUser();
            newUser.setImgUrl(fileUrl);
            newUser.setStudent(Util.IS_STUDENT);
            final StudentUser currentUser = BmobUser.getCurrentUser(context,StudentUser.class);
            newUser.update(context, currentUser.getObjectId(), new UpdateListener() {
                @Override
                public void onSuccess() {
                    ToastUtil.makeShortToast(context,"更新用户头像成功");
                    setHead(fileUrl);
                    makeImgCache(fileUrl);
                    BmobUser.getCurrentUser(context,StudentUser.class).update(context);
                    refreshUserImg(fileUrl);
                    dismissProgressDialog();
                }

                @Override
                public void onFailure(int i, String s) {
                    ToastUtil.makeShortToast(context,"更新用户头像失败:"+s);
                    dismissProgressDialog();
                }
            });
        }else {
            RestaurantUser newUser = new RestaurantUser();
            newUser.setImgUrl(fileUrl);
            newUser.setStudent(Util.IS_STUDENT);
            final RestaurantUser currentUser = BmobUser.getCurrentUser(context,RestaurantUser.class);
            newUser.update(context, currentUser.getObjectId(), new UpdateListener() {
                @Override
                public void onSuccess() {
                    ToastUtil.makeShortToast(context,"更新用户头像成功");
                    setHead(fileUrl);
                    makeImgCache(fileUrl);
                    BmobUser.getCurrentUser(context,RestaurantUser.class).update(context);
                    refreshUserImg(fileUrl);
                    dismissProgressDialog();
                }

                @Override
                public void onFailure(int i, String s) {
                    ToastUtil.makeShortToast(context,"更新用户头像失败:"+s);
                    dismissProgressDialog();
                }
            });
        }
    }

    public void refreshUserImg(String url){
        BmobUser user = BmobUser.getCurrentUser(context);
        UserInfo info = new UserInfo(user.getObjectId(),user.getUsername(), Uri.parse(url));
        RongIM.getInstance().refreshUserInfoCache(info);
    }

    private void makeImgCache(String url) {
        editor.putString("head_url",url);
        editor.apply();
    }

    public void setHead(String headUrl) {
        Glide.with(context).load(headUrl).into(imgModify);
    }
}
