package com.yiyoupin.stock.ui.fragment;

import android.view.View;

import com.yiyoupin.stock.R;
import com.yiyoupin.stock.ui.base.BaseStockFragment;

/**
 * @author zhaoyapeng
 * @version create time:18/3/2013:54
 * @Email zyp@jusfoun.com
 * @Description ${行情}
 */
public class QuotesFrgament extends BaseStockFragment {

    public static QuotesFrgament getInstance() {
        QuotesFrgament fragment = new QuotesFrgament();
        return fragment;
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_quotes;
    }

    @Override
    public void initDatas() {

    }

    @Override
    public void initView(View rootView) {

    }

    @Override
    public void initAction() {

    }

    @Override
    protected void refreshData() {

    }

}
