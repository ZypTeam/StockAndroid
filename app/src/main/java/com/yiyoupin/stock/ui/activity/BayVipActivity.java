package com.yiyoupin.stock.ui.activity;

import android.widget.TextView;

import com.yiyoupin.stock.R;
import com.yiyoupin.stock.ui.base.BaseStockActivity;
import com.yiyoupin.stock.ui.view.BackTitleView;

/**
 * @author wangcc
 * @date 2018/3/30
 * @describe
 */

public class BayVipActivity extends BaseStockActivity {
    protected BackTitleView titleView;
    protected TextView aboutUs;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_buy_vip;
    }

    @Override
    public void initDatas() {
    }

    @Override
    public void initView() {
        titleView = (BackTitleView) findViewById(R.id.title_view);
        aboutUs = (TextView) findViewById(R.id.about_us);

    }

    @Override
    public void initAction() {
        titleView.setTitle("购买VIP服务器");
    }

}
