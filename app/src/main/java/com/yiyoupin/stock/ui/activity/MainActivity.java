package com.yiyoupin.stock.ui.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jusfoun.baselibrary.view.HomeViewPager;
import com.yiyoupin.stock.R;
import com.yiyoupin.stock.ui.adapter.HomeAdapter;
import com.yiyoupin.stock.ui.base.BaseStockActivity;

public class MainActivity extends BaseStockActivity {


    protected HomeViewPager viewpager;
    protected ImageView imgHome;
    protected TextView textHome;
    protected LinearLayout layoutHome;
    protected ImageView imgQuotes;
    protected TextView textQuotes;
    protected LinearLayout layoutQuotes;
    protected ImageView imgOptional;
    protected TextView textOptional;
    protected LinearLayout layoutOptional;
    protected ImageView imgMy;
    protected TextView textMy;
    protected LinearLayout layoutMy;
    private HomeAdapter homeAdapter;


    @Override
    public int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    public void initDatas() {
        homeAdapter = new HomeAdapter(getSupportFragmentManager());
    }

    @Override
    public void initView() {
        viewpager = (HomeViewPager) findViewById(R.id.viewpager);
        imgHome = (ImageView) findViewById(R.id.img_home);
        textHome = (TextView) findViewById(R.id.text_home);
        layoutHome = (LinearLayout) findViewById(R.id.layout_home);
        imgQuotes = (ImageView) findViewById(R.id.img_quotes);
        textQuotes = (TextView) findViewById(R.id.text_quotes);
        layoutQuotes = (LinearLayout) findViewById(R.id.layout_quotes);
        imgOptional = (ImageView) findViewById(R.id.img_optional);
        textOptional = (TextView) findViewById(R.id.text_optional);
        layoutOptional = (LinearLayout) findViewById(R.id.layout_optional);
        imgMy = (ImageView) findViewById(R.id.img_my);
        textMy = (TextView) findViewById(R.id.text_my);
        layoutMy = (LinearLayout) findViewById(R.id.layout_my);

    }

    @Override
    public void initAction() {
        viewpager.setAdapter(homeAdapter);
        viewpager.setNotTouchScoll(true);

        layoutHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewpager.setCurrentItem(0);
            }
        });
        layoutQuotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewpager.setCurrentItem(1);
            }
        });
        layoutOptional.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewpager.setCurrentItem(2);
            }
        });
        layoutMy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewpager.setCurrentItem(3);
            }
        });
    }
}
