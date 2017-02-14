package com.school.schooldeal.mine.view;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.school.schooldeal.R;
import com.school.schooldeal.base.BaseFragment;
import com.school.schooldeal.commen.util.ToastUtil;
import com.school.schooldeal.commen.util.Util;
import com.school.schooldeal.mine.model.MineAdapter;
import com.school.schooldeal.mine.presenter.MinePresenter;
import com.school.schooldeal.sign.model.RestaurantUser;
import com.school.schooldeal.sign.model.StudentUser;
import com.school.schooldeal.sign.view.SignInAcitivty;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bmob.v3.BmobUser;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by U-nookia on 2016/12/20.
 */

public class MineFragment extends BaseFragment implements ImplMineFragment{


    @BindView(R.id.head)
    CircleImageView head;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.score)
    TextView score;
    @BindView(R.id.top)
    RelativeLayout topLayout;
    @BindView(R.id.list_mine)
    RecyclerView listMine;

    private MaterialDialog dialog;

    @Override
    protected void initData() {
        initMyMessage();
        initRecycler();
        MinePresenter presenter = new MinePresenter(getContext(),this);
        presenter.initAdapter();
    }

    private void initRecycler() {
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        listMine.setLayoutManager(manager);
        listMine.addItemDecoration(new MyItemDecoration());
    }

    private void initMyMessage() {
        int score_num;
        if (Util.IS_STUDENT) {
            StudentUser user = BmobUser.getCurrentUser(getContext(),StudentUser.class);
            String name_str = user.getUsername();
            name.setText(name_str);
            if (Util.FIRST_TIME_SIGNIN) score_num = 0;
            else score_num = user.getCreditScore();
            score.setText("信用分："+score_num+"");
        } else {
            RestaurantUser user = BmobUser.getCurrentUser(getContext(),RestaurantUser.class);
            String name_str = user.getUsername();
            name.setText(name_str);
            score.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    protected int getResourceId() {
        return R.layout.fragment_mine;
    }

    @OnClick(R.id.top)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.top:
                showChooseDialog();
                break;
        }
    }

    private void showChooseDialog() {
        dialog = new MaterialDialog.Builder(getContext())
                .items(R.array.top)
                .itemsCallback(new MaterialDialog.ListCallback() {
                    @Override
                    public void onSelection(MaterialDialog dialog, View itemView, int position, CharSequence text) {
                        clickTopDialog(position);
                    }
                }).build();
        dialog.show();
    }

    private void clickTopDialog(int position) {
        switch (position){
            case 0:
                //修改头像
                break;
            case 1:
                //修改个人信息
                break;
            case 2:
                //修改密码
                break;
            case 3:
                //退出登录
                BmobUser.logOut(getContext());
                ToastUtil.makeLongToast(getContext(),"退出登录成功");
                getContext().startActivity(SignInAcitivty.getIntentToSignInActivity(getContext()));
                getActivity().finish();
                break;
        }
    }

    @Override
    public void setAdapter(MineAdapter adapter){
        listMine.setAdapter(adapter);
    }
}
