package com.school.schooldeal.schooltask.model;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.school.schooldeal.R;
import com.school.schooldeal.base.BaseRecyclerAdapter;
import com.school.schooldeal.base.BaseViewHolder;
import com.school.schooldeal.commen.util.ToastUtil;
import com.school.schooldeal.model.CommonRequest;
import com.school.schooldeal.model.CommonService;
import com.school.schooldeal.model.Student;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.GetListener;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by 教科书式的机智少年 on 2017/1/20.
 */

public class SchoolTaskDataAdapter extends BaseRecyclerAdapter<SchoolTaskOrderBean>{
    private OnSchoolTaskItemClickListener onItemClickListener = null;
    private Context context;

    public SchoolTaskDataAdapter(Context context){
        super(context);
        this.context = context;
    }

    @Override
    protected int getViewType(SchoolTaskOrderBean schoolTaskOrderBean) {
        return schoolTaskOrderBean.getAmount();
    }

    @Override
    protected void bindData(BaseViewHolder holder, final SchoolTaskOrderBean item) {
        /*final SchoolTaskViewHolder viewHolder = (SchoolTaskViewHolder)holder;
        final CommonRequest commonRequest = item.getCommonRequest();
        Student student = commonRequest.getStudent();
        final CardView root = viewHolder.getRoot();
        viewHolder.getLocation().setText(student.getUsername());
        viewHolder.getDescribe().setText(commonRequest.getRequestContent());
        viewHolder.getRemuneration().setText(commonRequest.getRemuneration()+"元");

        viewHolder.getRob().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonService commonService = new CommonService();
                Student student = new Student();
                student.setObjectId("zxc");
                commonService.setRequest(commonRequest);
                commonService.setStudent(student);
                commonService.setRemuneration(commonRequest.getRemuneration());
                commonService.save(context, new SaveListener() {
                    @Override
                    public void onSuccess() {
                        ToastUtil.makeShortToast(context,"抢单成功");
                        getLists().remove(item.getAmount());
                        notifyItemRemoved(item.getAmount());
                        notifyItemRangeChanged(item.getAmount(),getItemCount());
                    }

                    @Override
                    public void onFailure(int i, String s) {
                        ToastUtil.makeShortToast(context,"抢单失败");
                    }
                });
            }
        });

        root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(root,commonRequest);
            }
        });*/
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, final int position) {
        super.onBindViewHolder(holder, position);
        SchoolTaskViewHolder viewHolder = (SchoolTaskViewHolder) holder;
        final CommonRequest commonRequest = getLists().get(position).getCommonRequest();
        Student student = commonRequest.getStudent();
        final CardView root = viewHolder.getRoot();
        final Button rob = viewHolder.getRob();
        viewHolder.getLocation().setText(student.getBedroom());
        viewHolder.getDescribe().setText(commonRequest.getRequestContent());
        viewHolder.getRemuneration().setText(commonRequest.getRemuneration()+"元");
        viewHolder.getStorename().setText(commonRequest.getStore().getStoreName()+":");

        rob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BmobQuery<CommonRequest> query = new BmobQuery<CommonRequest>();
                query.getObject(context, commonRequest.getObjectId(), new GetListener<CommonRequest>() {
                    @Override
                    public void onSuccess(CommonRequest request) {
                        if (request.getType() == 0){
                            CommonService commonService = new CommonService();
                            commonRequest.setType(1);
                            commonRequest.update(context);
                            Student student = new Student();
                            student.setObjectId("b91d31b71e");
                            commonService.setRequest(commonRequest);
                            commonService.setStudent(student);
                            commonService.setRemuneration(commonRequest.getRemuneration());
                            commonService.save(context, new SaveListener() {
                                @Override
                                public void onSuccess() {
                                    ToastUtil.makeShortToast(context,"抢单成功");
                                    getLists().remove(position);
                                    notifyItemRemoved(position);
                                    notifyItemRangeChanged(position,getItemCount());
                                }

                                @Override
                                public void onFailure(int i, String s) {
                                    ToastUtil.makeShortToast(context,"抢单失败");
                                }
                            });
                        }else {
                            ToastUtil.makeShortToast(context,"糟糕，这个已经被抢了呢");
                            getLists().remove(position);
                            notifyItemRemoved(position);
                            notifyItemRangeChanged(position,getItemCount());
                        }
                    }

                    @Override
                    public void onFailure(int i, String s) {
                        ToastUtil.makeShortToast(context,"抢单失败");
                    }
                });
            }
        });

        root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(root,commonRequest,position);
            }
        });


    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SchoolTaskViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recycler_school_task,parent,false),context);
    }
    public void setOnItemClickListener(OnSchoolTaskItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
    public void setnewItems(List<SchoolTaskOrderBean> newOrders){
        getLists().clear();
        getLists().addAll(newOrders);
        notifyDataSetChanged();
    }

    public interface OnSchoolTaskItemClickListener{
        void onItemClick(View view,CommonRequest commonRequest,int position);
    }
}

