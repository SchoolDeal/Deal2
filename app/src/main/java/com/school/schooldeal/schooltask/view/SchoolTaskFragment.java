package com.school.schooldeal.schooltask.view;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.view.View;

import com.school.schooldeal.R;
import com.school.schooldeal.base.BaseFragment;
import com.school.schooldeal.schooltask.presenter.SchoolTaskPresenter;

import butterknife.BindView;

/**
 * Created by U-nookia on 2016/12/20.
 */

public class SchoolTaskFragment extends BaseFragment implements ImplSchoolTaskFragment{
    /*@BindView(R.id.schoolTaskRecycler)
    RecyclerView schoolTaskRecyclerView;*/
    private SchoolTaskPresenter presenter;
    @BindView(R.id.school_task_card_express)
    CardView express;
    @BindView(R.id.school_task_card_take_food)
    CardView takeFood;
    @BindView(R.id.school_task_card_shopping)
    CardView shopping;
    @Override
    protected void initData() {
        /*initRecyclerView();
        presenter = new SchoolTaskPresenter(getContext(),this);
        presenter.initAdapter();*/
        express.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),SchoolTaskActivity.class);
                intent.putExtra("title","Express");
                startActivity(intent);
            }
        });

        takeFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),SchoolTaskActivity.class);
                intent.putExtra("title","TakeFood");
                startActivity(intent);
            }
        });

        shopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),SchoolTaskActivity.class);
                intent.putExtra("title","Shopping");
                startActivity(intent);
            }
        });
    }

    @Override
    protected int getResourceId() {
        return R.layout.fragment_school_task;
    }

    /*@Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()){
            case R.id.school_task_card_express:
                Log.e("data","1");
                intent = new Intent(getActivity(),SchoolTaskActivity.class);
                break;
            case R.id.school_task_card_take_food:
                Log.e("data","2");
                intent = new Intent(getActivity(),SchoolTaskActivity.class);
                break;
            case R.id.school_task_card_shopping:
                Log.e("data","3");
                intent = new Intent(getActivity(),SchoolTaskActivity.class);
                break;
            default:
                break;
        }
        startActivity(intent);
    }*/
}
