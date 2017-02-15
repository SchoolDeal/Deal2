package com.school.schooldeal.takeout.view;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;

import com.school.schooldeal.R;
import com.school.schooldeal.base.BaseFragment;
import com.school.schooldeal.commen.util.Util;
import com.school.schooldeal.takeout.model.TakeOutDataAdapter;
import com.school.schooldeal.takeout.presenter.TakeOutFragmentPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by U-nookia on 2016/12/19.
 * 外卖界面的view
 */

public class TakeOutFragment extends BaseFragment
        implements ImplTakeOutFragment {

    public static final String className = "TakeOutFragment";

    @BindView(R.id.takeOutRecycler)
    RecyclerView takeOutRecycler;
    @BindView(R.id.takeout_fab)
    FloatingActionButton mTakeoutFab;

    private TakeOutFragmentPresenter presenter;
    //FloatingActionBtn动画
    private static final Interpolator INTERPOLATOR = new FastOutSlowInInterpolator();

    @Override
    protected void initData() {
        initRecycler();
        presenter = new TakeOutFragmentPresenter(getContext(), this);
        presenter.initAdapter();
        if (Util.IS_STUDENT){
            Log.d(className, "is student user");
            mTakeoutFab.setVisibility(View.GONE);
        }else {
            Log.d(className, "is restaurant user");
            addOnScrollListener();
        }
    }

    private void initRecycler() {
        takeOutRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    protected int getResourceId() {
        return R.layout.fragment_takeout;
    }

    @Override
    public void setAdapter(TakeOutDataAdapter adapter) {
        takeOutRecycler.setAdapter(adapter);
    }

    @OnClick(R.id.takeout_fab)
    public void onClick() {
        Intent intent = new Intent(getContext(), TakeoutGenerateActivity.class);
        startActivity(intent);
    }

    //RecyclerView滑动监听
    private void addOnScrollListener(){
        takeOutRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                //
                boolean isSignificantDelta = Math.abs(dy) > 4;
                if (isSignificantDelta) {
                    if (dy > 0) {
                        //向上滑动时隐藏FloatingActionButton
                        mTakeoutFab.hide();
//                        ViewCompat.animate(mTakeoutFab)
//                                .translationY(mTakeoutFab.getHeight() + getMarginBottom(mTakeoutFab))
//                                .setInterpolator(INTERPOLATOR)
//                                .withLayer()
//                                .setListener(null)
//                                .start();
                    } else {
                        //向下滑动时显示FloatingActionButton
                        mTakeoutFab.show();
//                        ViewCompat.animate(mTakeoutFab).translationY(0)
//                               .setInterpolator(INTERPOLATOR)
//                                .withLayer()
//                                .setListener(null)
//                               .start();
                    }
                }
            }
        });
    }

    private int getMarginBottom(View v) {
        int marginBottom = 0;
        final ViewGroup.LayoutParams layoutParams = v.getLayoutParams();
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            marginBottom = ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin;
        }
        return marginBottom;
    }


}
