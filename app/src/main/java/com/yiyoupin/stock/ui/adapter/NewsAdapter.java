package com.yiyoupin.stock.ui.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.yiyoupin.stock.ui.fragment.NewsFragment;
import com.yiyoupin.stock.ui.util.HomeFragmentUtil;

/**
 * @author zhaoyapeng
 * @version create time:17/12/2115:44
 * @Email zyp@jusfoun.com
 * @Description ${首页fragment}
 */
public class NewsAdapter extends FragmentPagerAdapter {


    public NewsAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return NewsFragment.getInstance();
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public CharSequence getPageTitle(int position) {
//        if (mTitles != null && mTitles.size() > 0) {
//            return mTitles.get(position);
//        }

        return "公告";

//        return super.getPageTitle(position);
    }

}
