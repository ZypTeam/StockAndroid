package com.yiyoupin.stock.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.yiyoupin.stock.ui.util.HomeFragmentUtil;
import com.yiyoupin.stock.ui.util.MainFragmentUtil;

/**
 * @author wangcc
 * @date 2018/4/16
 * @describe
 */
public class MainAdapter extends FragmentPagerAdapter {


    public MainAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return MainFragmentUtil.getInstance(position);
    }

    @Override
    public int getCount() {
        return 2;
    }
}
