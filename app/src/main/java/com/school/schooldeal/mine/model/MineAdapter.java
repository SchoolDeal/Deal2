package com.school.schooldeal.mine.model;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.school.schooldeal.R;
import com.school.schooldeal.base.BaseRecyclerAdapter;
import com.school.schooldeal.base.BaseViewHolder;
import com.school.schooldeal.commen.util.ToastUtil;
import com.school.schooldeal.commen.util.Util;
import com.school.schooldeal.mine.view.AboutUsActivity;
import com.school.schooldeal.mine.view.FedBackActivity;
import com.school.schooldeal.mine.view.MineReceivedActivity;
import com.school.schooldeal.takeout.view.TakeoutListActivity;

import io.rong.imkit.RongIM;
import io.rong.imlib.model.CSCustomServiceInfo;

/**
 * Created by U-nookia on 2017/2/14.
 */

public class MineAdapter extends BaseRecyclerAdapter<MineRecyclerItemBean> {

    public MineAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getViewType(MineRecyclerItemBean mineRecyclerItemBean) {
        return 0;
    }

    @Override
    protected void bindData(BaseViewHolder holder, final MineRecyclerItemBean item) {
        ImageView img = holder.getView(R.id.item_img);
        TextView item_content = holder.getView(R.id.item_content);
        RelativeLayout itemView = holder.getView(R.id.item_view);
        img.setImageResource(item.getImgRes());
        item_content.setText(item.getItem());
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click(item);
            }
        });
    }

    private void click(MineRecyclerItemBean item) {
        switch (item.getItem()){
            case Util.order_release:
                if (Util.IS_STUDENT){

                }else{
                    TakeoutListActivity.actionStart(getContext(), TakeoutListActivity.PUBLISHED);
                }
                //发布的订单
                ToastUtil.makeShortToast(getContext(),item.getItem());
                break;
            case Util.order_receive:
                //TakeoutListActivity.actionStart(getContext(), TakeoutListActivity.RECEIVED);
                Intent intent = new Intent(getContext(), MineReceivedActivity.class);
                getContext().startActivity(intent);
                //接收的订单
                break;
            case Util.order_finish:
                TakeoutListActivity.actionStart(getContext(), TakeoutListActivity.FINISHED);
                //完成的订单
                break;
            case Util.fed_back:
                //反馈错误
                getContext().startActivity(FedBackActivity.getIntentToFedBackActivity(getContext()));
                break;
            case Util.customer_service:
                //联系客服
                startCustumerService();
                break;
            case Util.about_us:
                //关于我们
                getContext().startActivity(AboutUsActivity.getIntent(getContext()));
                break;
            case Util.version_up:
                //版本升级
                ToastUtil.makeShortToast(getContext(),"已是最新版本");
                break;
        }
    }

    /*
    启动客服服务
     */
    private void startCustumerService() {
        //首先需要构造使用客服者的用户信息
        CSCustomServiceInfo.Builder csBuilder = new CSCustomServiceInfo.Builder();
        CSCustomServiceInfo csInfo = csBuilder.nickName("融云").build();
        /**
         * 启动客户服聊天界面
         * @param context           应用上下文。
         * @param customerServiceId 要与之聊天的客服 Id。
         * @param title             聊天的标题，如果传入空值，则默认显示与之聊天的客服名称。
         * @param customServiceInfo 当前使用客服者的用户信息。{@link io.rong.imlib.model.CSCustomServiceInfo}
         */
        RongIM.getInstance().startCustomerServiceChat(getContext(), "KEFU148662207661664", "在线客服",csInfo);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MineViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_mine,parent,false));
    }
}
