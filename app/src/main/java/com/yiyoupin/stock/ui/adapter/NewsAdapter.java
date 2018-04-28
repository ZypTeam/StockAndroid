package com.yiyoupin.stock.ui.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.yiyoupin.stock.model.NewModel;
import com.yiyoupin.stock.ui.fragment.NewsFragment;

/**
 * @author zhaoyapeng
 * @version create time:17/12/2115:44
 * @Email zyp@jusfoun.com
 * @Description ${首页fragment}
 */
public class NewsAdapter extends FragmentPagerAdapter {


    private NewModel model;

    public NewsAdapter(FragmentManager fm) {
        super(fm);
        model = new NewModel();
    }

    @Override
    public Fragment getItem(int position) {
        return NewsFragment.getInstance(model, position);
    }

    @Override
    public int getCount() {
        if (model != null) {
            return 3;
        } else {
            return 0;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
//        if (mTitles != null && mTitles.size() > 0) {
//            return mTitles.get(position);
//        }

        return "公告";
    }


    public void setData(NewModel model) {
        this.model= model;
        notifyDataSetChanged();
    }

}
