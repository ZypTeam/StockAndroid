package com.yiyoupin.stock.ui.activity;

import com.yiyoupin.stock.R;
import com.yiyoupin.stock.ui.base.BaseStockActivity;
import com.yiyoupin.stock.ui.view.BackTitleView;

/**
 * @author zhaoyapeng
 * @version create time:18/3/2815:49
 * @Email zyp@jusfoun.com
 * @Description ${个股展示}
 */
public class StockShowActivity extends BaseStockActivity {

    protected BackTitleView titlebar;
    private BackTitleView backTitleView;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_stock_show;
    }

    @Override
    public void initDatas() {

    }

    @Override
    public void initView() {
        titlebar = (BackTitleView) findViewById(R.id.titlebar);

    }

    @Override
    public void initAction() {
        titlebar.setTitle("个股展示");
    }
}
