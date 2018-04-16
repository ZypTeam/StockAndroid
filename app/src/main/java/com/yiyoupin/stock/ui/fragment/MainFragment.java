package com.yiyoupin.stock.ui.fragment;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jusfoun.baselibrary.view.HomeViewPager;
import com.yiyoupin.stock.R;
import com.yiyoupin.stock.ui.adapter.HomeAdapter;
import com.yiyoupin.stock.ui.base.BaseStockFragment;

/**
 * @author wangcc
 * @date 2018/4/16
 * @describe
 */
public class MainFragment extends BaseStockFragment {

    protected HomeViewPager viewpager;
    protected ImageView imgHome;
    protected TextView textHome;
    protected RelativeLayout layoutHome;
    protected ImageView imgQuotes;
    protected TextView textQuotes;
    protected RelativeLayout layoutQuotes;
    protected ImageView imgOptional;
    protected TextView textOptional;
    protected RelativeLayout layoutOptional;
    protected ImageView imgMy;
    protected TextView textMy;
    protected RelativeLayout layoutMy;
    private HomeAdapter homeAdapter;

    public static MainFragment getInstance(Bundle args) {
        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void refreshData() {

    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_main;
    }

    @Override
    public void initDatas() {
        homeAdapter = new HomeAdapter(getChildFragmentManager());
    }

    @Override
    public void initView(View rootView) {
        viewpager = (HomeViewPager) rootView.findViewById(R.id.viewpager);
        imgHome = (ImageView) rootView.findViewById(R.id.img_home);
        textHome = (TextView) rootView.findViewById(R.id.text_home);
        layoutHome = (RelativeLayout) rootView.findViewById(R.id.layout_home);
        imgQuotes = (ImageView) rootView.findViewById(R.id.img_quotes);
        textQuotes = (TextView) rootView.findViewById(R.id.text_quotes);
        layoutQuotes = (RelativeLayout) rootView.findViewById(R.id.layout_quotes);
        imgOptional = (ImageView) rootView.findViewById(R.id.img_optional);
        textOptional = (TextView) rootView.findViewById(R.id.text_optional);
        layoutOptional = (RelativeLayout) rootView.findViewById(R.id.layout_optional);
        imgMy = (ImageView) rootView.findViewById(R.id.img_my);
        textMy = (TextView) rootView.findViewById(R.id.text_my);
        layoutMy = (RelativeLayout) rootView.findViewById(R.id.layout_my);
    }

    @Override
    public void initAction() {
        viewpager.setAdapter(homeAdapter);
        viewpager.setNotTouchScoll(true);

        layoutHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                select(0);
            }
        });
        layoutQuotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                select(1);
            }
        });
        layoutOptional.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                select(2);
            }
        });
        layoutMy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                select(3);
            }
        });

        if (getArguments()==null){
            //登录跳转
            select(3);
        }else {
            select(0);
        }
    }

    private void select(int position) {
        viewpager.setCurrentItem(position, false);

        imgHome.setImageResource(R.drawable.img_home_normal);
        textHome.setTextColor(getResources().getColor(R.color.home_bottom__btn_text_normal));
        layoutHome.setBackgroundResource(R.color.home_bottom_btn_normal);

        imgQuotes.setImageResource(R.drawable.img_quotes_noraml);
        textQuotes.setTextColor(getResources().getColor(R.color.home_bottom__btn_text_normal));
        layoutQuotes.setBackgroundResource(R.color.home_bottom_btn_normal);

        imgOptional.setImageResource(R.drawable.img_optional_normal);
        textOptional.setTextColor(getResources().getColor(R.color.home_bottom__btn_text_normal));
        layoutOptional.setBackgroundResource(R.color.home_bottom_btn_normal);

        imgMy.setImageResource(R.drawable.img_my_normal);
        textMy.setTextColor(getResources().getColor(R.color.home_bottom__btn_text_normal));
        layoutMy.setBackgroundResource(R.color.home_bottom_btn_normal);

        switch (position) {
            case 0:
                imgHome.setImageResource(R.drawable.img_home_select);
                textHome.setTextColor(getResources().getColor(R.color.home_bottom__btn_text_selecter));
                layoutHome.setBackgroundResource(R.color.home_bottom_btn_selscter);
                break;
            case 1:
                imgQuotes.setImageResource(R.drawable.img_quotes_selecter);
                textQuotes.setTextColor(getResources().getColor(R.color.home_bottom__btn_text_selecter));
                layoutQuotes.setBackgroundResource(R.color.home_bottom_btn_selscter);
                break;
            case 2:
                imgOptional.setImageResource(R.drawable.img_optional_select);
                textOptional.setTextColor(getResources().getColor(R.color.home_bottom__btn_text_selecter));
                layoutOptional.setBackgroundResource(R.color.home_bottom_btn_selscter);
                break;
            case 3:
                imgMy.setImageResource(R.drawable.img_my_select);
                textMy.setTextColor(getResources().getColor(R.color.home_bottom__btn_text_selecter));
                layoutMy.setBackgroundResource(R.color.home_bottom_btn_selscter);
                break;
            default:
                break;
        }
    }
}
