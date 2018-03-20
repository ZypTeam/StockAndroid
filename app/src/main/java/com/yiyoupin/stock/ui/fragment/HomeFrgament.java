package com.yiyoupin.stock.ui.fragment;

import android.view.View;

import com.yiyoupin.stock.R;
import com.yiyoupin.stock.ui.base.BaseStockFragment;

/**
 * @author zhaoyapeng
 * @version create time:18/3/2013:54
 * @Email zyp@jusfoun.com
 * @Description ${首页}
 */
public class HomeFrgament extends BaseStockFragment {


    public static HomeFrgament getInstance() {
        HomeFrgament fragment = new HomeFrgament();
        return fragment;
    }

    @Override
    protected void refreshData() {

    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_home;
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
