package com.school.schooldeal.mine.view;

import android.view.View;
import android.widget.TextView;

import com.school.schooldeal.R;
import com.school.schooldeal.base.BaseFragment;
import com.school.schooldeal.commen.util.Util;
import com.school.schooldeal.sign.model.RestaurantUser;
import com.school.schooldeal.sign.model.StudentUser;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bmob.v3.BmobUser;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by U-nookia on 2016/12/20.
 */

public class MineFragment extends BaseFragment {


    @BindView(R.id.head)
    CircleImageView head;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.score)
    TextView score;
    @BindView(R.id.my_order_release)
    TextView myOrderRelease;
    @BindView(R.id.my_order_receive)
    TextView myOrderReceive;
    @BindView(R.id.my_order_finish)
    TextView myOrderFinish;
    @BindView(R.id.feed_back)
    TextView feedBack;
    @BindView(R.id.about_us)
    TextView aboutUs;
    @BindView(R.id.version)
    TextView version;

    @Override
    protected void initData() {
        initMyMessage();
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

    @OnClick({R.id.head, R.id.my_order_release, R.id.my_order_receive, R.id.my_order_finish, R.id.feed_back, R.id.about_us, R.id.version})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.head:
                break;
            case R.id.my_order_release:
                break;
            case R.id.my_order_receive:
                break;
            case R.id.my_order_finish:
                break;
            case R.id.feed_back:
                break;
            case R.id.about_us:
                break;
            case R.id.version:
                break;
        }
    }
}
