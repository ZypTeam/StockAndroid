package com.yiyoupin.stock.ui.fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yiyoupin.stock.R;
import com.yiyoupin.stock.ui.activity.EditPersonInfoActivity;
import com.yiyoupin.stock.ui.activity.LoginActivity;
import com.yiyoupin.stock.ui.base.BaseStockFragment;
import com.yiyoupin.stock.ui.util.UiUtils;

/**
 * @author zhaoyapeng
 * @version create time:18/3/2013:54
 * @Email zyp@jusfoun.com
 * @Description ${我的}
 */
public class MyFrgament extends BaseStockFragment {

    protected TextView username;
    protected TextView consume;
    protected TextView balance;
    protected TextView editPassword;
    protected TextView payInfo;
    protected TextView checkUpdate;
    protected TextView aboutUs;
    protected TextView msgList;
    protected TextView exit;
    private ImageView iconHead;
    private TextView editPersion;

    public static MyFrgament getInstance() {
        MyFrgament fragment = new MyFrgament();
        return fragment;
    }

    @Override
    protected void refreshData() {

    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_my;
    }

    @Override
    public void initDatas() {

    }

    @Override
    public void initView(View rootView) {
        iconHead = rootView.findViewById(R.id.icon_head);
        editPersion = rootView.findViewById(R.id.edit_info);
        username = (TextView) rootView.findViewById(R.id.username);
        consume = (TextView) rootView.findViewById(R.id.consume);
        balance = (TextView) rootView.findViewById(R.id.balance);
        editPassword = (TextView) rootView.findViewById(R.id.edit_password);
        payInfo = (TextView) rootView.findViewById(R.id.pay_info);
        checkUpdate = (TextView) rootView.findViewById(R.id.check_update);
        aboutUs = (TextView) rootView.findViewById(R.id.about_us);
        msgList = (TextView) rootView.findViewById(R.id.msg_list);
        exit = (TextView) rootView.findViewById(R.id.exit);
    }

    @Override
    public void initAction() {
        iconHead.setOnClickListener(v -> {
            goActivity(null, LoginActivity.class);
        });

        editPersion.setOnClickListener(v -> {
            goActivity(null, EditPersonInfoActivity.class);
        });

        aboutUs.setOnClickListener(v -> {
            UiUtils.goAboutUs(mContext);
        });

        payInfo.setOnClickListener(v -> {
            UiUtils.goPayList(mContext);
        });

        editPassword.setOnClickListener(v -> {
            UiUtils.goChangePass(mContext);
        });

        msgList.setOnClickListener(v -> {
            UiUtils.goMsgList(mContext);
        });
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UiUtils.goLoginActivity(mContext);
            }
        });
    }
}
