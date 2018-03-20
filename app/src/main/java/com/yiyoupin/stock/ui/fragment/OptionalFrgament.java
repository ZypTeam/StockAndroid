package com.yiyoupin.stock.ui.fragment;

import android.view.View;

import com.yiyoupin.stock.R;
import com.yiyoupin.stock.ui.base.BaseStockFragment;

/**
 * @author zhaoyapeng
 * @version create time:18/3/2013:54
 * @Email zyp@jusfoun.com
 * @Description ${自选}
 */
public class OptionalFrgament extends BaseStockFragment {

    public static OptionalFrgament getInstance() {
        OptionalFrgament fragment = new OptionalFrgament();
        return fragment;
    }

    @Override
    protected void refreshData() {

    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_optional;
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
}
