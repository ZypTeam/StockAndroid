package com.yiyoupin.stock.ui.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.yiyoupin.stock.ui.fragment.DetailsFragment;
import com.yiyoupin.stock.ui.util.HomeFragmentUtil;

/**
 * @author zhaoyapeng
 * @version create time:17/12/2115:44
 * @Email zyp@jusfoun.com
 * @Description ${首页fragment}
 */
public class DetailsFragmentAdapter extends FragmentPagerAdapter {


    public DetailsFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return DetailsFragment.getInstance(position);
    }

    @Override
    public int getCount() {
        return 2;
    }
}
