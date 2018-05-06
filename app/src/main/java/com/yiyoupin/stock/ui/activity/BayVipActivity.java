package com.yiyoupin.stock.ui.activity;

import android.widget.ImageView;
import android.widget.TextView;

import com.yiyoupin.stock.R;
import com.yiyoupin.stock.delegate.UserInfoDelegate;
import com.yiyoupin.stock.model.UserModel;
import com.yiyoupin.stock.ui.base.BaseStockActivity;
import com.yiyoupin.stock.ui.view.BackTitleView;
import com.yiyoupin.stock.ui.view.FormView;

/**
 * @author wangcc
 * @date 2018/3/30
 * @describe
 */

public class BayVipActivity extends BaseStockActivity {
    protected BackTitleView titleView;
    protected TextView aboutUs;
    protected FormView nameUser;
    protected FormView startTime;
    protected FormView endTime;
    protected FormView price;
    protected ImageView qrCode;
    private UserModel userModel;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_buy_vip;
    }

    @Override
    public void initDatas() {
        userModel= UserInfoDelegate.getInstance().getUserInfo();
    }

    @Override
    public void initView() {
        titleView = (BackTitleView) findViewById(R.id.title_view);
        aboutUs = (TextView) findViewById(R.id.about_us);
        nameUser = (FormView) findViewById(R.id.name_user);
        startTime = (FormView) findViewById(R.id.start_time);
        endTime = (FormView) findViewById(R.id.end_time);
        price = (FormView) findViewById(R.id.price);
        qrCode = (ImageView) findViewById(R.id.qr_code);

    }

    @Override
    public void initAction() {
        titleView.setTitle("购买VIP服务器");
        nameUser.setName("姓名");
        nameUser.setContent(userModel.getNick_name());

        startTime.setName("开始时间");
        startTime.setContent("2018-02-01");

        endTime.setName("结束时间");
        endTime.setContent("2019-01-31");

        endTime.setName("价格");
        endTime.setContent("100元");
    }

}
