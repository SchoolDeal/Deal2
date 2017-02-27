package com.school.schooldeal.schooltask.view;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.school.schooldeal.R;
import com.school.schooldeal.base.BaseActivity;
import com.school.schooldeal.commen.util.ToastUtil;
import com.school.schooldeal.schooltask.presenter.SchoolTaskReasePresenter;

import java.util.List;

import butterknife.BindView;

/**
 * Created by 教科书式的机智少年 on 2017/2/9.
 */

public class SchoolTaskReleaseActivity extends BaseActivity implements ImplSchoolTaskRelease,View.OnClickListener {
    private SchoolTaskReasePresenter presenter;
    @BindView(R.id.school_task_release_button)
    Button send;
    @BindView(R.id.school_task_release_toolbar)
    Toolbar toolbar;
    @BindView(R.id.school_task_release_arrow)
    ImageView arrow;
    @BindView(R.id.school_task_release_storename)
    TextView storename;
    @BindView(R.id.school_task_release_refund)
    EditText refund;
    @BindView(R.id.school_task_release_text)
    EditText content;
    @BindView(R.id.school_task_release_remarks)
    EditText remarks;
    private List<String> stores;
    private Boolean chooseStore = false;
    private MaterialDialog materialDialog;
    private MsgRecive msgRecive;
    @Override
    protected void initData() {
        initToolBar();
        presenter = new SchoolTaskReasePresenter(this,context);
        presenter.getStoresName(stores);
        send.setOnClickListener(this);
        arrow.setOnClickListener(this);
        storename.setOnClickListener(this);
        msgRecive = new MsgRecive();
        IntentFilter filter = new IntentFilter();
        filter.addAction("deal.school.action.SCHOOLMSG");
        registerReceiver(msgRecive,filter);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_schooltask_release;
    }

    private void initToolBar(){
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        toolbar.setTitle("发布");
    }

    @Override
    public void onClick(View v) {
        String refundText;
        String contentText;
        String remarksText;
        switch (v.getId()){
            case R.id.school_task_release_button:
                refundText = refund.getText().toString();
                contentText = content.getText().toString();
                remarksText = remarks.getText().toString();
                if (refundText.isEmpty() && contentText.isEmpty() && chooseStore == false){
                    ToastUtil.makeShortToast(context,"请完善订单信息");
                }else {
                    materialDialog = new MaterialDialog.Builder(this)
                            .title("正在发送")
                            .progress(true,0)
                            .show();
                    presenter.sendMessage(storename.getText().toString(),contentText,refundText,remarksText);
                }
                break;
            case R.id.school_task_release_arrow:
            case R.id.school_task_release_storename:
                if (stores == null || stores.size() == 0){
                    ToastUtil.makeShortToast(context,"稍等，正在请求商店数据");
                }else {
                    MaterialDialog dialog = new MaterialDialog.Builder(context)
                            .title("选择商店")
                            .items(stores)
                            .itemsCallback(new MaterialDialog.ListCallback() {
                                @Override
                                public void onSelection(MaterialDialog dialog, View itemView, int position, CharSequence text) {
                                    storename.setText(text);
                                    chooseStore = true;
                                }
                            })
                            .show();
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void setStores(List<String> stores) {
        this.stores = stores;
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(msgRecive);
        super.onDestroy();
    }

    public class MsgRecive extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.e("Release","OK");
            materialDialog.dismiss();
        }
    }
}
